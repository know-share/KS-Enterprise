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


/**
 * Repositorio de usuario. Permite consultas y operaciones
 * con la entidad {@link Usuario} ante la base de datos.
 * @author Miguel Montañez
 *
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId>{
	
	Usuario findByUsernameIgnoreCase(String username);
	
	Usuario findByCorreoIgnoreCase(String correo);
	
	Usuario findByPasswordAndUsernameIgnoreCase(String password,String username);
	
	@Query("{'username':{$ne:?0},"
			+ "'seguidores.username':{$ne:?0},"
			+ "'amigos.username':{$ne:?0},"
			+ "'solicitudesAmistad':{$ne:?0},"
			+ "'tipo':{$ne:'ADMIN'}}")
	List<Usuario> findMyNoConnections(String username);
	
	@Query("{$text:{$search:?0,$diacriticSensitive:false}}")
	List<Usuario> searchByNombreOrApellido(String param);
	
	@Query(value="{'username': {$in:?0}}", fields="{'_id':1}")
	List<ObjectId> findUsuariosByUsername(List<String> username);
}
