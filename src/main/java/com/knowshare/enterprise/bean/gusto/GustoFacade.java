/**
 * 
 */
package com.knowshare.enterprise.bean.gusto;

import java.util.List;

import com.knowshare.entities.perfilusuario.Gusto;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface GustoFacade {
	
	/**
	 * Obtiene todos los gustos registrados en
	 * la aplicación
	 * @return Lista de {@link Gusto gustos}
	 */
	List<Gusto> getAllGustos();
}
