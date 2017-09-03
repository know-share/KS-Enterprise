/**
 * 
 */
package com.knowshare.enterprise.bean.cualidad;

import java.util.List;

import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.entities.perfilusuario.Cualidad;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Cualidad}
 * @author Miguel Montañez
 *
 */
public interface CualidadListFacade {
	
	/**
	 * Obtiene todas las cualidades registradas en el 
	 * sistema
	 * @return Lista de {@link CualidadDTO cualidades}
	 */
	List<CualidadDTO> getAll();

}
