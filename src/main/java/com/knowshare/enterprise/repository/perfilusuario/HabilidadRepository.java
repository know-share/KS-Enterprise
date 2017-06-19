	/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * Repositorio con metodos CRUD para la clase Habilidad.
 * @author miguel
 *
 */
@Repository
public interface HabilidadRepository extends MongoRepository<Habilidad, String> {
	
	@Query("{$or:[{tipo:'PROFESIONALES',carrera.$id:?0},{tipo:'PERSONALES'}]}")
	List<Habilidad> getHabilidades(String carrera);

}
