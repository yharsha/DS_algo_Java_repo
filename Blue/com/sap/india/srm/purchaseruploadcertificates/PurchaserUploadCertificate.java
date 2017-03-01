package com.sap.india.srm.purchaseruploadcertificates;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sap.india.srm.main.MainApplet;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;



/*
 * This class is solely responsible for uploading the certificate of purchasers. This can be worked as a stand alone application or linked into the portal. The class will take the certificate details and store the
 * information in the data base that can be fetched when needed. So the uploading certificate is just one time activity.
 */

@SuppressWarnings("serial")
public class PurchaserUploadCertificate extends MainApplet implements ActionListener,ListSelectionListener{
 
	
	//Define UI elements of the applet...
	JPanel content;
	JButton upload;
	JButton migrate;
	JButton cancel;
	JTable table;
	JScrollPane scrollPane;
	
	//Array of certificates available in microsoft keystore...
	ArrayList<X509Certificate> certificates;
	X509Certificate selectedCertificate;
	
	//Mode constant...
	String mode = "upload";
	
	//JavaScript Objects.....
	public static JSObject object;
	public static JSObject objectMainForm;
	
	
	public PurchaserUploadCertificate(){
		init();
	}
	
	
	//Instantiate the UI components and add event listeners....
	public void init(){
		   content = new JPanel();
		   Color col = new Color(169, 208, 245, 100);
		   content.setLayout(null);
		   upload = new JButton("Upload");
		   cancel = new JButton("Cancel");
		   
		   
		   //JLabel coloredLabel = new JLabel("Please select an appropriate certificate", JLabel.CENTER);
		   JButton coloredLabel = new JButton("Please select an appropriate certificate");
		   coloredLabel.setBounds(20, 0, 800, 20);
		   coloredLabel.setEnabled(false);
		   content.add(coloredLabel);
		   coloredLabel.setFocusable(true);

		   
		   PurchaserListAllCertificates listCertificates = new PurchaserListAllCertificates();
		   Object rows[][] = new Object[listCertificates.certificateSize][6];
		   rows=listCertificates.getAllCertificatesFromMSStore();
		   certificates = listCertificates.getAllCertificateObjects();
		   if(certificates == null){
			   JOptionPane.showMessageDialog(null, "No certificates found !", "warning", 1);
			   return ;
		   }
		   Object columns[] = { "Issued To", "Version", "Email","Issued By","Purpose","Valid Till" };
		   table = new JTable(rows, columns){

				public boolean isCellEditable(int rowIndex, int vColIndex) {
			         return false;
			    }
		   };
		   table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		   table.getSelectionModel().addListSelectionListener(this);
		   table.setRowSelectionAllowed(true);
		   
		   
		   //Set preferred size of all the columns....
		   table.getColumnModel().getColumn(0).setPreferredWidth(150);
		   table.getColumnModel().getColumn(1).setPreferredWidth(20);
		   table.getColumnModel().getColumn(2).setPreferredWidth(150);
		   table.getColumnModel().getColumn(3).setPreferredWidth(150);
		   table.getColumnModel().getColumn(4).setPreferredWidth(100);
		   table.getColumnModel().getColumn(5).setPreferredWidth(90);
		   
		   
		   
		   
		   scrollPane = new JScrollPane(table);
		   
		   
		   scrollPane.setBounds(20, 20, 800, 145);
		   content.add(scrollPane);
		   
		   cancel.setBounds(120, 190, 100, 40);
		   cancel.addActionListener(this);
		   //content.add(cancel);
		   
		   upload.setBounds(20, 190, 100, 40);
		   upload.addActionListener(this);
		   upload.setEnabled(false);
		   content.add(upload);
		   content.setBackground(col);
		   
		   scrollPane.setBackground(col);
		   
		   this.add(content);
		   this.setSize(700, 700);
		   this.setVisible(true);
		   
		   try{
			   UIManager.setLookAndFeel(
				   UIManager.getSystemLookAndFeelClassName());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   SwingUtilities.updateComponentTreeUI(this);
		   
	}
	
	//Gets triggered when a row is selected in the panel...
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == table.getSelectionModel()){
			selectedCertificate = certificates.get(table.getSelectedRow());
			upload.setEnabled(true);
		}	
		
	}
	
	//Gets triggered when either upload or cancel button is triggered....
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == upload){
			//The following method uploads certificate to the database after processing.....
			processUploadingCertificate();
		}
		else{
			if(object !=null){
				try{
					JOptionPane.showMessageDialog(null, "You have cancelled operation !", "Information", 1);
					object.call("cancelForm", null);
				}catch(JSException jsexc){
					System.out.println(jsexc.getMessage());
				}
				catch(Exception ie){
					System.out.println(ie.getMessage());
				}
			}
		}
	}
	
	//Process uploading certificate using base64 encoding decoding technique..
	private void processUploadingCertificate() {
		// TODO Auto-generated method stub
		PurchaserGeneralUtilites pgu = new PurchaserGeneralUtilites();
		byte[]  certificateInByteArray = pgu.getByteArrayObject(selectedCertificate);
		PurchaserBase64EncodeDecode encdec =new PurchaserBase64EncodeDecode();
		String encodedCertificate = encdec.base64Encode(certificateInByteArray);
		try{
			//The following method sets the attributes to the form and submits to the ABAP......
			setMembersIntoMainForm(selectedCertificate, encodedCertificate);
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Uploading unsuccessful. Please try again !", "Error", 1);
			System.out.println(e.getMessage());
		}
		
	}
	

	//Set the certificate into Main Form of BSP page....
	private void setMembersIntoMainForm(X509Certificate selcertificate,String enc) throws Exception {
		// TODO Auto-generated method stub
		if(object !=null && objectMainForm != null){
			try{
				//the following lines of codes will set the attributes of the input field of the form and submits the form to set it into the ABAP....
				PurchaserBase64EncodeDecode encdec = new PurchaserBase64EncodeDecode();
				JSObject PublicCertificate = (JSObject)objectMainForm.getMember("PublicCertificate");
				PublicCertificate.setMember("value", enc);
				JSObject SerialNumber = (JSObject)objectMainForm.getMember("SerialNumber");
				SerialNumber.setMember("value", selcertificate.getSerialNumber().toString());
				JSObject SubjectDN = (JSObject)objectMainForm.getMember("SubjectDN");
				SubjectDN.setMember("value", selcertificate.getSubjectX500Principal());   // method name changed
				JSObject ExpiryDate = (JSObject)objectMainForm.getMember("ExpiryDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			    String DateFormatted = formatter.format(selcertificate.getNotAfter());    
				
				ExpiryDate.setMember("value", DateFormatted);

				JSObject issuerDN = (JSObject)objectMainForm.getMember("IssuerDN");
				issuerDN.setMember("value", selcertificate.getIssuerX500Principal().toString()); // method name changed
				
				object.call("submitForm", null);
			}catch(JSException jsexc){
				JOptionPane.showMessageDialog(null, "Fatal error. Please try again !", "Warning", 1);
				System.out.println(jsexc.getMessage());
			}catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		}
	}

	//The JSObject from the Main Applet 
	public void sendJSObjectToPurchaser(JSObject object2) {
		// TODO Auto-generated method stub
		try{
			object = object2;
		}catch(JSException jsexc){
			System.out.println(jsexc.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	//The JS object for MainForm...
	public void sendMainFormToPurchaser(JSObject object3){
		try{
			objectMainForm = object3;
		}catch(JSException jsexc){
			System.out.println(jsexc.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMainFormToPurchaserWithExtraLargeSpace(){
		try{
			object.call("CancelMainFormActivity", null);
		}catch(Exception e){
			
		}
	}

	
}
