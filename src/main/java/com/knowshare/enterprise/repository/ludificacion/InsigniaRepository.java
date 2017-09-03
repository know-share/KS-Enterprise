/**
 * 
 */
package com.knowshare.enterprise.repository.ludificacion;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.ludificacion.Insignia;

/**
 * Repositorio de insignia. Permite consultas y operaciones
 * con la entidad {@link Insignia} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface InsigniaRepository extends MongoRepository<Insignia, String>{

}
