/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import com.knowshare.entities.idea.Tag;

/**
 * @author Pablo Gaitan
 *
 */
public interface TagFacade {
	
	List<Tag> findAll();
	boolean create (String tag);
	boolean update (Tag tag);
	boolean delete(String tag);
}
