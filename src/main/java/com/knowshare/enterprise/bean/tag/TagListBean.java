/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.idea.TagRepository;
import com.knowshare.entities.idea.Tag;

/**
 * @author miguel
 *
 */
@Component
public class TagListBean implements TagListFacade{

	@Autowired
	private TagRepository tagRep;
	
	@Override
	public List<Tag> findAll() {
		return tagRep.findAll();
	}
}
