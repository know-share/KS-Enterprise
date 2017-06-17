package com.knowshare.enterprise.bean.habilidad;

import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * 
 * @author miguel
 *
 */
public interface HabilidadListFacade {
	
	Habilidad findOne(String nombre);
}
