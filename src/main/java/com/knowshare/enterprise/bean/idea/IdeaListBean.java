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
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * @author Pablo Gaitan
 *
 */
@Component
public class IdeaListBean implements IdeaListFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	@Autowired
	private UsuarioRepository usuRep;
	
	public List<IdeaDTO> find10(String username){
		List<IdeaDTO> ret = new ArrayList<>();
		List<Idea> lista = ideaRep.findAll();
		IdeaDTO dto = new IdeaDTO();
		for (Idea idea : lista) {
			dto = MapEntities.mapIdeaToDTO(idea);
			if(isLight(idea, username)!=null)
				dto.setIsLight(true);
			else
				dto.setIsLight(false);
			ret.add(dto);
		}
		return ret;
	}
	
	public List<IdeaDTO> findByUsuario(String username){
		final Usuario usu = usuRep.findByUsernameIgnoreCase(username);
		List<Idea> idea =  ideaRep.findIdeaByUsuario(usu.getId());
		List<IdeaDTO> dots = new ArrayList<>();
		IdeaDTO dto = new IdeaDTO();
		for (Idea ide : idea) {
			dto = MapEntities.mapIdeaToDTO(ide);
			if(isLight(ide, username)!=null)
				dto.setIsLight(true);
			else
				dto.setIsLight(false);
			dots.add(dto);
		}
		return dots;
	}
	
	public OperacionIdea isLight(Idea idea, String username){
		for (OperacionIdea o : idea.getOperaciones()) {
			if(o.getTipo().equals(TipoOperacionEnum.LIGHT))
				if(o.getUsername().equalsIgnoreCase(username))
					return o;
		}
		return null;
	}

	@Override
	public IdeaDTO findById(String id, String username) {
		Idea idea = ideaRep.findOne(id);
		IdeaDTO dto = MapEntities.mapIdeaToDTO(idea);
		if(isLight(idea, username)!=null)
			dto.setIsLight(true);
		else
			dto.setIsLight(false);
		return dto;
	}

	@Override
	public List<IdeaDTO> findByUsuarioProyecto(String username) {
		final Usuario usu = usuRep.findByUsernameIgnoreCase(username);
		List<Idea> idea =  ideaRep.findIdeaByUsuarioProyecto(usu.getId());
		List<IdeaDTO> dots = new ArrayList<>();
		IdeaDTO dto = new IdeaDTO();
		for (Idea ide : idea) {
			dto = MapEntities.mapIdeaToDTO(ide);
			dots.add(dto);
		}
		return dots;
	}
	
	public List<OperacionIdea> findOpreaciones(String id,String tipo){
		List<OperacionIdea> ret = new ArrayList<>();
		Idea i = ideaRep.findOne(id);
		if(tipo.equals("light")){
			for (OperacionIdea op : i.getOperaciones()) {
				if(op.getTipo().equals(TipoOperacionEnum.LIGHT)){
					ret.add(op);
				}
			}
		}else{
			for (OperacionIdea op : i.getOperaciones()) {
				if(op.getTipo().equals(TipoOperacionEnum.COMENTARIO)){
					ret.add(op);
				}
			}
		}
		return ret;
	}
	
	
	

}
