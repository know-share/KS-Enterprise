/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Cualidad;

/**
 * Repositorio de cualidad. Permite consultas y operaciones
 * con la entidad {@link Cualidad} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface CualidadRepository extends MongoRepository<Cualidad, ObjectId>{

}
