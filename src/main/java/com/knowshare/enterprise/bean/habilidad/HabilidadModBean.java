/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.habilidad.HabilidadRepository;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * @author miguel
 *
 */
@Component
public class HabilidadModBean implements HabilidadModFacade{
	
	@Autowired
	private HabilidadRepository habilidadRepository;
	
	@Override
	public void createHabilidad(String nombre) {
		final Habilidad habilidad = new Habilidad();
		habilidad.setNombre(nombre);
		habilidadRepository.insert(habilidad);
	}
}
