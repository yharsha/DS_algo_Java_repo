package com.sap.india.srm.purchasermigratecertificate;

import java.security.KeyFactory;
import java.security.cert.X509Certificate;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

public class PurchaserMigrateValidateCertificate {
	private boolean isValidCertificate = false;
	public boolean isValidCertificate(X509Certificate cert){
		if(cert.getNotAfter().before(new Date())){
			isValidCertificate = false;
		}
		else{
			//isValidCertificate = true;
			try {
				isValidCertificate = classValidationOfCertificate(cert);
				
				KeyFactory keyfact = KeyFactory.getInstance("RSA");
				RSAPublicKeySpec  pubkey = keyfact.getKeySpec(cert.getPublicKey(), RSAPublicKeySpec.class);

				int RSAPublicKeyLength = pubkey.getModulus().bitLength();
				if(RSAPublicKeyLength == 1024){
					isValidCertificate = false;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isValidCertificate;
	}
	private boolean classValidation(X509Certificate cert) {
		// TODO Auto-generated method stub
		
		String str = (new StringBuilder()).append(cert.getSubjectDN().toString()).append(cert.getIssuerDN().toString()).toString();
		
		str = str.toLowerCase();
		
		if(str.indexOf("class 3") >= 0 || str.indexOf("class iii") >= 0 || str.indexOf("class 3a")>=0 ||str.indexOf("class iiia") >= 0 || str.indexOf("class iiib") >= 0 || str.indexOf("class 3b")>=0){
			return true;
		}else if(str.indexOf("class 2") >= 0 || str.indexOf("class ii") >= 0 || str.indexOf("class 2a")>=0 ||str.indexOf("class iia") >= 0 || str.indexOf("class iib") >= 0 || str.indexOf("class 2b")>=0){
				return true;
		}
		return false;
	}
	private boolean classValidationOfCertificate(X509Certificate cert) throws Exception{
		boolean isValidCertificate = false;
		if(cert.getExtensionValue("2.5.29.32") != null){
			String strCertDn = new String(cert.getExtensionValue("2.5.29.32"),"UTF-8");
			if(strCertDn != null){
				strCertDn  = strCertDn.toLowerCase();
				if(strCertDn.indexOf("class 3") >= 0 || strCertDn.indexOf("class iii") >= 0 || strCertDn.indexOf("class 3a")>=0 ||strCertDn.indexOf("class iiia") >= 0 || strCertDn.indexOf("class iiib") >= 0 || strCertDn.indexOf("class 3b")>=0){
					isValidCertificate = true;
				}else if(strCertDn.indexOf("class 2") >= 0 || strCertDn.indexOf("class ii") >= 0 || strCertDn.indexOf("class 2a")>=0 ||strCertDn.indexOf("class iia") >= 0 || strCertDn.indexOf("class iib") >= 0 || strCertDn.indexOf("class 2b")>=0){
					isValidCertificate = true;
				}	
			}
		}
		if(!isValidCertificate){
			isValidCertificate = classValidationWithPolicyStatement(cert);
		}
		return isValidCertificate;
	}
	private boolean classValidationWithPolicyStatement(X509Certificate cert){
		
		int indexOfCertificatePolicy = cert.toString().indexOf("CertificatePolicyId");
		
		if(indexOfCertificatePolicy!=-1)
		{
			String strCertificatePolicy = cert.toString().substring(indexOfCertificatePolicy, indexOfCertificatePolicy+100);
		
			if(strCertificatePolicy.contains("2.16.356.100.2.2") || strCertificatePolicy.contains("2.16.356.100.2.3")){
			return true;
			}
		}
		return false;
	}
}
