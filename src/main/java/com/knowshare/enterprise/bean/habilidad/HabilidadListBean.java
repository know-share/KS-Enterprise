package com.knowshare.enterprise.bean.habilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.habilidad.HabilidadRepository;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * 
 * @author miguel
 *
 */
@Component
public class HabilidadListBean implements HabilidadListFacade{
	
	@Autowired
	private HabilidadRepository habilidadRepository;
	
	public Habilidad findOne(String nombre){
		return habilidadRepository.findOne(nombre);
	}
}
