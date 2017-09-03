/**
 * 
 */
package com.knowshare.enterprise.bean.personalidad;

import java.util.List;

import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface PersonalidadFacade {
	
	/**
	 * Obtiene todas las personalidades registradas
	 * en la aplicación
	 * @return Lista de {@link Personalidad personalidades}
	 */
	List<Personalidad> getAllPersonalidades();

}
