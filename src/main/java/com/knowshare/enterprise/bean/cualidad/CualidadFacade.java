/**
 * 
 */
package com.knowshare.enterprise.bean.cualidad;

import java.util.List;

import com.knowshare.dto.perfilusuario.CualidadDTO;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface CualidadFacade {

	/**
	 * Obtiene todas las cualidades registradas en el 
	 * sistema
	 * @return Lista de {@link CualidadDTO cualidades}
	 */
	List<CualidadDTO> getAll();
}
