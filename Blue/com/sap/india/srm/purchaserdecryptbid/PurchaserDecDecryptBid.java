package com.sap.india.srm.purchaserdecryptbid;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JApplet;


//This class decrypt encrypted data from the key sent ...

public class PurchaserDecDecryptBid extends JApplet{
	
	
	//This method decrypts all bid and returns the decrypted original data in string format...
	public String decryptAllBid(byte[] assembledKey , String inputdata) throws Exception{
		PurchaserBase64Utilities base64 = new PurchaserBase64Utilities();
		PurchaserDecGeneralUtilities genutil = new PurchaserDecGeneralUtilities();
		Key key = genutil.getKeyObject(assembledKey);
		byte[] encryptedData = base64.base64Decode(inputdata); 
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String strSalt = "12";
		byte raw[] = strSalt.getBytes("UTF-8");
		byte keyBytes[] = new byte[16];
		System.arraycopy(raw, 0, keyBytes, 0, Math.min(keyBytes.length, raw.length));
		SecretKeySpec skeySpec = new SecretKeySpec(assembledKey, "AES");
		IvParameterSpec ivs = new IvParameterSpec(keyBytes);
		
		
		cipher.init(Cipher.DECRYPT_MODE, key, ivs);
		encryptedData = cipher.doFinal(encryptedData);
		return base64.base64Encode(encryptedData); 
	} 
}
