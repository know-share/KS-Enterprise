/**
 * 
 */
package com.knowshare.enterprise.bean.tag;

import com.knowshare.entities.idea.Tag;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad {@link Tag}.
 * @author Pablo Gaitan
 *
 */
public interface TagModFacade {
	
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
	boolean delete(String id);

	/**
	 * Crea un tag con el nombre especificado
	 * por el parámetro
	 * @param tag a crear
	 * @return verdadero si pudo ejecutar la acción, de
	 * lo contrario falso.
	 */
	boolean create (String nombre);

}
