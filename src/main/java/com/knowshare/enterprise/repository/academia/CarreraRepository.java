/**
 * 
 */
package com.knowshare.enterprise.repository.academia;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.academia.Carrera;

/**
 * @author miguel
 *
 */
@Repository
public interface CarreraRepository extends MongoRepository<Carrera, String>{
	
	Carrera findByNombreIgnoreCase(String nombre);
	
}
