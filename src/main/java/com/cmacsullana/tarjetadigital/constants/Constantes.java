package com.cmacsullana.tarjetadigital.constants;

/**
 * The Class Constantes.
 *
 * @author riap
 */
public class Constantes {

    public static String NOMBRE_CANAL_APLICACION  = "TJDIGITAL";
    public static String NOMBRE_USUARIO_APLICACION  = "TJDIGITAL";
    public static String NOMBRE_USUARIO_CANAL_REMOTO  = "TJDIGITAL";
    public static String IND_N  = "N";
    public static String IND_S  = "S";
    public static String IND_R  = "R";
    public static String IND_SR  = "SR";
    public static String LLAVE_CANAL_REMOTE = "689460b21fb5b102ea6397630e0f911c";
    public static final String CONCAT_PALOTE = "|";
    public static final String BLANK = " ";
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String DOT = ".";
    public static final String SPLIT_PALOTE = "\\|";
    public static final String PAGINA_UNO = "1";
    public static final String CANTIDAD_X_PAGINA = "10";
    public static final String CODIGO_PAIS = "10";
    public static final String AGENCIA_PRINCIPAL = "101";
    public static final String TOKEN_USER = "Qk55cHdhb3QhZ29MWkM9Q1pCUFRO";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static abstract class SESION {
    	public final static String TOKEN_FLOW = "tokenFlow";
    	public final static String TOKEN_VALIDATION = "tokenValidation";
    }

    /**
     * The Class RESPUESTA_CONTROLADOR.
     */
    public static abstract class RESPUESTA_CONTROLADOR {
        public final static String ESTADO_ERROR = "error";
        public final static String ESTADO_EXITO = "exito";
        public final static String MENSAJE_ERROR_AUTENTICACION = "credenciales incorrectas";
        public final static String MENSAJE_ERROR_SISTEMA = "Error inesperado del sistema";
        public final static String MENSAJE_ERROR_AUTENTICACION_REQUERIDA = "autenticacion requerida";
        public static final String ADAP_ERROR_CAMPO_NO_ENCONTRADO="EG001";
    }
    
    /**
     * The Class PARAMETRO.
     */
    public static abstract class PARAMETRO {
    	
