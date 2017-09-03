/**
 * 
 */
package com.knowshare.enterprise.bean.personalidad;

import java.util.List;

import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Personalidad}
 * @author Miguel Montañez
 *
 */
public interface PersonalidadListFacade {
	
	/**
	 * Obtiene todas las personalidades registradas
	 * en la aplicación
	 * @return Lista de {@link Personalidad personalidades}
	 */
	List<Personalidad> getAllPersonalidades();

}
