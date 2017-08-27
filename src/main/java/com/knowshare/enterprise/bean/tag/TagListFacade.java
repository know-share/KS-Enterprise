/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import com.knowshare.entities.idea.Tag;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Tag}
 * @author Miguel Montañez
 *
 */
public interface TagListFacade {
	
	/**
	 * Regresa todos los tags registrados en
	 * la aplicación
	 * @return Lista de {@link Tag tags}
	 */
	List<Tag> findAll();

}
