/**
 * 
 */
package com.knowshare.enterprise.repository.idea;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.knowshare.entities.idea.Tag;

/**
 * Repositorio de tag. Permite consultas y operaciones
 * con la entidad {@link Tag} ante la base de datos.
 * @author Pablo Gaitan
 *
 */
public interface TagRepository extends MongoRepository<Tag, String>{

	Long removeById(String id);
}
