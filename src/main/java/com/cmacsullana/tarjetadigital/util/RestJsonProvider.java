package com.cmacsullana.tarjetadigital.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author riap
 * 
 */
@Component
public class RestJsonProvider {
//

	@Value("${user.service.bus}")
	private String SERVICE_LAYER_USER_BUS;

	@Value("${user.service.bus.pwd}")
	private String SERVICE_LAYER_PWD_BUS;

	protected RestTemplate restTemplateJson = new RestTemplate();

	private String passwordDecrypt = StringUtils.EMPTY;

	protected Map<String, Object> callServiceJson(Map<String, Object> params, String url) {
		HttpHeaders headers = null;
		headers = UtilFunctions.getHttpHeadersJson(SERVICE_LAYER_USER_BUS, getLayerPassword());

		System.out.println("URL: " + url);
		System.out.println("Trama: " + UtilFunctions.mapToJson(params));
		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(params, headers);

		Map<String, Object> result = (Map<String, Object>) restTemplateJson.postForObject(url, request, Map.class);

		System.out.println("result: " + UtilFunctions.mapToJson(result));
		return result;
	}

	private String getLayerPassword() {
		if (passwordDecrypt.isEmpty())
			passwordDecrypt = SERVICE_LAYER_PWD_BUS;

		return passwordDecrypt;
	}

}
