package fmi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	
	public String generateMD5(String inString){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(inString.getBytes());
			byte bytesData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < bytesData.length; i++){
				sb.append(Integer.toString((bytesData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
