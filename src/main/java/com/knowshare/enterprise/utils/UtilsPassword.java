/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encargado de contener métodos utilitarios en cuanto
 * al manejo de las contraseñas
 * @author Pablo Gaitan
 *
 */
public class UtilsPassword {
	
	private UtilsPassword(){}

	/**
	 * Se encarga de obtener el hash de una contraseña dado el username
	 * y el password
	 * @param username
	 * @param password
	 * @return El hash de la contraseña
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String username, String password) throws NoSuchAlgorithmException{
		String cadena = username.toLowerCase() + password;
		String md5;
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(cadena.getBytes(), 0, cadena.length());
		md5 = new BigInteger(1, digest.digest()).toString(16);
		return md5;
	}
}
