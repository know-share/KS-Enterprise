/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import java.util.List;

import com.knowshare.entities.idea.Tag;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Pablo Gaitan
 *
 */
public interface TagFacade {
	
	/**
	 * Regresa todos los tags registrados en
	 * la aplicación
	 * @return Lista de {@link Tag tags}
	 */
	List<Tag> findAll();
	
	/**
	 * Crea un tag con el nombre especificado
	 * por el parámetro
	 * @param tag a crear
	 * @return verdadero si pudo ejecutar la acción, de
	 * lo contrario falso.
	 */
	boolean create (String tag);
	
	/**
	 * Actualiza el tag especificado
	 * @param tag a actualizar
	 * @return verdadero si pudo ejecutar la acción, de
	 * lo contrario falso.
	 */
	boolean update (Tag tag);
	
	/**
	 * Elimina el tag con el nombre recibido
	 * como parámetro
	 * @param tag a eliminar
	 * @return verdadero si pudo ejecutar la acción, de
	 * lo contrario falso.
	 */
	boolean delete(String tag);
}
