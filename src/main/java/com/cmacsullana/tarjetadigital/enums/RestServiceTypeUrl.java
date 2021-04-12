package com.cmacsullana.tarjetadigital.enums;

public enum RestServiceTypeUrl {
	SERVICE_RENIEC("1"),
	SERVICE_BUS("2");
	String code;
	private RestServiceTypeUrl(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
