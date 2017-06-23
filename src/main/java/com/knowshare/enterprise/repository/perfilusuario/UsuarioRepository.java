/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Usuario;

/**
 * @author miguel
 *
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId>{
	
	Usuario findByUsernameIgnoreCase(String username);

}
