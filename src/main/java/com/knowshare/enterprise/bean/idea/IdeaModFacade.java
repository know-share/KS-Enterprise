/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad {@link Idea}.
 * @author Pablo Gaitán
 *
 */
public interface IdeaModFacade {
	
	IdeaDTO crearIdea(IdeaDTO dto);
	
	IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	
	IdeaDTO compartir(IdeaDTO dto,String username);
	
	IdeaDTO cambiarEstado(IdeaDTO dto);
}
