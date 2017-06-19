/**
 * 
 */
package com.knowshare.enterprise.repository.idea;

import org.springframework.stereotype.Repository;

import com.knowshare.entities.idea.Idea;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repositorio con los metodos CRUD de idea
 * @author pablo
 *
 */
@Repository
public interface IdeaRepository extends MongoRepository<Idea,String>{
	
	@Query("{$limit: 10}")
	public List<Idea> find10();
}
