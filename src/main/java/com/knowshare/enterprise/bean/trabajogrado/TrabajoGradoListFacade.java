/**
 * 
 */
package com.knowshare.enterprise.bean.trabajogrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * Se encarga de operaciones de consulta sobre la base 
 * de dato que no hagan modificaciones sobre la entidad
 * {@link TrabajoGrado}
 * @author Pablo Gaitan
 *
 */
public interface TrabajoGradoListFacade {
	
	/**
	 * Obtiene todos los trabajos de grado registrados
	 * en la base de datos.
	 * @return Lista de {@link TrabajoGrado trabajos de grado}
	 */
	List<TrabajoGrado> findAll();

}
