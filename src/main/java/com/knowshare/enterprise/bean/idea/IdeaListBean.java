/**
 * 
 */
package com.knowshare.enterprise.bean.idea;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * @author pablo
 *
 */
@Component
public class IdeaListBean implements IdeaListFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	@Autowired
	private UsuarioRepository usuRep;
	
	public List<IdeaDTO> find10(){
		List<IdeaDTO> ret = new ArrayList<>();
		List<Idea> lista = ideaRep.find10();
		for (Idea idea : lista) {
			ret.add(MapEntities.mapIdeaToDTO(idea));
		}
		return ret;
	}
	
	public List<IdeaDTO> findByUsuario(String username){
		List<IdeaDTO> ret = new ArrayList<>();
		Usuario u = usuRep.findByUsernameIgnoreCase(username);
		List<Idea> lista = ideaRep.findIdeaByUsuario(u);
		for (Idea idea : lista) {
			ret.add(MapEntities.mapIdeaToDTO(idea));
		}
		return ret;
	}
	

}
