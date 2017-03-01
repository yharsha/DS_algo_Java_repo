package com.sap.india.srm.purchaserdecryptbid;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import com.sap.india.srm.main.MainApplet;


/*
 *This class is responsible for overall assembling the decrypted key part of the purchasers and decrypting all the data. The class only shows UI of the purchaser decrypt my key operation that is
 *initiated by the respective purchaser who has logged in. The UI wont be shown for the operation decrypt all bid as there wont be anything to see in it. The class ceases to exist after the controll
 *gets transferred back to the calling BSP page. 
 */

public class PurchaserDecAssembleBrokenKey extends MainApplet implements ActionListener,ListSelectionListener{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Defining constants...
	public static String PURCHASERDECRYPTMYKEY="purchaserdecryptmykey";
	public static String PURCHASERDECRYPTALLBID="purchaserdecryptallbid";
	public static String PURCHASERDECRYPTMYTECHKEY = "PurchaserdecryptmyTechkey";
	
	
	//Defining the UI elements for applet....
	JPanel content;
	JButton decryptMyKey;
	JButton cancel;
	JButton decryptAllBid;
	JTable table;
	JScrollPane scrollPane;
	
	//list of certificates that will be shown on the table..
	ArrayList<X509Certificate> certificates;
	X509Certificate selectedCertificate;
	
	//Defining the Java script objects...
	public static JSObject object;
	public static JSObject objectMainForm;
	public static MainApplet AppletObject;
	
	public PurchaserDecAssembleBrokenKey(){
		
	}
	
	public void startPurchaserOperations(){
		init();
	}
	
