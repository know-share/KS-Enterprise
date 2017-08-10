/**
 * 
 */
package com.knowshare.enterprise.repository.idea;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.knowshare.entities.idea.Tag;

/**
 * @author Pablo Gaitan
 *
 */
public interface TagRepository extends MongoRepository<Tag, String>{

	Long removeById(String id);
}
