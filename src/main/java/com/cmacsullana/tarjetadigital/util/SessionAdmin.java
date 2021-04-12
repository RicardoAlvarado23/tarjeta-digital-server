package com.cmacsullana.tarjetadigital.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cmacsullana.tarjetadigital.entity.Sesion;

/**
 * 
 * @author riap
 *
 */
@Service
public class SessionAdmin {
	
	private LinkedHashMap<String, Object> sesion;

	public SessionAdmin() {
		sesion = new LinkedHashMap<String, Object>();
	}
	
	
	public void insertarValorSesion(String key, Object value) {
		sesion.put(key, value);
	}
	
	public Object extrarValorSesion(String key) {
		return sesion.get(key);
	}
	
	public LinkedHashMap<String, Object> getAll() {
		return sesion;
	}
	
	public void eliminarValorSesion(String key) {
		sesion.remove(key);
	}
	
	public void resetearSesion() {
		sesion = new LinkedHashMap<String, Object>();
	}
	
	public void imprimirSesion() {
		for (Map.Entry<String, Object> entry : sesion.entrySet()) {
		    String key = entry.getKey();
		    Sesion  value = (Sesion) entry.getValue();
		    System.out.println("Llave: " + key  + " Valor: " + value.getValor());
		}
	}
}
