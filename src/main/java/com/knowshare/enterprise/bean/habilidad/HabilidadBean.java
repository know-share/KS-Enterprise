/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * @author miguel
 *
 */
@Component
public class HabilidadBean implements HabilidadFacade{
	
	@Autowired
	private HabilidadModFacade habilidadModBean;
	
	@Autowired
	private HabilidadListFacade habilidadListBean;

	@Override
	public void createHabilidad(String nombre) {
		habilidadModBean.createHabilidad(nombre);
	}

	@Override
	public List<HabilidadDTO> getHabilidades(String carrera) {
		return habilidadListBean.getHabilidades(carrera);
	}
	
	public Page<Habilidad> getAll(){
		return habilidadListBean.getAll();
	}

	@Override
	public List<HabilidadDTO> getHabilidadesProfesionales(String carrera) {
		return habilidadListBean.getHabilidadesProfesionales(carrera);
	}
}
