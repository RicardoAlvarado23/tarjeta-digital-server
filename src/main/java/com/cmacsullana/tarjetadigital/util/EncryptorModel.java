package com.cmacsullana.tarjetadigital.util;

/**
 * 
 * @author riap
 *
 */
public class EncryptorModel {
	private String salt;
	private String iv;
	private Object value;

	public EncryptorModel(String salt, String iv, String value) {
		super();
		this.salt = salt;
		this.iv = iv;
		this.value = value;
	}

	public EncryptorModel() {
		super();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EncryptorModel [salt=" + salt + ", iv=" + iv + ", value=" + value + "]";
	}

}
