/**
 * 
 */
package com.knowshare.enterprise.repository.academia;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author miguel
 *
 */
@Repository
public interface TrabajoGradoRepository extends MongoRepository<TrabajoGrado,ObjectId> {

}
