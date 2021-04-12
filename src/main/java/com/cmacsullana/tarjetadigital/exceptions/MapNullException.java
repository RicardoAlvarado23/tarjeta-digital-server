package com.cmacsullana.tarjetadigital.exceptions;

/**
 * 
 * @author riap
 *
 */
public class MapNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String codigo;
	private final String mensaje;
	
	public MapNullException(String codigo,String mensaje,String message) {
		super(message);
		this.codigo=codigo;
		this.mensaje=mensaje;
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
}