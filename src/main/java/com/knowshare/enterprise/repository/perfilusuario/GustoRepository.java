/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Gusto;

/**
 * @author miguel
 *
 */
@Repository
public interface GustoRepository extends MongoRepository<Gusto, ObjectId> {

}
