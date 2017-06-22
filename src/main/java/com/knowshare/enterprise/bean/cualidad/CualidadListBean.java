/**
 * 
 */
package com.knowshare.enterprise.bean.cualidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.perfilusuario.CualidadRepository;
import com.knowshare.entities.perfilusuario.Cualidad;

/**
 * @author miguel
 *
 */
@Component
public class CualidadListBean implements CualidadListFacade {
	
	@Autowired
	private CualidadRepository cualidadRepository;

	@Override
	public List<Cualidad> getAll() {
		return this.cualidadRepository.findAll();
	}

}
