/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.entities.idea.Tag;

/**
 * @author Pablo Gaitan
 *
 */
@Component
public class TagBean implements TagFacade{
	
	@Autowired
	private TagListFacade tagListBean;
	
	@Autowired
	private TagModFacade tagModBean;
	
	@Override
	public List<Tag> findAll() {
		return tagListBean.findAll();
	}

	@Override
	public boolean create (String nombre) {
		return tagModBean.create(nombre);
	}

	@Override
	public boolean update(Tag tag) {
		return tagModBean.update(tag);
	}

	@Override
	public boolean delete(String tag) {
		return tagModBean.delete(tag);
	}
	
	
}
