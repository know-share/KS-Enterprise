package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Idea}
 * @author Pablo Gaitán
 *
 */
public interface IdeaListFacade {
	
	Page<IdeaDTO> findByUsuario(String username, String usernameProfile,Integer page,long timestamp);
	
	OperacionIdea isLight(Idea idea, String username);
	
	IdeaDTO findById(String id, String username);
	
	List<IdeaDTO> findByUsuarioProyecto(String username);
	
	List<OperacionIdea> findOperaciones(String id,String tipo);
}
