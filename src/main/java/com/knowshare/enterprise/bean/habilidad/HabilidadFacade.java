/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * @author Miguel Montañez
 *
 */
public interface HabilidadFacade {
	
	void createHabilidad(String nombre);
	
	List<HabilidadDTO> getHabilidades(String carrera);
	
	List<HabilidadDTO> getHabilidadesProfesionales(String carrera);
	
	List<ObjectId> buscarPorNombre(String nombre);
	
	Page<Habilidad> getAll(Integer page);
	
	List<HabilidadDTO> getAll();
	
	boolean update (HabilidadDTO habilidad);
	
	boolean delete(ObjectId id);
	
	boolean create (HabilidadDTO habilidad);
}
