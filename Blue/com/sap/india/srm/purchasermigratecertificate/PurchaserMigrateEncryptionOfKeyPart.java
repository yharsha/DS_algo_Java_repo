package com.sap.india.srm.purchasermigratecertificate;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sap.india.srm.purchaseruploadcertificates.PurchaserBase64EncodeDecode;

public class PurchaserMigrateEncryptionOfKeyPart {
 
	public String encryptKeypart(String strDecryptedData, X509Certificate selectedCertificate){
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		byte[] strDecryptedDataInByte = base64.base64Decode(strDecryptedData);
		
		PublicKey pkey = selectedCertificate.getPublicKey();
		
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pkey);
			strDecryptedDataInByte = cipher.doFinal(strDecryptedDataInByte);
			
			return base64.base64Encode(strDecryptedDataInByte);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
