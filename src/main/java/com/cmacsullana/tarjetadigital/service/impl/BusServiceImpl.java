package com.cmacsullana.tarjetadigital.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cmacsullana.tarjetadigital.enums.ServiceLayerOperation;
import com.cmacsullana.tarjetadigital.service.BusService;
import com.cmacsullana.tarjetadigital.util.RestJsonProvider;
import com.cmacsullana.tarjetadigital.util.UtilFunctions;

/**
 * 
 * @author riap
 *
 */
@Service("busService")
public class BusServiceImpl extends RestJsonProvider  implements BusService{

	@Value("${url.service.layer.bus}")
	private String urlServiceLayerBus;
	
	@Override
	public Map<String, Object> obtenerCuestionarioCliente(String tipoDocumento, String numeroDocumento,
			String celular) {
		ServiceLayerOperation serviceLayerOperation = ServiceLayerOperation.VISO_OBTENER_CUESTIONARIO_CLIENTE;
		Map<String, Object> params = new HashMap<>();
		params.put("tipoDocumento", tipoDocumento);
		params.put("numDocumento", numeroDocumento);
		params.put("codCuestionario", "11");
		params.put("celular", celular);
		UtilFunctions.addDefaultParams(params, serviceLayerOperation);
		UtilFunctions.addLogParams(params);
		UtilFunctions.addDatoSerie(params, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);

		Map<String, Object> result = callServiceJson(params, serviceLayerOperation.getUrl(urlServiceLayerBus));
		return result;
	}

}
