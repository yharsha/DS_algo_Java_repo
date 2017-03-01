package com.sap.india.srm.purchaserdecryptbid;

import java.lang.reflect.Field;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;



// This class is responsible for decrypting the individual key part of the currently logged in purchaser...
public class PurchaserDecDecryptKey {

	
	//This method decrypts the key part and returns the string form of the decrypted key....
	public String decryptMyKey(String brokenKey, String publicCertificate) throws Exception, NoSuchPaddingException, NoSuchAlgorithmException{
		
		//This method will decrypt the key part of the respective purchaser..
		PurchaserBase64Utilities base64 = new PurchaserBase64Utilities();
		byte[] brokenKeyPart = base64.base64Decode(brokenKey);
		byte[] publicCertificateInByteArray = base64.base64Decode(publicCertificate);
		
		//Get the certificate object from the string object using general utilities..
		PurchaserDecGeneralUtilities genutil = new PurchaserDecGeneralUtilities();
		X509Certificate certificate = (X509Certificate) genutil.getCertificateObject(publicCertificateInByteArray);
		
		//Instantiate key store to and verify the selected certificate against sent certificate from HTML..
		KeyStore ks = KeyStore.getInstance("WINDOWS-MY");
		ks.load(null, null);
		
		Field field =  ks.getClass().getDeclaredField("keyStoreSpi");
		field.setAccessible(true);
		
		KeyStoreSpi kss = (KeyStoreSpi) field.get(ks);
		
		if("sun.security.mscapi.KeyStore$MY".equals(kss.getClass().getName())) {
			Collection entries;
			String alias, hashCode;
			X509Certificate[] certificates;
			
			field = kss.getClass().getEnclosingClass().getDeclaredField("entries");
			field.setAccessible(true);
			entries = (Collection)field.get(kss);
			
			for(Object entry : entries) {
				field = entry.getClass().getDeclaredField("certChain");
				field.setAccessible(true);
				certificates = (X509Certificate[])field.get(entry);

				hashCode = Integer.toString(certificates[0].hashCode());

				field = entry.getClass().getDeclaredField("alias");
				field.setAccessible(true);
				alias = (String)field.get(entry);
				
				
				if(!alias.equals(hashCode)) {
					field.set(entry, alias.concat(" - ").concat(hashCode));
				} 
			} 

			
		}
		
		PrivateKey pkey = null;
		for(Enumeration en = ks.aliases(); en.hasMoreElements(); )
        {
			String aliasKey = (String)en.nextElement();
            X509Certificate cert = (X509Certificate)ks.getCertificate(aliasKey);
            if(cert.getSerialNumber().intValue() == certificate.getSerialNumber().intValue()){
            	pkey = (PrivateKey) ks.getKey(aliasKey, null);
            	break;
            }
            
        }
		 
		//System.out.println("the broken key part is   "+new String(brokenKeyPart)+" with the length "+new String(brokenKeyPart).length());
		
		//Instantiate Cipher object and decrypt the key part using RSA algorithm....
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, pkey);
		brokenKeyPart = cipher.doFinal(brokenKeyPart);
		brokenKey = base64.base64Encode(brokenKeyPart);
		
		return brokenKey;
	}
	
}
