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
	Page<Habilidad> getAll();
}
