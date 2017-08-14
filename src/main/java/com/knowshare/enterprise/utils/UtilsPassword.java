/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Pablo Gaitan
 *
 */
public class UtilsPassword {

	public static String hashPassword(String username, String password) throws NoSuchAlgorithmException{
		String cadena = username.toLowerCase() + password;
		String md5 = new String();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(cadena.getBytes(), 0, cadena.length());
		md5 = new BigInteger(1, digest.digest()).toString(16);
		return md5;
	}
}
