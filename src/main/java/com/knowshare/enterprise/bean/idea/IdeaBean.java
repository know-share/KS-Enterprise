/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * @author Pablo Gaitan
 *
 */
@Component
public class IdeaBean implements IdeaFacade{
	
	@Autowired
	private IdeaModFacade ideaMod;
	
	@Autowired
	private IdeaListFacade ideaList;
	
	public IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion){
		return ideaMod.agregarOperacion(dto, operacion);
	}
	
	public IdeaDTO crearIdea(IdeaDTO dto){
		return ideaMod.crearIdea(dto);
	}
	
	public List<IdeaDTO> find10(String username){
		return ideaList.find10(username);
	}
	
	public List<IdeaDTO> findByUsuario(String username){
		return ideaList.findByUsuario(username);
	}

	@Override
	public IdeaDTO findById(String id, String username) {
		return ideaList.findById(id, username);
	}
	
	
}
