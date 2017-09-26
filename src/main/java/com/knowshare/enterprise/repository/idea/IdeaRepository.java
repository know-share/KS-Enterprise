/**
 * 
 */
package com.knowshare.enterprise.repository.idea;

import org.springframework.stereotype.Repository;

import com.knowshare.entities.idea.Idea;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("{'usuario.$id' : ?0, 'tipo' : {$ne:'PR'}}")
	List<Idea> findIdeaByUsuarioProyecto(ObjectId username);
	
	@Query(value="{'usuario.$id':?0, 'compartida':false}",count=true)
	Long countByUsuario(ObjectId id);
	
	@Query("{'usuario.$id' : {$in:?0}}")
	Page<Idea> findIdeaRed(List<ObjectId> ids,Pageable pageable);
	
	@Query("{'usuario.$id' : {$in:?0},'tipo':'?1'}")
	List<Idea> findIdea(List<ObjectId> ids,Sort sort,String tipo);
}
