package com.cmacsullana.tarjetadigital.enums;

public enum ServiceLayerOperation {
	CREDICLIC_LISTAR_TIPOS_DOCUMENTO("223","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_AGENCIAS("256","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_LUGARES("271","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_PRODUCTOS_WEB("951","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_BENEFICIOS("952","/CmacCrediclicService/cmacCrediclicService.json"),
	RENIEC_CONSULTAR_USUARIO_BUS("392","/CmacCrediclicService/cmacCrediclicService.json"),
	SENTINEL_CONSULTAR_RUC("602","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_ACTIVIDADES("455","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_REGISTRAR_PROSPECCION_EXTERNA("940","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_REGISTRAR_PROSPECCION_EXTERNA_ONLINE("953","/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_PROCESAR_PROSPECCION_EXTERNA("942", "/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_CONSULTAR_PROCESO_PROSPECTO("946", "/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_DOCUMENTOS_PRODUCTO("954", "/CmacCrediclicService/cmacCrediclicService.json"),
	CREDICLIC_LISTAR_PARAMETROS_VARIOS("1516", "/CmacCrediclicService/cmacCrediclicService.json"),
	VISO_OBTENER_CUESTIONARIO_CLIENTE("955", "/CmacCrediclicService/cmacCrediclicService.json"),
	VISO_VALIDAR_CUESTIONARIO_CLIENTE("956", "/CmacCrediclicService/cmacCrediclicService.json"),
    ;
	
	private final String id;
	private final String url;

	ServiceLayerOperation(String id, String url) {
		this.id = id;
		this.url = url;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	public String getUrl(String prefixUrl) {
		return prefixUrl + url;
	}
}
