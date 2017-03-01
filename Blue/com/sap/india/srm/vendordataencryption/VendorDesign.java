package com.sap.india.srm.vendordataencryption;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.SAXParserFactory;



import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import com.sap.india.srm.main.MainApplet;


public class VendorDesign extends MainApplet implements ActionListener,ListSelectionListener{

	/*
	 * @ This class is solely responsible for encrypting the data given by the applet parameter
	 * This class is the main encryption body that will take applet parameter input data and encrypt it. It will return encrypted data, encrypted symmetric key and broken key generated
	 * by the no of purchasers participating in this bid. The class also calls different apis that will assist to accomplish the respective operations..
	 */
	 
	/*
	 * Static serial no with serial version uid... It Does nothing in this class...
	 */
	private static final long serialVersionUID = 1L;
	
	//Define constants
	public static String VENDORENCRYPTBID = "vendorencryptbid";
	public static String VENDORDECRYPTBID = "vendordecryptbid";
	public static String VENDORENCRYPTDECRYPTBIDEDITMODE = "vendorencryptdecryptbideditmode";
	public static String FOREIGNVENDORENCRYPTBID = "foreignvendorencryptbid";
	
	public ArrayList<String> recoverBrokenKey;
	
	//Define the UI elements of the applet.....
	JPanel content;
	JButton encryptbid;
	JButton decryptbid;
	JButton migrate;
	JButton cancel;
	JTable table;
	JScrollPane scrollPane;
	
	//Lists all certificate...
	ArrayList<X509Certificate> certificates;
	X509Certificate selectedCertificate;
	
	//Define Java script objects...
	public static JSObject object;
	public static JSObject objectMainForm;
	
	//Mainapplet object...
	public static MainApplet AppletObject;
	public boolean isTechDataPresent = false;
	public String rows[][];
	public boolean isAllowedToEncrypt = false;

	public VendorDesign(){

	}
	
	public void startVendorOperations(){
		init();
	}
	
	
	//This method initializes UI elements of the applet....
	public void init(){
		content = new JPanel();
		content.setLayout(null);
		
		if(VENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode) || VENDORDECRYPTBID.equalsIgnoreCase(MainApplet.mode) || VENDORENCRYPTDECRYPTBIDEDITMODE.equalsIgnoreCase(MainApplet.mode) ){		
		
		JLabel coloredLabel = new JLabel("Please select an appropriate certificate", JLabel.CENTER);
		coloredLabel.setBounds(10, 10, 800, 20);
		content.add(coloredLabel);
		
		VendorListAllCertificates  vendorlist = new VendorListAllCertificates();
		//rows[][] = new Object[vendorlist.certificateSize][6];
		rows=vendorlist.getAllCertificatesFromMSStore();
		certificates = vendorlist.getAllCertificateObjects();
		if(certificates == null){
			JOptionPane.showMessageDialog(null, "No certificates found !", "Error !!!", 1);
			return ;
		}
		String columns[] = { "Issued To", "Version", "Email","Issued By","Purpose","Valid Till"  };
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
		 scrollPane.setBounds(30, 30, 800, 145);
		 content.add(scrollPane);
		
		encryptbid = new JButton("Encrypt Bid");
		cancel = new JButton("Cancel");
		decryptbid = new JButton("Decrypt Bid");
		
		cancel.setBounds(460, 190, 100, 40);
		cancel.addActionListener(this);
		content.add(cancel);
		   
		encryptbid.setBounds(330, 190, 130, 40);
		encryptbid.addActionListener(this);
		content.add(encryptbid);
		   
		decryptbid.setBounds(330, 190, 100, 40);
		decryptbid.addActionListener(this);
		content.add(decryptbid);
		
		if(VENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode)){
			decryptbid.setVisible(false);
			encryptbid.setEnabled(true);
		}
		else if(VENDORDECRYPTBID.equalsIgnoreCase(MainApplet.mode)){
			decryptbid.setEnabled(false);
			encryptbid.setVisible(false);
		}
		else{
			encryptbid.setVisible(false);
			decryptbid.setEnabled(false);
		}
		
