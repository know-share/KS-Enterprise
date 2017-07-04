/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * @author miguel
 *
 */
@Repository
public interface PersonalidadRepository extends MongoRepository<Personalidad, String>{

}
