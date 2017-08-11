package com.knowshare.enterprise.bean.habilidad;

import com.knowshare.dto.perfilusuario.HabilidadDTO;

/**
 * 
 * @author miguel
 *
 */
public interface HabilidadModFacade {

	/**
	 * Crea la habilidad dado el nombre
	 * @param nombre
	 */
	void createHabilidad(String nombre);
	boolean update (HabilidadDTO habilidad);
	boolean delete(String id);
	boolean create (HabilidadDTO habilidad);
}
