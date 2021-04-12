package com.cmacsullana.tarjetadigital.util;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author riap
 *
 */
public class Encryptor {
	private static final String TOKEN = "Qk55cHdhb3QhZ29MWkM9Q1pCUFRO";
	private String salt;
	private int pwdIterations = 100;
	private int keySize = 128;
	private byte[] ivBytes;
	private String ivRandom;
	private String keyAlgorithm = "AES";
	private String encryptAlgorithm = "AES/CBC/PKCS5Padding";
	private String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";

	public Encryptor() {
		this.salt = getSalt();
		this.ivRandom = getRandomIv();
	}

	public String getSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[8];
		random.nextBytes(bytes);
		String text = new String(bytes);
		return text;
	}

	private String getRandomIv() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[128 / 16];

		random.nextBytes(bytes);
		String text = new String(bytes);
		return text;
	}

	/**
	 * @param token
	 * @param plainText
	 * @return encrypted text
	 * @throws Exception
	 */
	public EncryptorModel encyrpt( String plainText) throws Exception {
		byte[] saltBytes = salt.getBytes("UTF-8");
		SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
		PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
		SecretKey secretKey = skf.generateSecret(spec);
		SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);

		// AES initialization
		Cipher cipher = Cipher.getInstance(encryptAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// generate IV
		this.ivBytes = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));

		String value = java.util.Base64.getEncoder().encodeToString(encryptedText);
		return new EncryptorModel(byteArrayToHexString(saltBytes), byteArrayToHexString(this.ivBytes), value);
	}
	
	/**
	 * 
	 * @param encryptText
	 * @return decrypted text
	 * @throws Exception
	 */
	public String decrypt( String value) throws Exception {
		byte[] saltBytes = salt.getBytes("UTF-8");
		byte[] encryptTextBytes =  java.util.Base64.getDecoder().decode(value);	

		SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
		PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
		SecretKey secretKey = skf.generateSecret(spec);
		SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);
		// SecretKey key = generateKeyFromPassword(TOKEN,saltBytes);

		// decrypt the message
		Cipher cipher = Cipher.getInstance(encryptAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));

		byte[] decyrptTextBytes = null;
		try {
			decyrptTextBytes = cipher.doFinal(encryptTextBytes);
		} catch (IllegalBlockSizeException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		String text = new String(decyrptTextBytes, StandardCharsets.UTF_8);
		return text;
	}
	
	public String decrypt( EncryptorModel encriptor) throws Exception {
		byte[] saltBytes =DatatypeConverter.parseHexBinary(encriptor.getSalt()); // hexStringToByteArray(salt);
		byte[] ivBytes = DatatypeConverter.parseHexBinary(encriptor.getIv());// hexStringToByteArray(iv);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec sKey = (SecretKeySpec) generateKeyFromPassword(TOKEN, saltBytes);
		Cipher c = Cipher.getInstance(encryptAlgorithm);
		c.init(Cipher.DECRYPT_MODE, sKey, ivParameterSpec);
		byte[] decordedValue = java.util.Base64.getDecoder().decode(encriptor.getValue().toString());
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue, StandardCharsets.UTF_8);
		return decryptedValue;
	}

	public  SecretKey generateKeyFromPassword(String password, byte[] saltBytes) throws GeneralSecurityException {

		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltBytes, pwdIterations, keySize);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(secretKey.getEncoded(), "AES");
	}

	// Converting a string of hex character to bytes
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// Converting a bytes array to string of hex character
	public static String byteArrayToHexString(byte[] b) {
		int len = b.length;
		String data = new String();

		for (int i = 0; i < len; i++) {
			data += Integer.toHexString((b[i] >> 4) & 0xf);
			data += Integer.toHexString(b[i] & 0xf);
		}
		return data;
	}
}
