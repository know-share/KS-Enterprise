/**
 * 
 */
package com.knowshare.enterprise.bean.personalidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.perfilusuario.PersonalidadRepository;
import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * @author miguel
 *
 */
@Component
public class PersonalidadListBean implements PersonalidadListFacade {
	
	@Autowired
	private PersonalidadRepository personalidadRepository;

	@Override
	public List<Personalidad> getAllPersonalidades() {
		return personalidadRepository.findAll();
	}

}