    	public static final String STATUS = "status";
        public static final String SUCCESS = "success";
        public static final String VO_IND = "VO_IND";
        public static final String VO_MSN = "VO_MSN";
        public static final String MESSAGE = "message";
        public static final String ID = "ID";       
        public static final String ID_OPER = "ID_OPER";
        public static final String AGENCIA = "agencia";
        public static final String AGENCIA_UPPER = "AGENCIA";
        public static final String BASE_LABEL_EMAIL="VO_EMAIL_DESTINO";
        public static final String BASE_LABEL_CODIGO="VO_COD_ERROR";
    	public static final String BASE_LABEL_CURSOR="CO_CURSOR";
    	public static final String BASE_LABEL_MENSAJE="VO_MSG_ERROR";
    	public static final String BASE_LABEL_CODIGO2="VO_IND";
    	public static final String BASE_LABEL_MENSAJE2="VO_MSN";
    	public static final String BASE_LABEL_CODIGO3="VO_CODIGO_ERROR";
    	public static final String BASE_LABEL_MENSAJE3="VO_MENSAJE_ERROR";
    	public static final String BASE_LABEL_TOKEN="VO_TOKEN";
    	public static final String BASE_LABEL_FECHA="VO_FECHA_TRX";
    	public static final String BASE_LABEL_HORA="VO_HORA_TRX";
    	public static final String BASE_LABEL_DATA_APLICATIVA="VO_DATA_APLICATIVA";
        public static final String APELLIDO_MATERNO = "apellidoMaterno";
        public static final String APELLIDO_PATERNO = "apellidoPaterno";
        public static final String APORTE_INICIAL = "aporteInicial";
        public static final String APORTE_INICIAL_MINIMO = "aporteInicialMinimo";
        public static final String BONIFICACION = "bonificacion";
        public static final String CANAL = "canal";
        public static final String USUARIO = "usuario";
        public static final String CAPITAL_ACUMULADO = "capitalAcumulado";
        public static final String CLAVE_PRODUCTO = "claveProducto";
        public static final String CODIGO_AGENCIA  = "codigoAgencia";
        public static final String CODIGO_APLICACION = "codigoAplicacion";
        public static final String CODIGO_MONEDA = "codigoMoneda";
        public static final String CODIGO_KILATAJE = "codigoKilataje";
        public static final String CODIGO_PRODUCTO = "codigoProducto";
        public static final String CODIGO_PRODUCTO_RELACIONADO = "codigoProductoRelacionado";
        public static final String CODIGO_SUB_APLICACION = "codigoSubAplicacion";
        public static final String CODIGO_USUARIO  = "codigoUsuario";
        public static final String CO_CURSOR = "CO_CURSOR";
        public static final String CURSOR = "cursor";
        public static final String CURSOR1 = "cursor1";
        public static final String CURSOR2 = "cursor2";
        public static final String CURSOR3 = "cursor3";
        public static final String DATA_APLICATIVA = "dataAplicativa";
        public static final String DATA_APLICATIVA1 = "dataAplicativa1";
        public static final String DATA_APLICATIVA2 = "dataAplicativa2";
        public static final String DATA_APLICATIVA3 = "dataAplicativa3";
        public final static String DATO = "dato";
        public static final String DATO1 = "dato1";
        public static final String DATO2 = "dato2";
        public static final String DATO3 = "dato3";
        public static final String DATO4 = "dato4";
        public static final String DATO5 = "dato5";
        public static final String CC = "cc";
        public static final String BCC = "bcc";
        public static final String BODY = "body";
        public static final String SUBJECT = "subject";
        public static final String SECURE_SALT = "s";
        public static final String SECURE_IV = "i";
        public static final String SECURE_VALUE = "v";
        public static final String FROM = "from";
        public static final String PASS = "pass";
        public static final String TO = "to";
        public static final String ATTACHMENT = "attachment";
        public static final String FILE = "file";
        public static final String FILENAME = "filename";
        public static final String PAR_TOKEN = "vo_token";
    	public static final String OPCION = "opcion";
    	public static final String NOMBRE_USUARIO = "nombreCliente";
    	public static final String TOKEN = "token";
    	public static final String TIPO_CONSULTA = "tipoConsulta";
    	public static final String DOI_USUARIO = "doiUsuario";
    	public static final String EMAIL = "email";
    	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
    	public static final String SESSION = "session";
    	public static final String FECHA_TRACE_ID = "fechaTraceId";
    	public static final String IP_ORIGEN = "ipOrigen";
    	public static final String FECHA = "fecha";
    	public static final String HORA = "hora";
    	public static final String IDSESSION = "IDSESSION";
        public static final String MEDIOACCESO = "MEDIOACCESO";
        public static final String TRACE = "TRACE";
        public static final String PAIS = "pais";
        public static final String DEPARTAMENTO = "departamento";
        public static final String PROVINCIA = "provincia";
        public static final String DISTRITO = "distrito";
        public static final String PRODUCTO = "producto";
        public static final String TIPO_DOC = "tipoDoc";
        public static final String NRO_DOC = "nroDoc";
        public static final String TIPO = "tipo";
        public static final String TIPO_DOCUMENTO = "tipoDocumento";
        public static final String NUM_DOCUMENTO = "numDocumento";
        public static final String NOMBRES = "nombres";
        public static final String RAZON_SOCIAL = "razonSocial";
        public static final String IND_ADMIS = "indAdmis";
        public static final String MSJ_ADMIS = "msjAdmis";
        public static final String APELLIDO_DE_CASADA = "apellidoDeCasada";
        public static final String FECHA_NACIMIENTO = "fechaNacimiento";
        public static final String TIENE_NEGOCIO = "tieneNegocio";
        public static final String RESPALDO_INGRESO = "respaldoIngreso";
        public static final String OTRO_RESPALDO_INGRESO = "otroRespaldoIngreso";
        public static final String CANAL2 = "canal2";
        public static final String CODIGO_PROSPECTO = "codigoProspecto";
        public static final String DIRECCION = "direccion";
        public static final String CELULAR = "celular";
        public static final String TIPO_CLIENTE = "tipoCliente";
        public static final String MONEDA = "moneda";
        public static final String MONTO = "monto";
        public static final String COMENTARIOS = "comentarios";
        public static final String TIPO_PRODUCTO  = "tipoProducto";
        public static final String RUC = "RUC";
        public static final String DECLARA_VENTAS = "declaraVentas";
        public static final String APORTE_ANUAL = "aporteAnual";
        public static final String CODIGO_ACTIVIDAD_LABORAL = "codigoActividadLaboral";
        public static final String IND_EN_LINEA = "indEnLinea";
        public static final String PLAZO = "plazo";
        public static final String TIPO_FRECUENCIA = "tipoFrecuencia";
        public static final String FRECUENCIA = "frecuencia";
        public static final String FECHA_FIN = "fechaFin";
        public static final String FECHA_INI = "fechaIni";
        public static final String CODIGO_PROCESO = "viCodigoProceso";
        public static final String ID_PRODUCTO = "idProducto";
        public static final String NUMERO_OPERACION = "numeroOperacion";
    }

}