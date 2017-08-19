/**
 * 
 */
package com.knowshare.enterprise.repository.ludificacion;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.ludificacion.Insignia;

/**
 * @author Miguel Montañez
 *
 */
@Repository
public interface InsigniaRepository extends MongoRepository<Insignia, String>{

}
