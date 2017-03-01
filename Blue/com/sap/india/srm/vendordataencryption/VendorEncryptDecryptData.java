package com.sap.india.srm.vendordataencryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("serial")
public class VendorEncryptDecryptData {
	
	//this method encrypts data and return its key and encrypted data....
	@SuppressWarnings("finally")
	public Map<Key,String> encryptData(String data)  throws Exception{
System.out.println("data="+data);	 		
		// AES 128 bits Symmetric encryption of data
		Map<Key, String> mapOfKeyAndEncryptedData = new HashMap<Key,String>();
		VendorBase64EncodeDecode base64encdec = new VendorBase64EncodeDecode();
System.out.println("chk0");	 
		byte[] decodedinputdata = base64encdec.base64Decode(data); 
		
System.out.println("chk1");	 
		String strSalt = "12";
		byte[] rawSalt = strSalt.getBytes("UTF-8");
		byte keyBytes[] = new byte[16];
	    System.arraycopy(rawSalt, 0, keyBytes, 0, Math.min(keyBytes.length, rawSalt.length));
	    SecretKey skeySpec = KeyGenerator.getInstance("AES").generateKey();
	    IvParameterSpec ivs = new IvParameterSpec(keyBytes);
	    
	    byte aesKey[] = skeySpec.getEncoded();
	    
System.out.println("chk2");		    
	    
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(1, skeySpec, ivs);
	    decodedinputdata = cipher.doFinal(decodedinputdata);
	    
System.out.println("chk3");		    
		String encrypteddata = base64encdec.base64Encode(decodedinputdata);
		//mapOfKeyAndEncryptedData.put(generatedKey, encrypteddata);
	    mapOfKeyAndEncryptedData.put(skeySpec, encrypteddata);
		
		VendorGeneralUtilities vgu = new VendorGeneralUtilities();
		byte[] keyinbyte = vgu.getByteArrayObjectOfKey(skeySpec);
		return mapOfKeyAndEncryptedData;
	}
	
	
	//this method return original data after decryption..
	public String  decryptData(Key aeskey,String data) throws IOException, Exception{
		//decode the input data first with base 64 encode decode utilities...
		VendorBase64EncodeDecode vendorbase64encdec = new VendorBase64EncodeDecode();
		byte[] inputData = vendorbase64encdec.base64Decode(data); 
		//Instantiate cipher and process decryption...
		
		
		
		String strSalt = "12";
		byte raw[] = strSalt.getBytes("UTF-8");
		byte keyBytes[] = new byte[16];
		System.arraycopy(raw, 0, keyBytes, 0, Math.min(keyBytes.length, raw.length));
		IvParameterSpec ivs = new IvParameterSpec(keyBytes);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, aeskey, ivs);
		inputData = cipher.doFinal(inputData);
		return vendorbase64encdec.base64Encode(inputData); 
	}
	
		
}
