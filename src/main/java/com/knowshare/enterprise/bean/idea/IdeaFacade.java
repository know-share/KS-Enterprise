/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * @author Pablo Gaitan
 *
 */
public interface IdeaFacade {
	
	IdeaDTO crearIdea(IdeaDTO dto);
	IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	List<IdeaDTO> find10(String username);
	List<IdeaDTO> findByUsuario(String username);
	IdeaDTO findById(String id,String username);
	IdeaDTO compartir(IdeaDTO dto,String username);
	List<IdeaDTO> findByUsuarioProyecto(String username);
	List<OperacionIdea> findOperaciones(String id,String tipo);
}
