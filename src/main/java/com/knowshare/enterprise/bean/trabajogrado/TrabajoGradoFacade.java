/**
 * 
 */
package com.knowshare.enterprise.bean.trabajogrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface TrabajoGradoFacade {
	
	/**
	 * Obtiene todos los trabajos de grado registrados
	 * en la base de datos.
	 * @return Lista de {@link TrabajoGrado trabajos de grado}
	 */
	List<TrabajoGrado> findAll();

}
