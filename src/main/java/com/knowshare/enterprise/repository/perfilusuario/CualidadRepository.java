/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Cualidad;

/**
 * @author miguel
 *
 */
@Repository
public interface CualidadRepository extends MongoRepository<Cualidad, ObjectId>{

}
