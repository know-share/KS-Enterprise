/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import com.knowshare.entities.idea.Tag;

/**
 * @author Pablo Gaitan
 *
 */
public interface TagModFacade {
	

	boolean update (Tag tag);

	boolean delete(String id);

	boolean create (String nombre);

}
