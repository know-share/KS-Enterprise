/**
 * 
 */
package com.knowshare.enterprise.repository.habilidad;

import org.springframework.stereotype.Repository;

import com.knowshare.entities.idea.Idea;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio con los metodos CRUD de idea
 * @author pablo
 *
 */
@Repository
public interface IdeaRepository extends MongoRepository<Idea,String>{

}
