package com.sap.india.srm.purchasermigratecertificate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
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

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

//import com.sap.india.srm.encrypt.AppletDesign.MyFocusTraversalPolicy;
import com.sap.india.srm.main.MainApplet;
import com.sap.india.srm.purchaseruploadcertificates.PurchaserBase64EncodeDecode;

//This class is responsible for migrating the certificate and data associated with it....
public class PurchaserMigrateCertificate extends MainApplet implements ActionListener,ListSelectionListener{
	
	 
	JPanel content;
	JButton migrate;
	JButton cancel;
	JTable table;
	JScrollPane scrollPane;
	ArrayList<X509Certificate> certificates;
	X509Certificate oldSelectedCertificate;
	X509Certificate selectedCertificate;
	String mode = "migrate";
	JTextArea resultdisplay ;
	String value=null;
	public static JSObject object;
	public static JSObject objectMainForm;
	public static MainApplet AppletObject;
	public boolean isOldCertificateSelected = false;
	public boolean isAllowedEncryption = false;
	public String rows[][];
	public ArrayList<String> isMigrated;
	
	public int[] indexOfRfxNotDecrypted;
	
	
	public static int totalNoOfResponsesNotDecrypted = 0;
	
	public PurchaserMigrateCertificate(){
	}

	public void startPurchaserMigrateOperation(){
		init();
	}
	
	
	//initializes the UI elements for Applet...
	public void init(){
		
		   content = new JPanel();
		   content.setLayout(null);
		   migrate = new JButton("Migrate");
		   cancel = new JButton("Cancel");
		   
		   
		   //JLabel coloredLabel = new JLabel("Please select an appropriate certificate", JLabel.CENTER);
		   JButton coloredLabel = new JButton("Please select an appropriate certificate");
		   coloredLabel.setBounds(30, 10, 800, 20);
		   content.add(coloredLabel);
		   coloredLabel.setEnabled(false);
		   coloredLabel.setFocusable(true);
		   
		   
		   
		   PurchaserMigrateListAllCertificates listCertificates = new PurchaserMigrateListAllCertificates(AppletObject.getParameter("OldCertificate"));
		   if(!listCertificates.isOldCertificateExists){
			   JOptionPane.showMessageDialog(null, "Old certificate does not exist. \n Migration is not allowed !", "Error", 1);
			   return;
		   }
		   
		   
		   rows = new String[listCertificates.certificateSize][6];
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
		   table.setFocusable(true);
		   
		   scrollPane = new JScrollPane(table);
		   scrollPane.setBounds(30, 30, 800, 145);
		   content.add(scrollPane);
		   
		   /*cancel.setBounds(130, 190, 100, 40);
		   cancel.addActionListener(this);
		   content.add(cancel);*/
		   
		   migrate.setBounds(30, 190, 100, 40);
		   migrate.addActionListener(this);
		   migrate.setEnabled(false);
		   content.add(migrate);
		   
		   migrate.setFocusable(true);
		   
		   this.add(content);
		   this.setSize(700, 700);
		   this.setVisible(true);
		   Color col = new Color(169, 208, 245, 100);
		   content.setBackground(col);
		   this.requestFocusInWindow();
		   try{
				   UIManager.setLookAndFeel(
						   UIManager.getSystemLookAndFeelClassName());
		   }catch(Exception e){
				   e.printStackTrace();
		   }
		   SwingUtilities.updateComponentTreeUI(this);
		   
	}


