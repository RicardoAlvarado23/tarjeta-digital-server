package com.cmacsullana.tarjetadigital.exceptions;

/**
 * 
 * @author riap
 *
 */
public class BaseDatosException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String codigo;
	private final String mensaje;
	
	public BaseDatosException(String codigo,String message) {
		super(message);
		this.codigo=codigo;
		this.mensaje=message;
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	

}