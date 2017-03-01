package com.sap.india.srm.vendordataencryption;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class GenerateBrokenKeyForPurchaser {

	/**
	 * @param args
	 */
	
	 
	public ArrayList<String> recoverBrokenKey = null;
	
	
	//Generate Broken Key...............
	public ArrayList<String> brokenKeyForPurchaser(Key key,int noOfPurchasers, X509Certificate[] purchaserscertificates,X509Certificate[] secondarypurchaserscertificates,X509Certificate[] tertiarypurchaserscertificates ) throws Exception{
		
		//Recovery mechanism to retrieve in case of lost of certificate.....
		recoverBrokenKey = new ArrayList<String>();
		
		
		//Broken key is generated and sent back to Vendor's master applet. Where it is set in mainForm....
		VendorGeneralUtilities genutil = new VendorGeneralUtilities();
		VendorBase64EncodeDecode base64encdec = new  VendorBase64EncodeDecode();
		byte[] aesKey  = genutil.getByteArrayObjectOfKey(key);
		 
		//byte[] aesKey = key.getEncoded();
		
		int pieceOfKey = aesKey.length / noOfPurchasers;
		int i,j=1,k,recoveryIterator;
		ArrayList<String> brokenKey = new ArrayList<String>();
		k=0;
		byte[] keyPiece = null;
		for(i = 0,recoveryIterator=0 ; i<noOfPurchasers ; i++,j++){
			if(j == noOfPurchasers){
				keyPiece = new byte[aesKey.length - i*pieceOfKey];
				System.arraycopy(aesKey,i*pieceOfKey, keyPiece, 0, keyPiece.length);
			}else{
				keyPiece = new byte[j*pieceOfKey - i*pieceOfKey];
				System.arraycopy(aesKey,i*pieceOfKey, keyPiece, 0, pieceOfKey);
			}
			
			
			///Asign recover broken key first followed by its encryption.....
			recoverBrokenKey.add(recoveryIterator++,base64encdec.base64Encode(keyPiece));
			
			//Original encryption starts from here....
			//for(int counter=0 ;counter<3;counter++){
			PublicKey rsaPublicKey = purchaserscertificates[i].getPublicKey();
			
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(Cipher.ENCRYPT_MODE, purchaserscertificates[i].getPublicKey());
			//Encrypt the key using RSA cipher algorithm....
			byte[] encryptSymmetricKey = rsaCipher.doFinal(keyPiece);
			
			
			String keypieceinstring = base64encdec.base64Encode(encryptSymmetricKey);
			brokenKey.add(k, keypieceinstring);
			k= k +1;
			
			//Encrypt with secondary purchasers... Its an option field and so requires if - else condition...
			if(secondarypurchaserscertificates[i] !=null){
				PublicKey secondaryRsaPublicKey = secondarypurchaserscertificates[i].getPublicKey();
				Cipher secondaryRsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				secondaryRsaCipher.init(Cipher.ENCRYPT_MODE, secondaryRsaPublicKey);
				
				byte[] secondaryEncryptSymmetricKey = secondaryRsaCipher.doFinal(keyPiece);
				String secondarykeypieceinstring = base64encdec.base64Encode(secondaryEncryptSymmetricKey);
				brokenKey.add(k, secondarykeypieceinstring);
				k = k + 1;
			}else{
				brokenKey.add(k, "");
				k = k + 1;
			}

			//Encrypt with tertiary purchasers...Its an option field and so requires if - else condition.
			if(tertiarypurchaserscertificates[i] != null){
				PublicKey tertiaryRsaPublicKey = tertiarypurchaserscertificates[i].getPublicKey();
				Cipher tertiaryRsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				tertiaryRsaCipher.init(Cipher.ENCRYPT_MODE, tertiaryRsaPublicKey);
				
				byte[] tertiaryEncryptSymmetricKey = tertiaryRsaCipher.doFinal(keyPiece);
				String secondarykeypieceinstring = base64encdec.base64Encode(tertiaryEncryptSymmetricKey);
				brokenKey.add(k, secondarykeypieceinstring);
				k++;
			}else{
				brokenKey.add(k, "");
				k++;
			}
			//}
			

		}
		
		return brokenKey;
	}
	
	public ArrayList<String> brokenKeyForSinglePurchaserWrapMode(Key key,int noOfPurchasers, X509Certificate purchaserscertificates,X509Certificate secondarypurchaserscertificates,X509Certificate tertiarypurchaserscertificates) throws Exception{
		VendorGeneralUtilities genutil = new VendorGeneralUtilities();
		VendorBase64EncodeDecode base64encdec = new  VendorBase64EncodeDecode();
		ArrayList<String> brokenKey = new ArrayList<String>();
		//Divide the key into two parts and encrypt it...
		
		X509Certificate[] purCertificate = new X509Certificate[3];
		purCertificate[0] = purchaserscertificates;
		purCertificate[1] = secondarypurchaserscertificates;
		purCertificate[2] = tertiarypurchaserscertificates;
		int i=0;
		for(int j=0;j<3;j++){
			if(purCertificate[j] !=null){
				Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				cipher.init(Cipher.WRAP_MODE, purCertificate[j].getPublicKey());
				byte[] aesKey = cipher.wrap(key);
				String encryptedSymmetricKey = base64encdec.base64Encode(aesKey);
				brokenKey.add(j, encryptedSymmetricKey);
			}else{
				brokenKey.add(j, null);
			}
		}
		return brokenKey;
	}
	
	
	
	public ArrayList<String> brokenKeyRecovery(){
		return recoverBrokenKey;
	}
}