	//Following method gets triggered when any row is selected....
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == table.getSelectionModel()){
			migrate.setEnabled(true);
			selectedCertificate = certificates.get(table.getSelectedRow());
		}
	}


	
	//Following method gets triggered when any button clicked ...
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == migrate ){
			int NoOfQTE = Integer.parseInt(AppletObject.getParameter("NoOfQTE"));
			if(NoOfQTE == 0){
				try{
					JOptionPane.showMessageDialog(null, "No Responses to be migrated!", "Information", 1);
					object.call("cancelForm", null);
				}catch(JSException jsexc){
					jsexc.printStackTrace();
				}catch(Exception ie){
					ie.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Migrating "+NoOfQTE+" respnoses.This may take a few minutes!", "Information", 1);
				processMigrationOfCertificate();
			}
		}else{
			try{
				JOptionPane.showMessageDialog(null, "You have cancelled operation !", "Information", 1);
				object.call("cancelForm", null);
			}catch(JSException jsexc){
				jsexc.printStackTrace();
			}catch(Exception ie){
				ie.printStackTrace();
			}
		}
	}

	//Following method initiates migration process of the certificate for the currently logged in purchaser...
	private void processMigrationOfCertificate() {
		// TODO Auto-generated method stub
		//Integer serialNoOfCeritificate = Integer.parseInt(AppletObject.getParameter("SerialNumber"));
		String certificate = AppletObject.getParameter("OldCertificate");
		
		
		PurchaserMigrateGeneralUtilities genutil = new PurchaserMigrateGeneralUtilities();
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		
		byte[] certificateInByte = base64.base64Decode(certificate);
		oldSelectedCertificate = (X509Certificate) genutil.getCertificateObject(certificateInByte);
		
		
		ArrayList<String> encryptedKeyPart = new ArrayList<String>();
		ArrayList<String> QTE = new ArrayList<String>();
		int NoOfQTE = Integer.parseInt(AppletObject.getParameter("NoOfQTE"));
		int i = 0;
		isMigrated = new ArrayList<String>(NoOfQTE);
		indexOfRfxNotDecrypted = new int[NoOfQTE];
		while(i<NoOfQTE){
			encryptedKeyPart.add(i, AppletObject.getParameter("Encrtpted_KeyPart["+i+"]"));
			QTE.add(i, AppletObject.getParameter("QTE_GUID["+i+"]"));
			isMigrated.add(i, "initial");
			indexOfRfxNotDecrypted[i] = 0;
			i++;
		}
		i=0;
		ArrayList<String> encryptedTechKeyPart = new ArrayList<String>();
		while(i<NoOfQTE){
			encryptedTechKeyPart.add(i, AppletObject.getParameter("Encrypted_TKeyPart["+i+"]"));
			i++;
		}
		processDecryptionOnTechKeyPart(encryptedTechKeyPart);
		
		processDecryptionOnKeyPart(encryptedKeyPart , QTE);
		
	}

	
	
	
	
	private void processDecryptionOnTechKeyPart(
			ArrayList<String> encryptedTechKeyPart) {
		// TODO Auto-generated method stub
		PurchaserMigrateGeneralUtilities genutil = new PurchaserMigrateGeneralUtilities();
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		ArrayList<String> encryptedKeyPartWithNewCertificate = new ArrayList<String>(); 
		
		
		
		
		int i = 0 ;
		byte[] keyPartInByteArray  = null;
	
		while(i < encryptedTechKeyPart.size()){
System.out.println("*********ENC TECH KEY is:"+encryptedTechKeyPart.get(i));
			if("".equalsIgnoreCase(encryptedTechKeyPart.get(i).trim()) ){
				encryptedKeyPartWithNewCertificate.add(i, "");
				isMigrated.add(i, "M");
				i++;
				continue;
			}
			try{
				keyPartInByteArray = base64.base64Decode(encryptedTechKeyPart.get(i));
				PurchaserMigrateDecryptionOfKeyPart decrypt = new PurchaserMigrateDecryptionOfKeyPart();
				String strDecryptedData = decrypt.decryptKeyPart(oldSelectedCertificate, keyPartInByteArray);
				PurchaserMigrateEncryptionOfKeyPart encrypt = new PurchaserMigrateEncryptionOfKeyPart();
				encryptedKeyPartWithNewCertificate.add(i, encrypt.encryptKeypart(strDecryptedData,selectedCertificate));
				isMigrated.add(i, "M");
				i++;
			}catch(Exception e){
				System.out.println(e.getMessage());
				encryptedKeyPartWithNewCertificate.add(i, encryptedTechKeyPart.get(i));
				if("initial".equalsIgnoreCase(isMigrated.get(i))){
					isMigrated.add(i, "E");
					indexOfRfxNotDecrypted[i] = 1;
					totalNoOfResponsesNotDecrypted++;
				}
				i++;
			}
		}
		sendTechDataToJSObject(encryptedKeyPartWithNewCertificate);
	}
	
	
	
	
	
	
	//This method sends the technical data to Java script object...
	private void sendTechDataToJSObject(
			ArrayList<String> encryptedKeyPartWithNewCertificate) {
		// TODO Auto-generated method stub
		if(object != null && objectMainForm != null ) {
			int i=0;
			JSObject decKey =null;
			for(;i<encryptedKeyPartWithNewCertificate.size();i++){
				decKey = (JSObject)objectMainForm.getMember("ENCRYPTEDTECHKEY["+i+"]");
				decKey.setMember("value", encryptedKeyPartWithNewCertificate.get(i));
			}
		}
	}

	private boolean validateCertificate(String certificate) {
		// TODO Auto-generated method stub
		PurchaserMigrateGeneralUtilities genutil = new PurchaserMigrateGeneralUtilities();
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		
		byte[] certificateInByte = base64.base64Decode(certificate);
		X509Certificate cert = (X509Certificate) genutil.getCertificateObject(certificateInByte);
		if(cert.getSerialNumber().intValue() == oldSelectedCertificate.getSerialNumber().intValue()){
			return true;
		}
		return false;
	}

	
	
	private void processDecryptionOnKeyPart(ArrayList<String> encryptedKeyPart,ArrayList<String> QTE) {
		// TODO Auto-generated method stub
		PurchaserMigrateGeneralUtilities genutil = new PurchaserMigrateGeneralUtilities();
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		ArrayList<String> encryptedKeyPartWithNewCertificate = new ArrayList<String>(); 
		
		int i = 0 ;
		byte[] keyPartInByteArray  = null;
		
		while(i < encryptedKeyPart.size()){
System.out.println("*********ENC PRICE KEY is:"+encryptedKeyPart.get(i));
			if(indexOfRfxNotDecrypted[i] == 1){
				encryptedKeyPartWithNewCertificate.add(i, encryptedKeyPart.get(i));
				isMigrated.add(i, "E");
				i++;
				continue;
			}
			try{
				keyPartInByteArray = base64.base64Decode(encryptedKeyPart.get(i));
				PurchaserMigrateDecryptionOfKeyPart decrypt = new PurchaserMigrateDecryptionOfKeyPart();
				String strDecryptedData = decrypt.decryptKeyPart(oldSelectedCertificate, keyPartInByteArray);
				PurchaserMigrateEncryptionOfKeyPart encrypt = new PurchaserMigrateEncryptionOfKeyPart();
				encryptedKeyPartWithNewCertificate.add(i, encrypt.encryptKeypart(strDecryptedData,selectedCertificate));
				if(!"".equalsIgnoreCase(isMigrated.get(i))){
					isMigrated.add(i, "M");
				}
				i++;
			}catch(Exception e){
				System.out.println(e.getMessage());
				encryptedKeyPartWithNewCertificate.add(i, encryptedKeyPart.get(i));
				isMigrated.add(i, "E");
				i++;
				totalNoOfResponsesNotDecrypted++;
			}
		}
		
		String certificate =  base64.base64Encode(genutil.getByteArrayObject(selectedCertificate));
		sendEncryptedKeyPartToJSObject(encryptedKeyPartWithNewCertificate,QTE,certificate);
		
	}
	
	

	private void sendEncryptedKeyPartToJSObject(
			ArrayList<String> encryptedKeyPartWithNewCertificate,
			ArrayList<String> qTE, String certificate) {
		// TODO Auto-generated method stub
		if(totalNoOfResponsesNotDecrypted != 0){
			//JOptionPane.showMessageDialog(this, "Number of responses that could not be decrypted is "+totalNoOfResponsesNotDecrypted+" !", "Warning !", 2);
			int value = JOptionPane.showConfirmDialog(this, "Number of responses that could not be decrypted is "
			+totalNoOfResponsesNotDecrypted+" ! \n Press OK to Migrate and CANCEL to return !", "Warning !", 2);
			if(value == -1 || value == 2){
				totalNoOfResponsesNotDecrypted = 0;
				return;
			}
		}
		if(object != null && objectMainForm != null ) {
			int i=0;
			JSObject decKey =null;
			JSObject qte = null;
			JSObject isMigratedFlag = null;
			for(;i<encryptedKeyPartWithNewCertificate.size();i++){
				decKey = (JSObject)objectMainForm.getMember("ENCRYPTEDKEY["+i+"]");
				decKey.setMember("value", encryptedKeyPartWithNewCertificate.get(i));
				qte = (JSObject)objectMainForm.getMember("QTEGUID["+i+"]");
				qte.setMember("value", qTE.get(i));
				
				isMigratedFlag = (JSObject)objectMainForm.getMember("isMigrated["+i+"]");
				isMigratedFlag.setMember("value", isMigrated.get(i));
				
			}
			//Send the certificate to html....
			setMembersIntoMainForm(selectedCertificate,certificate);
		}
	}

	
	
	
	//This method sets the members of the mainform on BSP page...
	private void setMembersIntoMainForm(X509Certificate selcertificate,String enc) {
		// TODO Auto-generated method stub
		if(object !=null && objectMainForm != null){
			try{
				loggerSystem.info("--- : Setting the fields of the HTML mainform. since the mode is upload, public certificate, serial number and subject dn will be sent. !: ---");
				//the following lines of codes will set the attributes of the input field of the form and submits the form to set it into the ABAP....
				PurchaserBase64EncodeDecode encdec = new PurchaserBase64EncodeDecode();
				JSObject PublicCertificate = (JSObject)objectMainForm.getMember("PublicCertificate");
				PublicCertificate.setMember("value", enc);
				JSObject SerialNumber = (JSObject)objectMainForm.getMember("SerialNumber");
				SerialNumber.setMember("value", selcertificate.getSerialNumber().toString());
				JSObject SubjectDN = (JSObject)objectMainForm.getMember("SubjectDN");
				SubjectDN.setMember("value", selcertificate.getSubjectX500Principal());		//method name changed
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			    String DateFormatted = formatter.format(selcertificate.getNotAfter());    
				
				
				JSObject ExpiryDate = (JSObject)objectMainForm.getMember("ExpiryDate");
				ExpiryDate.setMember("value", DateFormatted);
				
				
				JSObject issuerDN = (JSObject)objectMainForm.getMember("IssuerDN");
				issuerDN.setMember("value", selcertificate.getIssuerX500Principal().toString());	//method name changed

				loggerSystem.info("--- : Exiting from the application : ---");
				object.call("submitForm", null);
			}catch(JSException jsexc){
				JOptionPane.showMessageDialog(null, "Fatal error. Please try again !", "Warning", 1);
				System.out.println(jsexc.getMessage());
			}catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		}
	}
	
	public void sendJSObjectToPurchaser(JSObject object2) {
		// TODO Auto-generated method stub
		try{
			object = object2;
		}catch (JSException jsexc){
			System.out.println(jsexc.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
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
	public void sendAppletObject(MainApplet object4){
		try{
			AppletObject = object4;
		}catch (JSException jsexc){
			System.out.println(jsexc.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
}
