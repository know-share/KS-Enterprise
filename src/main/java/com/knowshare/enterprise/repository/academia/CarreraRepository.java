/**
 * 
 */
package com.knowshare.enterprise.repository.academia;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.academia.Carrera;

/**
 * Esta clase aprovecha la implementación básica de algunas funcionas por medio de @Repository
 * @author Miguel Montañez
 *
 */
@Repository
public interface CarreraRepository extends MongoRepository<Carrera, String>{
	
	/**
	 * Descripción: busca una Carrera por el nombre.
	 * @param nombre
	 * @return La carrera si la encontró, null si no encuentra nada.
	 */
	Carrera findByNombreIgnoreCase(String nombre);
	
	/**
	 * Descripción: elimina una Carrera dado su Id.
	 * @param id
	 * @return La cantidad de carreras que elimino, lo idea es 1.
	 */
	Long removeById(String id);
	
}
