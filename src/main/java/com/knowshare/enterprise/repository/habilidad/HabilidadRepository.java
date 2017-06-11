/**
 * 
 */
package com.knowshare.enterprise.repository.habilidad;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * Repositorio con metodos CRUD para la clase Habilidad.
 * @author miguel
 *
 */
@Repository
public interface HabilidadRepository extends MongoRepository<Habilidad, String> {

}
