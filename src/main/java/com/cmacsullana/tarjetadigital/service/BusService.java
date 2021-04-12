package com.cmacsullana.tarjetadigital.service;

import java.util.Map;

public interface BusService {
	public Map<String, Object> obtenerCuestionarioCliente(String tipoDocumento, String numeroDocumento, String celular );
}
