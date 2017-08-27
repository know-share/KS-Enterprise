/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;

/**
 * Esta clase tiene todos los métodos que se conectan a la base de 
 * datos y modifican o leen Carrera.
 * @author Miguel Montañez
 *
 */
public interface CarreraFacade {
	
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
	
	/**
	 * Descripción: actualiza los datos de una Carrera en la base de datos.
	 * @param carrera 
	 * @return Verdadero si actualizó correctamente, falso si no.
	 */
	boolean update (CarreraDTO carrera);
	
	/**
	 * Descripción: elimina una Carrera recibiendo su ID en la base de datos.
	 * @param id
	 * @return Verdadero si elimino correctamente, falso si no.
	 */
	boolean delete(String carrera);
	
	/**
	 * Descripción: crea una Carrera en la base de datos.
	 * @param carrera
	 * @return Verdadero si la crea, falso si no.
	 */
	boolean create (CarreraDTO carrera);
	
}
