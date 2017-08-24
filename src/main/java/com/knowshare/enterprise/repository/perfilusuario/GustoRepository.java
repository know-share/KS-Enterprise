/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Gusto;

/**
 * Repositorio de gusto. Permite consultas y operaciones
 * con la entidad {@link Gusto} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface GustoRepository extends MongoRepository<Gusto, ObjectId> {

}
