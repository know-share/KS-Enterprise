/**
 * 
 */
package com.knowshare.enterprise.bean.idea;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.InfoUsuario;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * {@link IdeaListFacade}
 * @author Pablo Gaitan
 *
 */
@Component
public class IdeaListBean implements IdeaListFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	@Autowired
	private UsuarioRepository usuRep;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<IdeaDTO> find10(String username){
		List<IdeaDTO> ret = new ArrayList<>();
		List<Idea> lista = ideaRep.findAll();
		IdeaDTO dto;
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
		IdeaDTO dto;
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
			if(o.getTipo().equals(TipoOperacionEnum.LIGHT) &&
					o.getUsername().equalsIgnoreCase(username))
				return o;
		}
		return null;
	}

	@Override
	public IdeaDTO findById(String id, String username) {
		Idea idea = ideaRep.findOne(id);
		if(null != idea){
			IdeaDTO dto = MapEntities.mapIdeaToDTO(idea);
			if(isLight(idea, username)!=null)
				dto.setIsLight(true);
			else
				dto.setIsLight(false);
			return dto;
		}
		return null;
	}

	@Override
	public List<IdeaDTO> findByUsuarioProyecto(String username) {
		final Usuario usu = usuRep.findByUsernameIgnoreCase(username);
		List<Idea> idea =  ideaRep.findIdeaByUsuarioProyecto(usu.getId());
		List<IdeaDTO> dots = new ArrayList<>();
		IdeaDTO dto;
		for (Idea ide : idea) {
			dto = MapEntities.mapIdeaToDTO(ide);
			dots.add(dto);
		}
		return dots;
	}
	
	public List<OperacionIdea> findOperaciones(String id,String tipo){
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

	@Override
	public List<IdeaDTO> findRed(String username) {
		final Usuario usu = usuRep.findByUsernameIgnoreCase(username);
		List<InfoUsuario> red = usu.getAmigos();
		red.addAll(usu.getSiguiendo());
		List<String> usernamesRed = new ArrayList<>();
		for (InfoUsuario inf : red)
			usernamesRed.add(inf.getUsername());
		List<ObjectId> usuariosId = usuRep.findUsuariosByUsername(usernamesRed)
				.stream()
				.map(Usuario::getId)
				.collect(Collectors.toList());
		List<Idea> ideas = ideaRep.findIdeaRed(usuariosId);
		List<IdeaDTO> dtos = new ArrayList<>();
		for (Idea i : ideas) {
			dtos.add(MapEntities.mapIdeaToDTO(i));
		}
		return dtos;
	}

	@Override
	public List<IdeaDTO> findByTags(List<Tag> tags) {
//		List<Idea> ideas = ideaRep.findIdeaByTags(tags.stream().map(Tag::getId).collect(Collectors.toList()));
		List<String> ids = tags.stream().map(Tag::getId).collect(Collectors.toList());
		final Query query = new Query(Criteria.where("tags")
				.all(tags));
		List<Idea> ideas = mongoTemplate.find(query, Idea.class);
		List<IdeaDTO> dtos = new ArrayList<>();
		for (Idea i : ideas) {
			dtos.add(MapEntities.mapIdeaToDTO(i));
		}
		return dtos;
	}
	
}
