/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import com.knowshare.dto.academia.CarreraDTO;

/**
 * Clase con métodos que modifican la base de datos (CREATE, DELETE, UPDATE).
 * @author Felipe Bautista
 *
 */
public interface CarreraModFacade {

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
	boolean delete(String id);
	
	/**
	 * Descripción: crea una Carrera en la base de datos.
	 * @param carrera
	 * @return Verdadero si la crea, falso si no.
	 */
	boolean create (CarreraDTO carrera);
	
	/**
	 * Descripción: Actualiza los énfasis de una carrera
	 * @param carrera
	 * @return Verdadero si actualizó correctatamente, falso si no
	 */
	boolean updateEnfasis(CarreraDTO carrera);
}
