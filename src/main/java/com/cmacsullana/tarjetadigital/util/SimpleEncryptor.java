package com.cmacsullana.tarjetadigital.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author riap
 *
 */
public class SimpleEncryptor {
	private static final Logger LOGGER = ESAPI.getLogger(Encryptor.class);

	/*
	 * Encripta un dato
	 */
	public String encrypt(String decrypted) {
		try {
			CipherText cipherText = ESAPI.encryptor().encrypt(new PlainText(decrypted));
			byte[] crypt = cipherText.asPortableSerializedByteArray();
			String encrypt = ESAPI.encoder().encodeForBase64(crypt, true);
			return encrypt;
		} catch (EncryptionException ex) {
			LOGGER.error(Logger.EVENT_FAILURE, "Error al encriptar valor. " + " - Detalle: " + ex.getMessage());
			return StringUtils.EMPTY;
		}
	}

	/*
	 * Desencripta un dato
	 */
	public String decrypt(String encrypted) {
		try {
			byte[] crypt = ESAPI.encoder().decodeFromBase64(encrypted);
			CipherText cipherText = CipherText.fromPortableSerializedBytes(crypt);
			PlainText plainText = ESAPI.encryptor().decrypt(cipherText);
			return plainText.toString();
		} catch (IOException ex) {
			LOGGER.error(Logger.EVENT_FAILURE, "Error al desencriptar valor. " + " - Detalle: " + ex.getMessage());
		} catch (EncryptionException ex) {
			LOGGER.error(Logger.EVENT_FAILURE, "Error al desencriptar valor. " + " - Detalle: " + ex.getMessage());
		}
		return StringUtils.EMPTY;
	}
}
