package com.sap.india.srm.vendordataencryption;

import java.lang.reflect.Field;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.sap.india.srm.main.MainApplet;

public class VendorEncryptDecryptKey extends MainApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String encryptSymmetricKey (Key key,X509Certificate certificate) throws Exception, NoSuchPaddingException{
		 VendorGeneralUtilities vgu = new VendorGeneralUtilities();
		 byte[] keyInByteArray = vgu.getByteArrayObjectOfKey(key);
		 
		  
		// RSA 1024 bits Asymmetric encryption of Symmetric AES key		    
		 PublicKey rsaPublicKey = certificate.getPublicKey();
		 //Encrypt the key using RSA cipher algorithm....
		 Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		 rsaCipher.init(Cipher.WRAP_MODE, rsaPublicKey);
		 byte[] encryptSymmetricKey = rsaCipher.wrap(key);
		 
		 /*Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		 rsaCipher.init(Cipher., rsaPublicKey);
		 byte[] encryptSymmetricKey = rsaCipher.doFinal(keyInByteArray);*/
		 
		 
		 VendorBase64EncodeDecode vendorbase64encdec = new VendorBase64EncodeDecode();
		 String encryptedSymKeyToReturn = vendorbase64encdec.base64Encode(encryptSymmetricKey);
   		 return encryptedSymKeyToReturn;
	}
	
	public Key decryptSymmetricKey (X509Certificate certificate, String symmetrickey) throws Exception{
		
		
		//Decrypt the symmetric key which is used to encrypt the data...
		VendorBase64EncodeDecode vendorbase64encdec = new VendorBase64EncodeDecode();
		byte[] symmetricKey = vendorbase64encdec.base64Decode(symmetrickey);
		
		//Instantiate key store and get the same certificate with the key to unwrap....
		
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
            if(certificate.getSerialNumber().intValue()	 == cert.getSerialNumber().intValue()){
            	//extract the private key to decrypt.....
            	pkey = (PrivateKey) ks.getKey(aliasKey, null);
            	break;
            }
        }
		
		
		//decrypt the symmetric key ...
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.UNWRAP_MODE, pkey);
		Key aessymmetricKey = cipher.unwrap(symmetricKey, "AES", Cipher.SECRET_KEY);
		
		
		
		/*Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, pkey);
		byte[] decryptedKeyInByteArray = cipher.doFinal(symmetricKey);*/
		
		
		return aessymmetricKey;
	}
	
//to check availability of private key during encryption..	
	
	public boolean IsPrivateKeyPresent(X509Certificate certificate) throws Exception{
		
		boolean PrivateKeyPresent= false;
		
	
		//Instantiate key store and get the same certificate with the key....
		
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
            if(certificate.getSerialNumber().intValue()	 == cert.getSerialNumber().intValue()){
            	//extract the private key
            	System.out.println("Certificate match found to extract private key");
            	pkey = (PrivateKey) ks.getKey(aliasKey, null);
            	System.out.println("private key="+pkey);
            	if(pkey!=null)
            		PrivateKeyPresent= true;
            	break;
            	
            }
        }
		
		return PrivateKeyPresent;
		
	}
	
}
