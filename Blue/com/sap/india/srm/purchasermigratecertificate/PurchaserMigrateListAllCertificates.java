package com.sap.india.srm.purchasermigratecertificate;

import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.KeyStoreSpi;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.sap.india.srm.purchaseruploadcertificates.PurchaserBase64EncodeDecode;


public class PurchaserMigrateListAllCertificates {
	public String[][] listOfCertificates;
	public ArrayList<X509Certificate> certificateobjects;
	public int certificateSize ; 
	
	public boolean isOldCertificateExists = false;
	
	 
	public X509Certificate oldcertificate = null;
	public PurchaserMigrateListAllCertificates(String oldcerti) {
		// TODO Auto-generated constructor stub
		try{
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
			
			int i=0;
			listOfCertificates = new String[ks.size()][6];
			certificateobjects = new ArrayList<X509Certificate>(ks.size());
			this.getCertificateObject(oldcerti);
			boolean[] keyusage = null; 
			for(Enumeration en = ks.aliases(); en.hasMoreElements(); )
	        {
	            String aliasKey = (String)en.nextElement();
	            X509Certificate cert = (X509Certificate)ks.getCertificate(aliasKey);
	            //  Validating certificate....
	            PurchaserMigrateValidateCertificate vcert = new PurchaserMigrateValidateCertificate();
	            if(oldcertificate.getSerialNumber().intValue() == cert.getSerialNumber().intValue()){
	            	isOldCertificateExists = true;
	            	continue;
	            }
	            if(!vcert.isValidCertificate(cert)){
	            	continue;
	            }
	            
	            
	            //Following block is for checking the purpose of the certificate....
                keyusage = cert.getKeyUsage() ;
                if(keyusage != null) {
                	if(!keyusage[2]){
                		if(!keyusage[3] ){
            				continue;
                		}
                	}
                }else{
                	continue;
                }
	            
	            certificateobjects.add(i,cert);
	            i++;
	        }
			this.certificateSize = this.certificateobjects.size();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[][] getAllCertificatesFromMSStore() {
		// TODO Auto-generated method stub
		try{
			
			int i=0;
			listOfCertificates = new String[certificateobjects.size()][6];
			for(X509Certificate cert : this.certificateobjects){
				String[] st = cert.getSubjectDN().getName().split(",");
				int k = 0;
				while(k<st.length){
					if(st[k].contains("CN") || st[k].contains("cn")){
						listOfCertificates[i][0] = st[k].substring((st[k].indexOf("=") + 1), st[k].length());
						break;
					}
					k++;
				}
                //listOfCertificates[i][0] = st[0].substring(3, (st[0].length()-1));
				//listOfCertificates[i][0] = cert.getSubjectDN().getName();
                listOfCertificates[i][1] = Integer.toString(cert.getVersion());
                Collection coll = cert.getSubjectAlternativeNames();
                if(coll != null){
	                for(Iterator iter = coll.iterator(); iter.hasNext();)
	                {
	                    List lst = (List)iter.next();
	                    if(((Integer)lst.get(0)).intValue() == 1)
	                    {
	                    	String email = (String)lst.get(1);
	                    	listOfCertificates[i][2] = email;
	                    }
	                }
                }else{
                	listOfCertificates[i][2] = (new String("Not Available"));
                }
                st=cert.getIssuerDN().toString().split(",");
                k=0;
                while(k<st.length){
					if(st[k].contains("CN") || st[k].contains("cn")){
						listOfCertificates[i][3] = st[k].substring((st[k].indexOf("=") + 1), st[k].length());
						break;
					}
					k++;
				}
                
                
                boolean[] keyusage = null; 
                keyusage = cert.getKeyUsage() ;
                if(keyusage != null) {
                	if((keyusage[0] && (keyusage[2] ||keyusage[3]))){
                		listOfCertificates[i][4] = "Digital sign/Encryption";
                	}
                	else if(keyusage[2] || keyusage[3]){
                		listOfCertificates[i][4] = "Encryption";
                	}
                	else{
                		continue;
                	}
                }else{
                	continue;
                }
                Date date = cert.getNotAfter();
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                listOfCertificates[i][5] = formatter.format(date);
                i++;
			}
			return listOfCertificates;
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<X509Certificate> getAllCertificateObjects(){
		return certificateobjects;
	}
	
	public void getCertificateObject(String certi){
		PurchaserMigrateGeneralUtilities genutil = new PurchaserMigrateGeneralUtilities();
		PurchaserBase64EncodeDecode base64 = new PurchaserBase64EncodeDecode();
		
		byte[] certificateInByte = base64.base64Decode(certi);
		oldcertificate = (X509Certificate) genutil.getCertificateObject(certificateInByte);
		
	}
}
