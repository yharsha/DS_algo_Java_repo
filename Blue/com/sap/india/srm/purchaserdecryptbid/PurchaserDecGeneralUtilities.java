package com.sap.india.srm.purchaserdecryptbid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class PurchaserDecGeneralUtilities {

	public byte[] getByteArrayObject(X509Certificate cert){
		byte[] byteArrayObject = null;
        try {
           
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(cert);
           
            oos.close();
            bos.close();
            byteArrayObject = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return byteArrayObject;
        }
        return byteArrayObject;
	}
	
	 
	public Certificate getCertificateObject(byte[] byteArrayObject){
		X509Certificate cert = null;
		//It requires byte array input stream to get the certificate object....
        ByteArrayInputStream bais;
        ObjectInputStream ins;
        try {
	        // Byte array input stream will hold the byte array object and put into input stream buffer....
	        bais = new ByteArrayInputStream(byteArrayObject);
	       
	        ins = new ObjectInputStream(bais);
	        cert =(X509Certificate) ins.readObject();
	       
	        ins.close();
	        
	        return cert;

        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return cert;
	}
	
	public Key getKeyObject (byte[] byteArrayObject){
		Key key = null;
		ByteArrayInputStream bais;
        ObjectInputStream ins;
        try {
 	       
	        bais = new ByteArrayInputStream(byteArrayObject);
	       
	        ins = new ObjectInputStream(bais);
	        key =(Key) ins.readObject();
	       
	        ins.close();
	        
	      //  CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	       // InputStream inputStream = new ByteArrayInputStream(byteArrayObject);
	       // Certificate certi =  certFactory.generateCertificate(inputStream);
	        return key;

        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return key;
	}
	public byte[] getByteArrayObjectOfKey(Key key){
		byte[] byteArrayObject = null;
        try {
           
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(key);
           
            oos.close();
            bos.close();
            byteArrayObject = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return byteArrayObject;
        }
        return byteArrayObject;
	}
}
