/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.idea.TagRepository;
import com.knowshare.entities.idea.Tag;

/**
 * {@link TagModFacade}
 * @author Pablo Gaitan
 *
 */
@Component
public class TagModBean implements TagModFacade {

	@Autowired
	 private TagRepository tagRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public boolean update(Tag tag) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(tag.getId()));
		update.set("nombre", tag.getNombre());
		return mongoTemplate.updateFirst(query, update, Tag.class).getN() > 0;
	}

	@Override
	public boolean delete(String id) {
		return tagRepository.removeById(id)==1;
	}

	@Override
	public boolean create(String nombre) {
		if(nombre!=null) {	
			Tag tag = new Tag()
				.setNombre(nombre)
				.setId(new ObjectId().toString());
			if(tagRepository.insert(tag)!=null) 
				return true;
		}
		return false;
	}	
}
