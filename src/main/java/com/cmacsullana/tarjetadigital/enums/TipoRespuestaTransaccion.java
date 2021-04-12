package com.cmacsullana.tarjetadigital.enums;

public enum TipoRespuestaTransaccion {
	 RESPUESTA_SERVIDOR("1"),
	    RESPUESTA_DATABASE("2");

	    private final String code;

	    TipoRespuestaTransaccion(String code) {
	        this.code = code;
	    }

	    /**
	     * @return the code
	     */
	    public String getCode() {
	        return code;
	    }

	    public static TipoRespuestaTransaccion parse(String code) {
	    	TipoRespuestaTransaccion tipoRespuestaTransaccion = null; // Default
	        for (TipoRespuestaTransaccion item : TipoRespuestaTransaccion.values()) {
	            if (item.getCode().equals(code)) {
	            	tipoRespuestaTransaccion = item;
	                break;
	            }
	        }
	        return tipoRespuestaTransaccion;
	    }
}