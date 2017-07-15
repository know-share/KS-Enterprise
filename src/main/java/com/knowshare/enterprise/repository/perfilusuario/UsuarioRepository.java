/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoUsuariosEnum;

/**
 * @author miguel
 *
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId>{
	
	Usuario findByUsernameIgnoreCase(String username);
	
	Usuario findByPasswordAndUsernameIgnoreCase(String password,String username);
	
	@Query("{'username':{$ne:?0},"
			+ "'seguidores.username':{$ne:?0},"
			+ "'amigos.username':{$ne:?0},"
			+ "'tipo':?1}")
	List<Usuario> findMyNoConnections(String username,TipoUsuariosEnum tipo);

}