	//Initializing the UI components for the applet...
	public void init(){
		content = new JPanel();
		content.setLayout(null);
		decryptMyKey = new JButton("Decrypt My Key");
		cancel = new JButton("Cancel");
		decryptAllBid = new JButton("Decrypt All Bid");
		if(PURCHASERDECRYPTMYKEY.equalsIgnoreCase(MainApplet.mode) || PURCHASERDECRYPTMYTECHKEY.equalsIgnoreCase(MainApplet.mode)){
			
			
			 JLabel coloredLabel = new JLabel("Please select an appropriate certificate", JLabel.CENTER);
			 coloredLabel.setBounds(0, 0, 800, 20);
			 content.add(coloredLabel);
			
			PurchaserDecListAllCertificates listCertificates = new PurchaserDecListAllCertificates();
			Object[][] rows = new Object[listCertificates.certificateSize][6];
			rows=listCertificates.getAllCertificatesFromMSStore();
			certificates = listCertificates.getAllCertificateObjects();
			if(certificates == null){
				JOptionPane.showMessageDialog(null, "No certificates found !", "Error !!!", 1);
				return ;
			}
			
			Object columns[] = { "Issued To", "Version", "Email","Issued By","Purpose","Valid Till"  };
			table = new JTable(rows, columns){
					private static final long serialVersionUID = 1L;
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
			 scrollPane.setBounds(20, 20, 600, 145);
			 content.add(scrollPane);
			
			 decryptMyKey.setBounds(20, 190, 150, 40);
			 decryptMyKey.addActionListener(this);
			 content.add(decryptMyKey);
			 decryptMyKey.setEnabled(false);
			 
			 cancel.setBounds(190, 190, 150, 40);
			 cancel.addActionListener(this);
			 content.add(cancel);
			 
			 this.add(content);
			 this.setSize(700, 700);
			 this.setVisible(true);
			 Color col = new Color(169, 208, 245, 100);
			 content.setBackground(col);
		} else {
			processDecryptAllBid();
		}
	}
	
	
	//This method gets triggered when any button like decrypt my key or cancel is clicked....
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == decryptMyKey){
			int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("noOfPurchasers"));
			//if(noOfPurchasers > 1){
			processDecryptMyKeyForMultiplePurchasers();
			//}else{
			//	processDecryptMyKey();
			//}
		}
		else if(e.getSource() == cancel){
			JOptionPane.showMessageDialog(null, " You have cancelled the operation ! ", "Information !", 1);
			sendCancelledOperationFlagToBSP();
		}
	
	}

	
	//The following method will process the decryption of individual key part of the purchasers...
	private void processDecryptMyKeyForMultiplePurchasers() {
		// TODO Auto-generated method stub
		String publicCertificate = AppletObject.getParameter("publicCertificate");
		PurchaserDecDecryptKey decKey = new PurchaserDecDecryptKey();
		int noOfBidders = Integer.parseInt(AppletObject.getParameter("noOfBidders"));
		List<String> decryptedBrokenKeyPart = new ArrayList<String>();
		List<String> qteList = new ArrayList<String>();
		
		if(isValidSelectedCertificateWithKeyStore(publicCertificate)){
			try {
				for(int i=0;i<noOfBidders;i++){
					String brokenKeyFromApplet = AppletObject.getParameter("brokenKeyPart["+i+"]");
					//String decbrokenKey = decKey.decryptMyKeyUnWrap(brokenKeyFromApplet, publicCertificate);
					String decbrokenKey = decKey.decryptMyKey(brokenKeyFromApplet, publicCertificate);
					String qteValue = AppletObject.getParameter("bidQte["+i+"]");
					qteList.add(i,qteValue);
					decryptedBrokenKeyPart.add(i, decbrokenKey);
				}
				sendKeyToJSObjectMainForm(decryptedBrokenKeyPart,qteList);
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " Decryption unsuccessful. Please try again ! ", "Error !", 1);
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, " Decryption unsuccessful. Please try again ! ", "Error !", 1);
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, " Please choose a valid certificate ! ", "Information !", 1);
		}
	}

	
	//Process the decryption of the data once individual key part is decrypted...
	private void processDecryptAllBid() {
		// TODO Auto-generated method stub
		
		ArrayList<String> decryptedArrayList = new ArrayList<String>();
		ArrayList<String> decryptedQTE = new ArrayList<String>();
		/*String mergedBrokenKey = AppletObject.getParameter("assembledKey");
	
		PurchaserBase64Utilities base64 = new PurchaserBase64Utilities();
		byte[] assembledKey = base64.base64Decode(mergedBrokenKey);*/
		
		int noOfbidders = Integer.parseInt(AppletObject.getParameter("noOfBidders"));
		for(int i=0;i<noOfbidders;i++){
			decryptedQTE.add(i,AppletObject.getParameter("RFXQTE["+i+"]"));
			byte[] aesKeyInByteArray = processAssemblingBrokenKey(i);
			//System.out.println("The assembled key is "+new String(aesKeyInByteArray));
			String inputData = AppletObject.getParameter("QTEEncrypteddata["+i+"]");
			
			PurchaserDecDecryptBid decryptbid = new PurchaserDecDecryptBid();
			try {
				String decryptedData = decryptbid.decryptAllBid(aesKeyInByteArray, inputData);
				decryptedArrayList.add(i, decryptedData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				decryptedArrayList.add(i, "");
				e.printStackTrace();
			}
		}
		sendDataToJSObjectMainForm(decryptedArrayList,decryptedQTE);
		
	}

	
	
	//setting the decrypted data into the Java script object...
	private void sendDataToJSObjectMainForm(ArrayList<String> decryptedData,ArrayList<String> decryptedQTE) {
		// TODO Auto-generated method stub
		if(object != null && objectMainForm != null ) {
			if(decryptedData != null){
				for(int i=0;i<decryptedData.size();i++){
					JSObject decdata = (JSObject)objectMainForm.getMember("QTEDecryptedData["+i+"]");
					decdata.setMember("value", decryptedData.get(i));
					JSObject decqte = (JSObject)objectMainForm.getMember("DECRYPTEDQTE["+i+"]");
					decqte.setMember("value", decryptedQTE.get(i));
				}
				object.call("submitForm", null);
			}
		}
	}

	
	private void sendDataToJSObjectMainFormWithBatchUpdate(List<String> decryptedData){
		if(object !=null && objectMainForm != null){
			if(decryptedData !=null){
				for(int i=0;i<decryptedData.size();i++){
					JSObject decdata = (JSObject)objectMainForm.getMember("QTEDecryptedData["+i+"]");
					decdata.setMember("value", decryptedData.get(i));
					object.call("submitForm", null);
				}
			}
		}
	}
	
	private void SendDataToJSObjectMainFormWithBatchUpdateToBSPPage(){
		if(object != null && objectMainForm != null){
			JSObject decData = (JSObject ) objectMainForm.getMember("GetEncryptedData");
			decData.setMember("value", "HELLOWORLD!");
		}
	}
	
	
	
	//validate serial no of the certificate selected and the certificate retrieved from the data base...
	private boolean isValidSelectedCertificateWithKeyStore(String publicCertificate) {
		// TODO Auto-generated method stub
		PurchaserBase64Utilities base64 = new PurchaserBase64Utilities();
		byte[] publicCertificateInByteArray = base64.base64Decode(publicCertificate);
		PurchaserDecGeneralUtilities genutil = new PurchaserDecGeneralUtilities();
		X509Certificate certificate = (X509Certificate) genutil.getCertificateObject(publicCertificateInByteArray);
		boolean isValid=false;
		
		if(selectedCertificate.getSerialNumber().intValue() == certificate.getSerialNumber().intValue()){
			isValid = true;
		}
		else{
			isValid = false;
		}
		return isValid;
	}

	

	//Assembles the broken key and prepare whole key for final decryption...
	private byte[] processAssemblingBrokenKey(int indexOfQTE) {
		
		// TODO Auto-generated method stub
		int i;
		int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("NoOfPurchasers"));
		PurchaserBase64Utilities base64 = new PurchaserBase64Utilities();
		byte[] aesKey = null;
		byte[] prevAesKey = null;
		int prevLength = 0;
		for(i=0;i<noOfPurchasers;i++){
			byte[] KeyPart = base64.base64Decode(AppletObject.getParameter("QTE["+indexOfQTE+"]purchaserkeypart["+i+"]"));
			prevAesKey = new byte[prevLength];
			if(i!=0){
				System.arraycopy(aesKey, 0, prevAesKey, 0, prevLength);
			}
			aesKey = new byte[prevLength + KeyPart.length];
			System.arraycopy(prevAesKey, 0, aesKey, 0, prevLength);
			System.arraycopy(KeyPart, 0, aesKey, prevLength, KeyPart.length);
			prevLength = aesKey.length;
		}
		return aesKey;
	}

	private void sendKeyToJSObjectMainForm(List<String> decbrokenKey,List<String> qteList) {
		// TODO Auto-generated method stub
		if(object != null && objectMainForm != null ) {
			int i=0;
			JSObject decKey =null;
			JSObject qte = null;
			for(;i<decbrokenKey.size();i++){
				decKey = (JSObject)objectMainForm.getMember("decryptBrokenKeyPart["+i+"]");
				decKey.setMember("value", decbrokenKey.get(i));
				qte = (JSObject)objectMainForm.getMember("Qte["+i+"]");
				qte.setMember("value", qteList.get(i));
			}
			object.call("submitForm", null);
		}
	}

	public void sendJSObjectToPurchaser(JSObject object2) {
		// TODO Auto-generated method stub
		try{
			object = object2;
		}catch(JSException jsexc){
			jsexc.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendMainFormToPurchaser(JSObject object3) {
		try{
			objectMainForm = object3;
		}catch(JSException jsexc){
			jsexc.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendAppletObject(MainApplet object4){
		try{
			AppletObject = object4;
		}catch(JSException jsexc){
			jsexc.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == table.getSelectionModel()){
			selectedCertificate = certificates.get(table.getSelectedRow());
			if(PURCHASERDECRYPTMYKEY.equalsIgnoreCase(MainApplet.mode)||PURCHASERDECRYPTMYTECHKEY.equalsIgnoreCase(MainApplet.mode)){
				decryptMyKey.setEnabled(true);
			}
		}
	}
	
	
	//When the cancel button is clicked, the purchaser gets redirected to the calling applet without completing any operation....
	
	public void sendCancelledOperationFlagToBSP(){
		if(object != null && objectMainForm != null ) {
			JSObject cancelOperation = (JSObject) objectMainForm.getMember("ACTION");
			cancelOperation.setMember("value", "CANCEL");
			object.call("CancelOperation",null);
		}
	}
}
