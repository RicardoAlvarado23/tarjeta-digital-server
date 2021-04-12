package com.cmacsullana.tarjetadigital.enums;

/**
 * The Enum RespuestaControladorEnum.
 *
 * @author riap
 */
public enum RespuestaControladorEnum {
	ESTADO_ERROR("error");

	private String value;

	/**
	 * Instantiates a new respuesta controlador enum.
	 *
	 * @param value the value
	 */
	private RespuestaControladorEnum(String value) {
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
