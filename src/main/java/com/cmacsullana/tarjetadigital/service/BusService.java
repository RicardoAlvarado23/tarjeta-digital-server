package com.cmacsullana.tarjetadigital.service;

import java.util.Map;

public interface BusService {
	public Map<String, Object> obtenerCuestionarioCliente(String tipoDocumento, String numeroDocumento, String celular );
	public Map<String, Object> validarCuestionarioCliente(String tipoDocumento, String numeroDocumento, String codigoEvaluacion, String respuestas);
}
