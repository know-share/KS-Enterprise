/**
 * 
 */
package com.knowshare.enterprise.repository.academia;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * Repositorio de trabajo grado. Permite consultas y operaciones
 * con la entidad {@link TrabajoGrado} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface TrabajoGradoRepository extends MongoRepository<TrabajoGrado,ObjectId> {

}
