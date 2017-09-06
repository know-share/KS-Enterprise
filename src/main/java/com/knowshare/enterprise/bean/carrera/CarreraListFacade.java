/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;

/**
 * Esta clase busca y obtiene información de la base de datos.
 * @author Miguel Montañez
 *
 */
public interface CarreraListFacade {
	
	/**
	 * Descripción: busca todas las Carreras en la base de datos.
	 * @return Lista de Carreras, null si no encuentra nada.
	 */
	List<CarreraDTO> getAllCarreras();
	
	/**
	 * Descripción: busca los énfasis y areas de conocimiento de una carrera
	 * @param carrera
	 * @return Un EnfasisAreaConocmientoDTO con los énfasis y 
	 * areas de conocmiento de una Carrera, null si no encuentra la Carrera.
	 */
	EnfasisAreaConocimientoDTO getEnfasisAreaConocimiento(String carrera);

}