		this.add(content);
		this.setSize(700, 700);
		this.setVisible(true);
		Color col = new Color(169, 208, 245, 100);
		content.setBackground(col);
	}
		
// will come to else if mode is foreign vendor encrypt bid.... so direct encryption is needed from purchaser certificate without requiring any bidder side certificate		
		else{
			processEncryptionOnData();
		    }	
  
}
	
	
	//Whenever any row gets selected, the following method gets triggered....
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == table.getSelectionModel()){
			selectedCertificate = certificates.get(table.getSelectedRow());
			int rowIndex = table.getSelectedRow();
			if("Digital sign/Encryption".equalsIgnoreCase(rows[rowIndex][4]) || "Encryption".equalsIgnoreCase(rows[rowIndex][4])){
				isAllowedToEncrypt = true;
			}
			if(VENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode) ){
				encryptbid.setEnabled(true);
			}
			else if(VENDORDECRYPTBID.equalsIgnoreCase(MainApplet.mode)){
				decryptbid.setEnabled(true);
			}
		}
	}

	//Whenever any button i.e. "encrypt" or "decrypt" or "cancel" is pressed, the following method gets triggered...
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == encryptbid){
			if(isAllowedToEncrypt){
				processEncryptionOnData();
			}else{
				JOptionPane.showMessageDialog(null, " Select the certificate that allows encryption!  ", "Information !", 1);
			}
		}
		else if(event.getSource() == decryptbid){
			String techKeyPresent = AppletObject.getParameter("isTechSymmetricKeyPresent");
			if("1".equalsIgnoreCase(techKeyPresent)){
				processDecryptionOnTechData();
			}
			processDecryptionOnData();
		}
		else if(event.getSource() == cancel){
			JOptionPane.showMessageDialog(null, " You have cancelled the operation ! ", "Information !", 1);
			sendCancelledOperationFlagToBSP();
		}
	}
	
	
	//Process decryption of technical data..............
	private void processDecryptionOnTechData() {
		// TODO Auto-generated method stub
		try{
			//Get all the applet parameters....
			String encryptedData = AppletObject.getParameter("DecryptEncryptedTechData");
			String encryptedSymmetricKey = AppletObject.getParameter("VendorAssymTechKey");
			
			
			//Decrypt the encrypted symmetric key...
			VendorEncryptDecryptKey vendorencdecsymkey = new VendorEncryptDecryptKey();
			Key symmetricKey = vendorencdecsymkey.decryptSymmetricKey(selectedCertificate, encryptedSymmetricKey);
			
			//Decrypt the data using the decrypted key above...
			VendorEncryptDecryptData vendorencdecdata = new VendorEncryptDecryptData();
			String decryptedData = vendorencdecdata.decryptData(symmetricKey, encryptedData);
			
			
			//Set the decrypted data into main form using JS object...
			setMembersOfMainFormAfterDecryptionOfTechData(decryptedData);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	//Setting the decrypted technical data into mainform..
	private void setMembersOfMainFormAfterDecryptionOfTechData(
			String decryptedData) {
		// TODO Auto-generated method stub
		if(object != null && objectMainForm != null){
			JSObject decData = (JSObject)objectMainForm.getMember("DecryptedOriginalTechData");
			decData.setMember("value", decryptedData);
		}
	}


	//Process decryption of data...............
	private void processDecryptionOnData() {
		// TODO Auto-generated method stub
		try{
			//Get all the applet parameters....
			String encryptedData = AppletObject.getParameter("DecryptEncryptedData");
			String encryptedSymmetricKey = AppletObject.getParameter("VendorAssymKey");
			
			//Decrypt the encrypted symmetric key...
			VendorEncryptDecryptKey vendorencdecsymkey = new VendorEncryptDecryptKey();
			Key symmetricKey = vendorencdecsymkey.decryptSymmetricKey(selectedCertificate, encryptedSymmetricKey);
			
			//Decrypt the data using the decrypted key above...
			VendorEncryptDecryptData vendorencdecdata = new VendorEncryptDecryptData();
			String decryptedData = vendorencdecdata.decryptData(symmetricKey, encryptedData);
			
			
			//Set the decrypted data into main form using JS object...
			setMembersOfMainFormAfterDecryption(decryptedData);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, " Select a suitable certificate!", "Operation Unsuccessful !", 1);
			System.out.println(e.getMessage());
		}
	}

	
	//setting the original price data into the mainform.. 
	private void setMembersOfMainFormAfterDecryption(String decryptedData) {
		// TODO Auto-generated method stub
		if(object != null && objectMainForm != null){
			JSObject decData = (JSObject)objectMainForm.getMember("DecryptedOriginalData");
			decData.setMember("value", decryptedData);
			//JOptionPane.showMessageDialog(null, " Decryption successful!", "Successfull !", 1);
			object.call("submitForm", null);
		}
	}


	//Process encryption of data......
	private void processEncryptionOnData() {
		// TODO Auto-generated method stub
		try{
System.out.println("f1");
			String encryptedSymmetricKey="";
			//get the data to be encrypted from HTML form....
System.out.println("f2");
			String inputData = AppletObject.getParameter("OriginalData");
System.out.println("f3");			
System.out.println("input data="+inputData);
System.out.println("here1");
			Map<Key, String> mapOfDataAndKey = new HashMap<Key,  String>();
			ArrayList<String> brokenKey = null; 
			VendorEncryptDecryptData vendorencdec = new VendorEncryptDecryptData();
			String encryptedData = "";
			String isTechDataPresentString = AppletObject.getParameter("hasTechData");
System.out.println("here2");
			Key key = null;
			if("1".equalsIgnoreCase(isTechDataPresentString)){
				String inputTechData = AppletObject.getParameter("OriginalTechData");
				mapOfDataAndKey = vendorencdec.encryptData(inputTechData);
				
				//Extract key and data from the Map received after encryption....
				Iterator<Key> iterator = mapOfDataAndKey.keySet().iterator();
				while(iterator.hasNext()) {
					key  = iterator.next(); 
					encryptedData = mapOfDataAndKey.get(key); 
				}
				//if mode is not foreign vendor then encrypt the generated symmetric key using bidder side certificate				
				if(!(FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode))){
					
				VendorEncryptDecryptKey encdeckey = new VendorEncryptDecryptKey();
				encryptedSymmetricKey = encdeckey.encryptSymmetricKey(key, selectedCertificate);
				}
				
				//generate the broken key using the purchasers' certificates.....
				
				brokenKey = processGenerateBrokenKey(key);
				setMembersOfMainFormTechData(encryptedData,brokenKey,encryptedSymmetricKey);
			}

			
			//Non technical data encryption runs from the following line...
System.out.println("here3");
System.out.println("input data="+inputData);
			mapOfDataAndKey = vendorencdec.encryptData(inputData);
System.out.println("here6");			
			//Extract key and data from the Map received after encryption....
			Iterator iterator = mapOfDataAndKey.keySet().iterator();
			while(iterator.hasNext()) {
				key  = (Key)iterator.next(); 
				encryptedData = mapOfDataAndKey.get(key); 
			}
			//if mode is not foreign vendor then encrypt the generated symmetric key using bidder side certificate				
			if(!(FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode))){
				
			VendorEncryptDecryptKey encdeckey = new VendorEncryptDecryptKey();
//check private key availability
			if(encdeckey.IsPrivateKeyPresent(selectedCertificate))
				encryptedSymmetricKey = encdeckey.encryptSymmetricKey(key, selectedCertificate);
			else
				throw new InvalidKeyException();
System.out.println("here7");
			
			}
			//generate the broken key using the purchasers' certificates.....
			
			brokenKey = processGenerateBrokenKey(key);
System.out.println("here8");
			//JOptionPane.showMessageDialog(null, "Encryption successful !", "Successfull !", 1);
			//Set the members of the mainForm object in BSP....
			setMembersOfMainFormAfterEncryption(encryptedData,brokenKey,encryptedSymmetricKey,selectedCertificate);
			
		}catch(InvalidKeyException e){
			
			JOptionPane.showMessageDialog(null, "Certificate Key cannot be accessed! Insert token and retry or choose valid certificate.", "Error !!!", 1);
			System.out.println(e.getMessage());
			
		}
		catch(Exception e){
			// if foreign vendor.. then return to response in case of any exception as there is no certificate screen
			if(FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode)){
				JOptionPane.showMessageDialog(null, " Encryption unsuccessful ", "Information !", 1);
				sendCancelledOperationFlagToBSP();
			}
			else{
			// for other vendors... remain on certificate screen in case of exception	
			JOptionPane.showMessageDialog(null, "Encryption unsuccessful. Please try again  !", "Error !!!", 1);
			System.out.println(e.getMessage());
			}
		}
	}
	


	//setting the technical data after encryption....
	private void setMembersOfMainFormTechData(String encryptedData,
			ArrayList<String> brokenKey, String encryptedSymmetricKey) {
		// TODO Auto-generated method stub
		
		
		int i = 0;
		if(object != null && objectMainForm != null){
			int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("noOfPurchasers"));
			for(;i<noOfPurchasers ; i++){
				
				JSObject purchaserKeyparts = (JSObject)objectMainForm.getMember("PrimaryPurchaser["+i+"]TechKeyPart");
				purchaserKeyparts.setMember("value", brokenKey.get(i*3));
				//Secondary purchasers key part.....
				JSObject secondaryPurchaserKeyparts = (JSObject)objectMainForm.getMember("SecondaryPurchaser["+i+"]TechKeyPart");
				secondaryPurchaserKeyparts.setMember("value", brokenKey.get((i*3)+1));
				
				
				//Tertiary purchasers key part......
				JSObject tertiaryPurchaserKeyparts = (JSObject)objectMainForm.getMember("TertiaryPurchaser["+i+"]TechKeyPart");
				tertiaryPurchaserKeyparts.setMember("value", brokenKey.get((i*3) + 2));
				
				//Recovery  brokenkey................
				/*JSObject adminkey = (JSObject)objectMainForm.getMember("admTechKey["+i+"]");
				adminkey.setMember("value", recoverBrokenKey.get(i));*/
				
				
			}
			JSObject encdata = (JSObject)objectMainForm.getMember("EncryptedTechData");
			encdata.setMember("value", encryptedData);
			
			//if mode is not foreign vendor then set the encrypted symmetric key				
			if(!(FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode))){
			JSObject rsaAssym = (JSObject)objectMainForm.getMember("VendorAssymTechKey");
			rsaAssym.setMember("value", encryptedSymmetricKey);
			}	
		}
		
		
	}


	//process generating the broken key using no of purchasers associated with the current RFx document...
	private ArrayList<String> processGenerateBrokenKey(Key key) {
		// TODO Auto-generated method stub
		//Break the key according to no of purchasers associated with current RFx document
		int i = 0;
		//int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("NoOfPurchasers"));
		int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("NoOfPurchasers"));
		ArrayList<String> brokenKeyParts = null;
		VendorGeneralUtilities genutil = new VendorGeneralUtilities();
		X509Certificate[] primarypurchaserscertificates = new X509Certificate[noOfPurchasers];
		X509Certificate[] secondarypurchaserscertificates = new X509Certificate[noOfPurchasers];
		X509Certificate[] tertiarypurchaserscertificates = new X509Certificate[noOfPurchasers];
		
		VendorBase64EncodeDecode base64encdec = new VendorBase64EncodeDecode();
		for(;i<noOfPurchasers;i++){
			//For primary purchasers....
			String primaryPurchaserCertificate = AppletObject.getParameter("PrimaryPurchaser["+i+"]Certificate");
			byte[] primarybytearrayobject = base64encdec.base64Decode(primaryPurchaserCertificate);
			primarypurchaserscertificates[i] = genutil.getCertificateObject(primarybytearrayobject);
			
			//For secondary purchasers....
			String secondaryPurchaserCertificate = AppletObject.getParameter("SecondaryPurchaser["+i+"]Certificate");
			if("0".equalsIgnoreCase(secondaryPurchaserCertificate)){
				secondarypurchaserscertificates[i] = null;
			}else{
				byte[] secondarybytearrayobject = base64encdec.base64Decode(secondaryPurchaserCertificate);
				secondarypurchaserscertificates[i] = genutil.getCertificateObject(secondarybytearrayobject);
			}
			
			//For Tertiary purchasers....
			String tertiaryPurchaserCertificate = AppletObject.getParameter("TertiaryPurchaser["+i+"]Certificate");
			if("0".equalsIgnoreCase(tertiaryPurchaserCertificate)){
				tertiarypurchaserscertificates[i] = null;
			}else{
				byte[] tertiarybytearrayobject = base64encdec.base64Decode(tertiaryPurchaserCertificate);
				tertiarypurchaserscertificates[i] = genutil.getCertificateObject(tertiarybytearrayobject);
			}
		}
		
		
		//Generate broken key for purchasers associated with the RFx document...
		GenerateBrokenKeyForPurchaser genbrokenkey = new GenerateBrokenKeyForPurchaser();
		try {
			brokenKeyParts = genbrokenkey.brokenKeyForPurchaser(key, noOfPurchasers,primarypurchaserscertificates,secondarypurchaserscertificates,tertiarypurchaserscertificates);
			recoverBrokenKey = genbrokenkey.brokenKeyRecovery();
		} catch (Exception e) {
			// TODsO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return brokenKeyParts;
	}


	//set the price data after encryption into main form...
	private void setMembersOfMainFormAfterEncryption(String encryptedData,ArrayList<String> brokenkey,String encryptedSymetricKey,X509Certificate selectedCertificate) {
		// TODO Auto-generated method stub
		/* 
		 * This method sends the encrypted data and key back to HTML page using JSObject...
		 */
		int i = 0;
		
		
		
		if(object != null && objectMainForm != null){
			int noOfPurchasers = Integer.parseInt(AppletObject.getParameter("noOfPurchasers"));
			for(;i<noOfPurchasers ; i++){
				
				JSObject purchaserKeyparts = (JSObject)objectMainForm.getMember("PrimaryPurchaser["+i+"]KeyPart");
				purchaserKeyparts.setMember("value", brokenkey.get(i*3));
				
				//Secondary purchasers key part.....
				JSObject secondaryPurchaserKeyparts = (JSObject)objectMainForm.getMember("SecondaryPurchaser["+i+"]KeyPart");
				secondaryPurchaserKeyparts.setMember("value", brokenkey.get((i*3)+1));
				
				
				//Tertiary purchasers key part......
				JSObject tertiaryPurchaserKeyparts = (JSObject)objectMainForm.getMember("TertiaryPurchaser["+i+"]KeyPart");
				tertiaryPurchaserKeyparts.setMember("value", brokenkey.get((i*3) + 2));
				
				//Recovery  brokenkey................
				/*JSObject adminkey = (JSObject)objectMainForm.getMember("admKey["+i+"]");
				adminkey.setMember("value", recoverBrokenKey.get(i));*/
				
			}
			JSObject encdata = (JSObject)objectMainForm.getMember("EncryptedData");
			encdata.setMember("value", encryptedData);
			
			//if mode is not foreign vendor then set the encrypted symmetric key and selected certificate details				
			if(!(FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(MainApplet.mode))){
				
			JSObject rsaAssym = (JSObject)objectMainForm.getMember("VendorAssymKey");
			rsaAssym.setMember("value", encryptedSymetricKey);
			
			
			JSObject issuerDN = (JSObject)objectMainForm.getMember("VIssuerDN");
			issuerDN.setMember("value", selectedCertificate.getIssuerX500Principal().toString()); // method name changed
			
			JSObject SerialNumber = (JSObject)objectMainForm.getMember("VSerialNumber");
			SerialNumber.setMember("value", selectedCertificate.getSerialNumber().toString());
			}
			
			object.call("submitForm", null);
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
	
	public void sendCancelledOperationFlagToBSP(){
		if(object != null && objectMainForm != null ) {
			JSObject cancelOperation = (JSObject) objectMainForm.getMember("ACTION");
			cancelOperation.setMember("value", "CANCEL");
			object.call("CancelOperation",null);
		}
	}
	
	
}
