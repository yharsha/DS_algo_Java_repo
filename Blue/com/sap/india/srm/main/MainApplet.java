package com.sap.india.srm.main;



import java.util.logging.Logger;

import javax.swing.JApplet;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import com.sap.india.srm.purchaserdecryptbid.*;
import com.sap.india.srm.purchasermigratecertificate.*;
import com.sap.india.srm.purchaseruploadcertificates.*;
import com.sap.india.srm.vendordataencryption.*;



/*
 * @ This is the main class where all the request will come. (i.e. uploading certificate, migrating certificate, vendor side encryption decryption, purchaser side encryption decryption). This class will serve
 * all the services required for encryption and decryption operations. It contains few constants which is encouraged as java standard programming and will be used throughout the class existence. This applet class
 * can be extended to any of the operations UI that will help the metaphor of polymorphism and can be reused. This class calls respective classes required to accomplish the requested operations by the user along 
 * with javascript object and this class object which can be useful in taking decisions. Only java script exceptions are handled here and the rest are handled in respective classes where the operations are done.
 */



@SuppressWarnings("serial")
public class MainApplet extends JApplet{
	
	
	//Define constants...
	
	public static String UPLOAD = "upload";
	public static String VENDORENCRYPTBID = "vendorencryptbid";
	public static String FOREIGNVENDORENCRYPTBID = "foreignvendorencryptbid";
	public static String VENDORDECRYPTBID = "vendordecryptbid";
	public static String PURCHASERDECRYPTMYKEY = "purchaserdecryptmykey";
	public static String PURCHASERDECRYPTALLBID = "purchaserdecryptallbid";
	public static String MIGRATE = "migrate";
	public static String VENDORENCRYPTDECRYPTBIDEDITMODE = "vendorencryptdecrypeditmode";
	public static String MIGRATEWITHSOLUTION = "migrationmode";
	public static String PURCHASERDECRYPTMYTECHKEY = "PurchaserdecryptmyTechkey";
	public static String PURCHASERDECRYPTALLTECHBID = "PurchaserDecryptAllTechBid";
	
	
	//Define objects of operations like upload,encrypt and migrate..
	PurchaserUploadCertificate purchaserUploadCertificate;
	PurchaserDecAssembleBrokenKey decbid; 
	VendorDesign vendordecdata;
	PurchaserMigrateCertificate purchaserMigrateCertificate;
	
	//Mode of the operation...
	public static String mode = "";
	
	//logger object that logs the process...
	public static Logger loggerSystem=null;
	
	public MainApplet() {
	
		loggerSystem = Logger.getLogger("log_file");
	}
	
	//This is the first method that gets executed when the applet gets loaded...
	public void init(){
		
	}
	
	
	public void start(){
		
		//get the mode from applet parameter and pass it wherever it is required....
		mode = this.getParameter("mode");
		
	
		//case 1 : When the mode is upload .... following if block will be executed....
		if(UPLOAD.equalsIgnoreCase(mode)){
			purchaserUploadCertificate = new PurchaserUploadCertificate();
			this.add(purchaserUploadCertificate);
			this.setSize(800,800);
			try{
				//Get Java script object and pass them to purchaser upload certificate.
				JSObject object = (JSObject)JSObject.getWindow(this);
				JSObject mainform = (JSObject) object.eval("document.mainForm");
				purchaserUploadCertificate.sendJSObjectToPurchaser(object);
				purchaserUploadCertificate.sendMainFormToPurchaser(mainform);
			}catch(JSException jsexc){
				jsexc.printStackTrace();
			}
		}
		
		//case 2 : When the mode is vendor encrypt/decrypt.. following else if block will be executed....
		else if(VENDORENCRYPTBID.equalsIgnoreCase(mode) || VENDORDECRYPTBID.equalsIgnoreCase(mode) || VENDORENCRYPTDECRYPTBIDEDITMODE.equalsIgnoreCase(mode) ||FOREIGNVENDORENCRYPTBID.equalsIgnoreCase(mode)){
			vendordecdata = new VendorDesign();
			this.add(vendordecdata);
			this.setSize(800, 800);
			try{
				//Get Java script object and pass them to vendor design class.
				JSObject object2 = (JSObject)JSObject.getWindow(this);
				JSObject mainform = (JSObject) object2.eval("document.mainForm");
				vendordecdata.sendJSObjectToPurchaser(object2);
				vendordecdata.sendMainFormToPurchaser(mainform);
				vendordecdata.sendAppletObject(this);
				vendordecdata.startVendorOperations();
			}catch(JSException jsexc){
				jsexc.printStackTrace();
			}
		}
		


		//Case 3 : when the mode is purchaser decrypt ... following else if block will be executed.. for decrypt key and decrypt bid ...
		else if(PURCHASERDECRYPTMYKEY.equalsIgnoreCase(mode) || PURCHASERDECRYPTALLBID.equalsIgnoreCase(mode) || PURCHASERDECRYPTMYTECHKEY.equalsIgnoreCase(mode) || PURCHASERDECRYPTALLTECHBID.equalsIgnoreCase(mode)){
			decbid = new PurchaserDecAssembleBrokenKey();
			try{
				//Get Java script object and pass them to PurchaserDecAssembleBrokenKey...
				JSObject object2 = (JSObject)JSObject.getWindow(this);
				JSObject mainform = (JSObject) object2.eval("document.mainForm");
				decbid.sendJSObjectToPurchaser(object2);
				decbid.sendMainFormToPurchaser(mainform);
				decbid.sendAppletObject(this);
				decbid.startPurchaserOperations();
				this.add(decbid);
				this.setSize(800, 800);
			}catch(JSException jsexc){
				jsexc.printStackTrace();
			}
		}
		
		//Case 4 : when the mode is purchaser Migrate ... following else if block will be executed.. for migrating the certificate......
		else if(MIGRATE.equalsIgnoreCase(mode)){
			purchaserMigrateCertificate = new PurchaserMigrateCertificate();
			this.add(purchaserMigrateCertificate);
			this.setSize(800,800);
			try{
				//Get Java script object and pass them to purchaser upload certificate.
				JSObject object = (JSObject)JSObject.getWindow(this);
				JSObject mainform = (JSObject) object.eval("document.mainForm");
				purchaserMigrateCertificate.sendJSObjectToPurchaser(object);
				purchaserMigrateCertificate.sendMainFormToPurchaser(mainform);
				purchaserMigrateCertificate.sendAppletObject(this);
				purchaserMigrateCertificate.startPurchaserMigrateOperation();
			}catch(JSException jsexc){
				jsexc.printStackTrace();
			}
		}
	}
		
}
