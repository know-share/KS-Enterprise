/**
 * 
 */
package com.knowshare.enterprise.repository.perfilusuario;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * Repositorio con metodos CRUD para la clase Habilidad.
 * @author miguel
 *
 */
@Repository
public interface HabilidadRepository extends MongoRepository<Habilidad, String>{
	
	@Query(value="{$or:[{'tipo':'PERSONALES'},{'carrera.$id':?0, 'tipo':'PROFESIONALES'}]}")
	List<Habilidad> getHabilidades(String carrera);
	
	@Query("{'tipo':'PROFESIONALES','carrera.$id':?0}")
	List<Habilidad> getHabilidadesProfesionales(String carrera);
<<<<<<< HEAD
	
	Habilidad findByNombre(String nombre);
	
	long removeById(ObjectId id);
=======
>>>>>>> a1bd82fe5624ca9901f2ac1bb71624eab2356107
}
