package com.cmacsullana.tarjetadigital.entity;

import java.util.Date;

public class Sesion {
	private String valor;
	private String llave;
	private Date createAt;
	
	public Sesion() {
		createAt = new Date();
	}
	
	public Sesion(String llave, String valor) {
		this.llave = llave;
		this.valor = valor;
		createAt = new Date();
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Sesion [valor=" + valor + ", llave=" + llave + ", createAt=" + createAt + "]";
	}
	
	
	
	
}
