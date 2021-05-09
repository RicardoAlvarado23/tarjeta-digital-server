package com.cmacsullana.tarjetadigital.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmacsullana.tarjetadigital.constants.Constantes;
import com.cmacsullana.tarjetadigital.service.BusService;
import com.cmacsullana.tarjetadigital.util.UtilFunctions;

/**
 * 
 * @author riap
 *
 */
@RestController
@RequestMapping("/panel")
public class TarjetaDigitalController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BusService busServicio;
	

	@PostMapping("/obtener-cuestionario")
	public ResponseEntity<?> obtenerCuestionario(@RequestBody Map<String, Object> parametros) {
		Map<String, Object> response = UtilFunctions.createMessageOk();
		try {
			String tipoDocumento = UtilFunctions.verifyString(parametros.get("tipoDocumento"));
			String doi = UtilFunctions.verifyString(parametros.get("doi"));
			String celular = UtilFunctions.verifyString(parametros.get("celular"));
			
			Map<String, Object> result = busServicio.obtenerCuestionarioCliente(tipoDocumento, doi, celular);
			response = UtilFunctions.createMessage(result);
			response.putAll(result);
		} catch (Exception e) {
			logger.error("Error en obtener cuestionario. Detalle: " + e.getMessage());
			response = UtilFunctions.createMessageError("Error en obtener cuestionario");
		}
		return ResponseEntity.ok().body(response);
	}
	
	
	@PostMapping("/validar-cuestionario")
	public ResponseEntity<?> validarCuestionario(@RequestBody Map<String, Object> parametros) {
		Map<String, Object> response = UtilFunctions.createMessageOk();
		try {
			String tipoDocumento = UtilFunctions.verifyString(parametros.get("tipoDocumento"));
			String doi = UtilFunctions.verifyString(parametros.get("doi"));
			String codigoEvaluacion = UtilFunctions.verifyString(parametros.get("codigoEvaluacion"));
			String respuestas = UtilFunctions.verifyString(parametros.get("respuestas"));
			
			Map<String, Object> result = busServicio.validarCuestionarioCliente(tipoDocumento, doi, codigoEvaluacion, respuestas);
			response = UtilFunctions.createMessage(result);
			response.putAll(result);
		} catch (Exception e) {
			logger.error("Error en validar cuestionario. Detalle: " + e.getMessage());
			response = UtilFunctions.createMessageError("Error en validar cuestionario");
		}
		return ResponseEntity.ok().body(response);
	}
	
}
