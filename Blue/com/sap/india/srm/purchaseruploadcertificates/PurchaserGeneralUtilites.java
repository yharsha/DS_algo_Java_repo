package com.sap.india.srm.purchaseruploadcertificates;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class PurchaserGeneralUtilites{
 
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
		        
        ByteArrayInputStream bais;
        ObjectInputStream ins;
        try {
	       
	        bais = new ByteArrayInputStream(byteArrayObject);
	       
	        ins = new ObjectInputStream(bais);
	        cert =(X509Certificate) ins.readObject();
	       
	        ins.close();
	        
	      //  CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	       // InputStream inputStream = new ByteArrayInputStream(byteArrayObject);
	       // Certificate certi =  certFactory.generateCertificate(inputStream);
	        return cert;

        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return cert;
	}
}
