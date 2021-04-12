package com.cmacsullana.tarjetadigital.enums;

/**
 * The Enum SessionEnum.
 *
 * @author riap
 */
public enum SessionEnum {
	USUARIO("SESION_USUARIO"), 
	IP("SESION_IP"), 
	PC("SESION_PC");

	private String value;

	/**
	 * Instantiates a new session enum.
	 *
	 * @param value the value
	 */
	private SessionEnum(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
