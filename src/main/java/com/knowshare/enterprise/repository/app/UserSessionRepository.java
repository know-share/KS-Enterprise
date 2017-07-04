/**
 * 
 */
package com.knowshare.enterprise.repository.app;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.app.UserSession;

/**
 * @author Pablo Gaitan
 *
 */
@Repository
public interface UserSessionRepository extends MongoRepository<UserSession, ObjectId>{
	UserSession findByToken(String token);
	
	Long removeByToken(String token);
}
