package com.sap.india.srm.purchasermigratecertificate;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.sap.india.srm.purchaseruploadcertificates.PurchaserBase64EncodeDecode;

public class PurchaserMigrateDecryptionOfKeyPart {

	public String decryptKeyPart(X509Certificate certificate, byte[] brokenKeyPart) throws KeyStoreException, Exception{
		 
		KeyStore ks;
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		try {
			ks = KeyStore.getInstance("WINDOWS-MY");
			ks.load(null, null);
			PrivateKey pkey = null;
			for(Enumeration en = ks.aliases(); en.hasMoreElements(); )
			{
				String aliasKey = (String)en.nextElement();
				X509Certificate cert = (X509Certificate)ks.getCertificate(aliasKey);
				if(certificate.getSerialNumber().intValue()	 == cert.getSerialNumber().intValue()){
					//extract the private key to decrypt.....
					pkey = (PrivateKey) ks.getKey(aliasKey, null);
					// TODO Auto-generated catch block
					break;
				}
			}
			
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pkey);
			brokenKeyPart = cipher.doFinal(brokenKeyPart);
			return (base64.base64Encode(brokenKeyPart));
			
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
