/**
 * 
 */
package com.knowshare.enterprise.bean.gusto;

import java.util.List;

import com.knowshare.entities.perfilusuario.Gusto;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Gusto}
 * @author Miguel Montañez
 *
 */
public interface GustoListFacade {
	
	/**
	 * Obtiene todos los gustos registrados en
	 * la aplicación
	 * @return Lista de {@link Gusto gustos}
	 */
	List<Gusto> getAllGustos();

}
