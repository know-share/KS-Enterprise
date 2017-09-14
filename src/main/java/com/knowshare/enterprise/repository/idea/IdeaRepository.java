/**
 * 
 */
package com.knowshare.enterprise.repository.idea;

import org.springframework.stereotype.Repository;

import com.knowshare.entities.idea.Idea;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repositorio con los metodos CRUD de {@link Idea idea}
 * @author Pablo Gaitan
 *
 */
@Repository
public interface IdeaRepository extends MongoRepository<Idea,String>{
	
	//@Query("{$limit: 10}")
	List<Idea> findAll();
	
	@Query("{'usuario.$id' : ?0}")
	List<Idea> findIdeaByUsuario(ObjectId username);
	
	@Query("{'usuario.$id' : ?0, 'tipo' : {$ne:'PR'}}")
	List<Idea> findIdeaByUsuarioProyecto(ObjectId username);
	
	@Query(value="{'usuario.$id':?0}",count=true)
	Long countByUsuario(ObjectId id);
	
	@Query("{'usuario.$id' : {$in:?0}}")
	List<Idea> findIdeaRed(List<ObjectId> ids);
	
	@Query("{'usuario.$id' : {$in:?0},'tipo':'PR'}")
	List<Idea> findIdeaRedProyectos(List<ObjectId> ids);
	
	@Query("{'usuario.$id' : {$in:?0},'tipo':'PE'}")
	List<Idea> findIdeaRedEmpezar(List<ObjectId> ids);
	
	@Query("{'usuario.$id' : {$in:?0},'tipo':'PC'}")
	List<Idea> findIdeaContinuar(List<ObjectId> ids,Sort sort);
	
	@Query("{'usuario.$id' : {$in:?0},'tipo':'NU'}")
	List<Idea> findIdeaNueva(List<ObjectId> ids,Sort sort);
}
