/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Pablo Gaitan
 *
 */
public interface IdeaFacade {
	
	IdeaDTO crearIdea(IdeaDTO dto);
	
	IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	
	Page<IdeaDTO> findByUsuario(String username,String usernameProfile,Integer page, long timestamp);
	
	IdeaDTO findById(String id,String username);
	
	IdeaDTO compartir(IdeaDTO dto,String username);
	
	Page<IdeaDTO> findByUsuarioProyecto(String username,Integer page,long timestamp);
	
	List<OperacionIdea> findOperaciones(String id,String tipo);
	
	IdeaDTO cambiarEstado(IdeaDTO dto);
	
	
}
