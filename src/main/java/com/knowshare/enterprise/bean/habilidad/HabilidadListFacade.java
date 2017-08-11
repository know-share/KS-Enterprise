package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * 
 * @author miguel
 *
 */
public interface HabilidadListFacade {
	
	List<HabilidadDTO> getHabilidades(String carrera);
	
	List<HabilidadDTO> getHabilidadesProfesionales(String carrera);
	
	List<ObjectId> buscarPorNombre(String nombre);
	
	/**
	 * Method not tested, this is for testing purpose
	 * @return
	 */
	Page<Habilidad> getAll(Integer page);
}
