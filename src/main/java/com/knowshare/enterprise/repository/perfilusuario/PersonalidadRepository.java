/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * Repositorio de personalidad. Permite consultas y operaciones
 * con la entidad {@link Personalidad} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface PersonalidadRepository extends MongoRepository<Personalidad, String>{

}
