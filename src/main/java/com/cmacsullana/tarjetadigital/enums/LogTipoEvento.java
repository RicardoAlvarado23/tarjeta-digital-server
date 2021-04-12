package com.cmacsullana.tarjetadigital.enums;

public enum LogTipoEvento {
	APERTURA_CUENTA("120");
	
	private String codigo;
	
	LogTipoEvento(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
