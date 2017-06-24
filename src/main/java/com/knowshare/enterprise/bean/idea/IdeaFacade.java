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
	
	public Idea crearIdea(IdeaDTO dto);
	public void agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	public List<IdeaDTO> find10();
	
}
