package com.cmacsullana.tarjetadigital.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cmacsullana.tarjetadigital.constants.Constantes;
import com.cmacsullana.tarjetadigital.constants.SecurityConstant;
import com.cmacsullana.tarjetadigital.entity.Sesion;
import com.cmacsullana.tarjetadigital.enums.ServiceLayerOperation;
import com.cmacsullana.tarjetadigital.enums.SessionEnum;
import com.cmacsullana.tarjetadigital.enums.StatusRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UtilFunctions {
	private static Logger logger = Logger.getLogger(UtilFunctions.class);
	private static String HOUR_FORMAT = "HH:mm";
	private static final int REACHABLE_TIMEOUT = 5000;
	
	private static SessionAdmin sessionAdmin;
	
	@Autowired
    public void setSomeThing(SessionAdmin sessionAdmin){
		UtilFunctions.sessionAdmin = sessionAdmin;
    }

	/**
	 * Determinar si el jboss esta en modo Domain o en modo standalone
	 * 
	 * @return boolean
	 */
	public static Boolean esModoDomain() {
		Boolean respuesta = Boolean.FALSE;
		try {
			ObjectName serverMBean = new ObjectName("jboss.as:management-root=server");
			String serverGroupName = (String) ManagementFactory.getPlatformMBeanServer().getAttribute(serverMBean,
					"serverGroup");
			logger.info("Nombre de Dominio " + serverGroupName);
			respuesta = Boolean.TRUE;
		} catch (Exception e) {
			logger.info("Modo stand alone");
		}
		return respuesta;
	}

	/**
	 * Obtener nombre nodo.
	 * 
	 * @return the string
	 */
	public static String obtenerNombreNodo() {
		return System.getProperty("jboss.node.name").trim();
	}

	/**
	 * Obtener usuario sesion.
	 * 
	 * @return the string es el nick del usuario en sesion
	 */
	public static String obtenerUsuarioSesion() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		String nick = null;
		if (session != null) {
			nick = (String) session.getAttribute(SessionEnum.USUARIO.getValue());
		}
		return nick;
	}

	/**
	 * Obtener IP sesion.
	 * 
	 * @return the string
	 */
	public static String obtenerIPSesion() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		String nick = null;
		if (session != null) {
			nick = (String) session.getAttribute(SessionEnum.IP.getValue());
		}
		return nick;
	}

	/**
	 * Obtener PC sesion.
	 * 
	 * @return the string
	 */
	public static String obtenerPCSesion() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		String nick = null;
		if (session != null) {
			nick = (String) session.getAttribute(SessionEnum.PC.getValue());
		}
		return nick;
	}

	/**
	 * Asignar usuario sesion.
	 * 
	 * @param nick the nick
	 */
	public static void asignarUsuarioSesion(String nick) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(SessionEnum.USUARIO.getValue(), nick);
	}

	/**
	 * Asignar IP sesion.
	 * 
	 * @param ip the ip
	 */
	public static void asignarIPSesion(String ip) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(SessionEnum.IP.getValue(), ip);
	}

	/**
	 * Asignar PC sesion.
	 * 
	 * @param pc the pc
	 */
	public static void asignarPCSesion(String pc) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(SessionEnum.PC.getValue(), pc);
	}

	/**
	 * Obtener fecha hora actual.
	 * 
	 * @return the timestamp
	 */
	public static Timestamp obtenerFechaHoraActual() {
		java.util.Date date = new java.util.Date();
		Timestamp timeStampTransaction = new Timestamp(date.getTime());
		return timeStampTransaction;
	}

	/**
	 * Completar numero con ceros.
	 * 
	 * @param len   the len
	 * @param value the value
	 * @return the string
	 */
	public static String completarNumeroConCeros(Integer len, Integer value) {
		return String.format("%0" + len + "d", value);
	}

	/**
	 * Obtener formato fecha.
	 * 
	 * @param Fecha the fecha
	 * @return the string
	 */
	public static String obtenerFormatoFecha(Timestamp Fecha) {
		String fecha = null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").format(Fecha);
		} catch (Exception e) {
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, e);
			e.printStackTrace();
		}
		return fecha;
	}

	/**
	 * Encriptar A md 5.
	 * 
	 * @param input the input
	 * @return the string
	 */
	public static String encriptarAMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes("UTF-8"));
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * Checks if is number.
	 * 
	 * @param cadena the cadena
	 * @return true, if is number
	 */
	public static boolean isNumber(String cadena) {
		boolean isDecimal = false;
		if (cadena == null || cadena.isEmpty()) {
			return false;
		}
		int i = 0;
		if (cadena.charAt(0) == '-') {
			if (cadena.length() > 1) {
				i++;
			} else {
				return false;
			}
		}
		for (; i < cadena.length(); i++) {
			if (!Character.isDigit(cadena.charAt(i))) {
				if (!isDecimal && (cadena.charAt(i) != '.' || cadena.charAt(i) != ',')) {
					isDecimal = true;
					continue;
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if is fecha valida.
	 * 
	 * @param fecha the fecha
	 * @return true, if is fecha valida
	 */
	public static boolean isFechaValida(Date fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(formatoFecha.format(fecha));
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Convertir string to date.
	 * 
	 * @param fechaCadena the fecha cadena
	 * @return the timestamp
	 * @throws ParseException           the parse exception
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static Timestamp convertirStringToDate(String fechaCadena) throws ParseException, IllegalArgumentException {
		Timestamp fecha = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = simpleDateFormat.parse(fechaCadena);
			fecha = new Timestamp(date.getTime());
		} catch (ParseException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		}
		return fecha;
	}

	/**
	 * Convertir pdf A byte array.
	 * 
	 * @param rutaArchivo the ruta archivo
	 * @return the byte[]
	 */
	public static byte[] convertirPdfAByteArray(String rutaArchivo) {

		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			inputStream = new FileInputStream(rutaArchivo);

			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * Obtiene http header
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public static HttpHeaders getHttpHeadersJson(String user, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String authorisation = user + ":" + password;
		byte[] encodedAuthorisation = org.springframework.security.crypto.codec.Base64.encode(authorisation.getBytes());
		headers.add("Authorization", "Basic " + new String(encodedAuthorisation));
		return headers;
	}

	/**
	 * Convertir Map a Json
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map map) {
		try {
			String mapAsJson = new ObjectMapper().writeValueAsString(map);
			return mapAsJson;
		} catch (JsonProcessingException ex) {
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, ex);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Contertir JSON String a Map
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		Map<String, Object> map = null;
		try {
			map = mapper.readValue(json, typeRef);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, e);
			e.printStackTrace();
		}
		return map;
	}

	public static boolean validateIsNotNullAndNotEmpty(Object o) {
		return (validateIsNotNull(o) && validateIsNotEmpty(o));
	}

	public static boolean validateIsNullAndEmpty(Object o) {
		return (validateIsNull(o) && validateIsEmpty(o));
	}

	public static boolean validateIsNullOrEmpty(Object o) {
		return (validateIsNull(o) || validateIsEmpty(o));
	}

	public static boolean validateIsNotNull(Object o) {
		return !validateIsNull(o);
	}

	public static boolean validateIsNull(Object o) {
		return o == null;
	}

	public static boolean validateIsEmpty(Object o) {
		String d = o.toString();
		return d.trim().length() == 0;
	}

	public static boolean validateIsNotEmpty(Object o) {
		return !validateIsEmpty(o);
	}

	public static boolean validateIsNotNullAndPositive(Integer num) {
		return (validateIsNotNull(num) && validateIsPositiveNumber(num));
	}

	public static boolean validateIsNotNullAndPositive(Long num) {
		return (validateIsNotNull(num) && validateIsPositiveNumber(num));
	}

	public static boolean validateIsNotNullAndPositive(BigDecimal num) {
		return (validateIsNotNull(num) && validateIsPositiveNumber(num));
	}

	public static boolean validateIsPositiveNumber(Integer num) {
		return num > 0;
	}

	public static boolean validateIsPositiveNumber(Long num) {
		return num > 0;
	}

	public static boolean validateIsPositiveNumber(BigDecimal num) {
		return BigDecimal.ZERO.compareTo(num) < 0;
	}

	/**
	 * Agregando serie de parámetros: DATO1, DATO2, DATO3,...
	 * 
	 * @param params
	 * @param values
	 */
	public static void addDatoSerie(Map<String, Object> params, String... values) {
		for (int i = 0; i < values.length; i++)
			params.put(Constantes.PARAMETRO.DATO + (i + 1), values[i]);
	}

	/**
	 * Agregando datos default
	 * 
	 * @param params
	 * @param values
	 */
	public static void addDefaultParams(Map<String, Object> params, ServiceLayerOperation serviceLayerOperation) {
		params.put(Constantes.PARAMETRO.CANAL, Constantes.NOMBRE_CANAL_APLICACION);
		params.put(Constantes.PARAMETRO.USUARIO, Constantes.NOMBRE_USUARIO_APLICACION);
		params.put(Constantes.PARAMETRO.ID_OPER, serviceLayerOperation.getId());
	}
	

	/**
	 * 
	 * @param params
	 */
	public static void addLogParams(Map<String, Object> params) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		params.put(Constantes.PARAMETRO.IDSESSION, RequestContextHolder.currentRequestAttributes().getSessionId());
		params.put(Constantes.PARAMETRO.MEDIOACCESO, getRemoteIp(request));
		params.put(Constantes.PARAMETRO.TRACE, getTraceNumber(request.getSession()).toString());
	}

	/**
	 * 
	 * @param httpSession
	 * @return
	 */
	public static Integer getTraceNumber(HttpSession httpSession) {
		Integer trace = 0;
		if (null != httpSession.getAttribute(SecurityConstant.TRACE)) {
			trace = Integer.parseInt(httpSession.getAttribute(SecurityConstant.TRACE).toString());
		}
		// trace++;
		// httpSession.setAttribute(SecurityConstant.TRACE, trace);

		return trace;
	}

	public static void setTraceNumber(String trace) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(SecurityConstant.TRACE, trace);
	}

	/**
	 * Crea un mensaje con un estado determinado por el usuario (status, message)
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static Map<String, Object> createMessage(String errorCode, String message) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put(Constantes.PARAMETRO.SUCCESS,
				errorCode.equals(StatusRest.EXITO.getCode()) || errorCode.equals("0"));
		respuesta.put(Constantes.PARAMETRO.STATUS, errorCode);
		respuesta.put(Constantes.PARAMETRO.MESSAGE, message);
		return respuesta;
	}

	/**
	 * Crea un mensaje de Error
	 * 
	 * @param message
	 * @return
	 */
	public static Map<String, Object> createMessageError(String message) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put(Constantes.PARAMETRO.SUCCESS, false);
		respuesta.put(Constantes.PARAMETRO.STATUS, StatusRest.ERROR.getCode());
		respuesta.put(Constantes.PARAMETRO.MESSAGE, message);
		return respuesta;
	}

	/**
	 * Crea un mensaje satisfactorio
	 * 
	 * @param message
	 * @return
	 */
	public static Map<String, Object> createMessageOk(String message) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put(Constantes.PARAMETRO.SUCCESS, true);
		respuesta.put(Constantes.PARAMETRO.STATUS, StatusRest.EXITO.getCode());
		respuesta.put(Constantes.PARAMETRO.MESSAGE, message);
		return respuesta;
	}

	/**
	 * Mensaje Satisfactorio
	 * 
	 * @return
	 */
	public static Map<String, Object> createMessageOk() {
		return createMessageOk("Proceso satisfactorio.");
	}

	/**
	 * Crea mensaje de un Result
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> createMessage(Map<String, Object> result) {
		String status = result.get(Constantes.PARAMETRO.STATUS).toString();
		String message = result.get(Constantes.PARAMETRO.MESSAGE).toString();
		Map<String, Object> response = createMessage(status, message);
		return response;
	}
	
	/**
	 * Crea mensaje de un Result
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> createMessageV2(Map<String, Object> result) {
		String status = result.get(Constantes.PARAMETRO.VO_IND).toString();
		String message = result.get(Constantes.PARAMETRO.VO_MSN).toString();
		Map<String, Object> response = createMessage(status, message);
		return response;
	}

	public static String trim(Object value) {
		if (value == null) {
			return StringUtils.EMPTY;
		}
		return value.toString().trim();
	}

	public static String validateNumeric(Object value) {
		String numeric = trim(value);
		if (numeric.isEmpty())
			return "0";
		return numeric;
	}

	public static String concatPalote(String... values) {
		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (sb.length() > 0)
				sb.append(Constantes.CONCAT_PALOTE);
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * Obtener IP de Cliente
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("X-ClientIP");  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}
	
	public static String getRemoteIp() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return getRemoteIp(request);
	}

	public static String getFechaHoraActual() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(date);
	}
	
	public static String getFechaActual() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return hourdateFormat.format(date);
	}
	

	/**
	 * 
	 * @param objeto
	 * @return
	 */
	public static String verifyString(Object objeto) {
		if (objeto == null) {
			return StringUtils.EMPTY;
		}
		return ESAPI.encoder().canonicalize(objeto.toString());
	}

	public static Map<String, Object> createSecureMessage(Map<String, Object> result) {
		String json = UtilFunctions.mapToJson(result);
		Encryptor encriptador = new Encryptor();
		EncryptorModel modelo = new EncryptorModel();
		Map<String, Object> respuesta = new HashMap<>();
		try {
			modelo = encriptador.encyrpt(json);
			respuesta.put(Constantes.PARAMETRO.SECURE_SALT, modelo.getSalt());
			respuesta.put(Constantes.PARAMETRO.SECURE_IV, modelo.getIv());
			respuesta.put(Constantes.PARAMETRO.SECURE_VALUE, modelo.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, e);
			e.printStackTrace();
		}
		return respuesta;
	}

	public static Map<String, Object> readSecureRequest(Map<String, Object> request) {
		Encryptor encriptador = new Encryptor();
		Map<String, Object> parametros = new HashMap<>();
		String i = UtilFunctions.verifyString(request.get("i"));
		String s = UtilFunctions.verifyString(request.get("s"));
		String v = UtilFunctions.verifyString(request.get("v"));
		EncryptorModel nuevoEncriptado = new EncryptorModel(s, i, v);
		try {
			String decript = encriptador.decrypt(nuevoEncriptado);
			parametros = UtilFunctions.jsonToMap(decript);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, e);
			e.printStackTrace();
		}
		return parametros;
	}

	public static Map<String, Object> readSecureRequest(String i, String s, String v) {
		Encryptor encriptador = new Encryptor();
		Map<String, Object> parametros = new HashMap<>();
		EncryptorModel nuevoEncriptado = new EncryptorModel(s, i, v);
		try {
			String decript = encriptador.decrypt(nuevoEncriptado);
			parametros = UtilFunctions.jsonToMap(decript);
		} catch (Exception e) {
			Logger.getLogger(UtilFunctions.class.getName()).log(Level.FATAL, null, e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parametros;
	}

	public static String base64Decode(String encoded) {
		return new String(Base64.decodeBase64(encoded.getBytes()));
	}

	public static String encodeBase64(String encode) {
		Base64 base64 = new Base64();
		return new String(base64.encode(encode.getBytes()));
	}

	public static byte[] encodeBase64(byte[] encode) {
		return Base64.encodeBase64(encode);
	}

	public static String decodeBase64(String decode) {
		Base64 base64 = new Base64();
		return new String(base64.decode(decode.getBytes()));
	}

	public static String generateRandomNumber(int length) {
		int max = 99999999;
		int min = 10;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		String random = StringUtils.leftPad(String.valueOf(randomNum), length, Constantes.ZERO);
		if (random.length() > length) {
			random = random.substring(random.length() - length);
		}
		return random;
	}

	public static String castReniectDateToFromat(String reniecDate) {
		String anio = reniecDate.substring(0, 4);
		String mes = reniecDate.substring(4, 6);
		String dia = reniecDate.substring(6, 8);
		String fechaNueva = dia + "/" + mes + "/" + anio;
		return fechaNueva;
	}

	public static Date castReniecDateToDate(String reniecDate) {
		String anio = reniecDate.substring(0, 4);
		String mes = reniecDate.substring(4, 6);
		String dia = reniecDate.substring(6, 8);
		String fechaNueva = dia + "/" + mes + "/" + anio;
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		try {
			date1 = formatter1.parse(fechaNueva);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1;
	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	public static String getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfHour = new SimpleDateFormat(HOUR_FORMAT);
		String hour = sdfHour.format(cal.getTime());
		return hour;
	}

	/**
	 * @param target hour to check
	 * @param start  interval start
	 * @param end    interval end
	 * @return true true if the given hour is between
	 */
	public static boolean isHourInInterval(String target, String start, String end) {
		return ((target.compareTo(start) >= 0) && (target.compareTo(end) <= 0));
	}

	/**
	 * @param start interval start
	 * @param end   interval end
	 * @return true true if the current hour is between
	 */
	public static boolean isNowInInterval(String start, String end) {
		return isHourInInterval(getCurrentHour(), start, end);
	}
	
	
	public static String getFechaTraceId() {
		 DateFormat simple = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 Date hoy = new Date();
		 return simple.format(hoy);
	}
	
	public static void saveInSesion(String llave, String valor) {
		Sesion sesion = new Sesion(llave, valor);
		sessionAdmin.insertarValorSesion(llave, sesion);
	}
	
	public static Object obtenerValorSesion(String llave) {
		return sessionAdmin.extrarValorSesion(llave);
	}
	
	public static void imprimirSesion() {
		 sessionAdmin.imprimirSesion();
	}
	
	public static Boolean validEmail(String email) {
		Pattern pattern = Pattern.compile(Constantes.PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
