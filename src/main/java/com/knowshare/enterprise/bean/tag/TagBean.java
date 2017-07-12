/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.repository.idea.TagRepository;
import com.knowshare.entities.idea.Tag;

/**
 * @author Pablo Gaitan
 *
 */
public class TagBean implements TagFacade{

	@Autowired
	private TagRepository tagRep;
	
	@Override
	public List<Tag> findAll() {
		return tagRep.findAll();
	}

	
}
