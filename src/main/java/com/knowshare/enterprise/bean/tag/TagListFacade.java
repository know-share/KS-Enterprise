/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import com.knowshare.entities.idea.Tag;

/**
 * @author miguel
 *
 */
public interface TagListFacade {
	
	List<Tag> findAll();

}
