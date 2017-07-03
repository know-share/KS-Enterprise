/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * @author HP
 *
 */
public interface IdeaFacade {
	
	Idea crearIdea(IdeaDTO dto);
	Idea agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	List<IdeaDTO> find10();
	
}
