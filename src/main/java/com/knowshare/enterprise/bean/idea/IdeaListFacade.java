package com.knowshare.enterprise.bean.idea;

import java.util.List;

import com.knowshare.dto.idea.IdeaDTO;

public interface IdeaListFacade {
	
	List<IdeaDTO> find10();
	List<IdeaDTO> findByUsuario(String username);
}
