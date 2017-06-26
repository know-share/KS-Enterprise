package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * 
 * @author miguel
 *
 */
public interface HabilidadListFacade {
	
	Habilidad findOne(String nombre);
	
	List<HabilidadDTO> getHabilidades(String carrera);
	
	List<HabilidadDTO> getHabilidadesProfesionales(String carrera);
	
	/**
	 * Method not tested, this is for testing purpose
	 * @return
	 */
	Page<Habilidad> getAll();
}
