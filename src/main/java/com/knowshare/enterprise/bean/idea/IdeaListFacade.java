package com.knowshare.enterprise.bean.idea;

import java.util.List;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;

public interface IdeaListFacade {
	
	List<IdeaDTO> find10(String username);
	List<IdeaDTO> findByUsuario(String username);
	OperacionIdea isLight(Idea idea, String username);
	IdeaDTO findById(String id, String username);
	List<IdeaDTO> findByUsuarioProyecto(String username);
	List<OperacionIdea> findOpreaciones(String id,String tipo);
}
