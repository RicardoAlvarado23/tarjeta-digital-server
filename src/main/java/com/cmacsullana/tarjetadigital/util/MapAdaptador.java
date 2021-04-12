package com.cmacsullana.tarjetadigital.util;

import java.util.HashMap;

import com.cmacsullana.tarjetadigital.constants.Constantes;
import com.cmacsullana.tarjetadigital.exceptions.MapNullException;

/**
 * 
 * @author riap
 *
 * @param <T>
 */
public class MapAdaptador<T> extends HashMap<String, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public T get(Object key) {
		try {
			return super.get(key);
		} catch (Exception e) {
			throw new MapNullException(Constantes.RESPUESTA_CONTROLADOR.ADAP_ERROR_CAMPO_NO_ENCONTRADO, "Campo " + key + " no encontrado",
					e.getMessage());
		}
	}

}