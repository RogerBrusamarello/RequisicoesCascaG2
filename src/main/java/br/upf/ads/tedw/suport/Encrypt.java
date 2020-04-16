package br.upf.ads.tedw.suport;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encrypt {

	/**
	 * 
	 * @param password
	 * @return
	 */

	// Criptografar
	public static String encryptMd5(String password) {

		String encryptedPass = null;
		MessageDigest md;

		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(password.getBytes())); // converter senha em array de bytes
			encryptedPass = hash.toString(16);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return encryptedPass;
	}

	// Validar
	public static boolean validatePass(String dbPassword, String password) {
		return password.equals(dbPassword);
	}
}
