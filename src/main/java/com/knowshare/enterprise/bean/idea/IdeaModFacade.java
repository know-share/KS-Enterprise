/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * @author pablo
 *
 */
public interface IdeaModFacade {
	
	IdeaDTO crearIdea(IdeaDTO dto);
	
	IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	
	IdeaDTO compartir(IdeaDTO dto,String username);
}
