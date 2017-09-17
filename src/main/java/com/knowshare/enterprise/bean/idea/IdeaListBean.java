/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	private static final int PAGE_SIZE = 10;
	
	public Page<IdeaDTO> findByUsuario(String username,String usernameProfile,
			Integer page,long timestamp){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(timestamp);
		final Usuario usu = usuRep.findByUsernameIgnoreCase(usernameProfile);
		final PageRequest request = 
				new PageRequest(page, PAGE_SIZE,new Sort(Direction.DESC, "fechaCreacion"));
		final Query query = new Query(
				Criteria.where("fechaCreacion").lt(calendar.getTime())
					.and("usuario.$id").is(usu.getId()))
			.with(request);
		final List<Idea> idea = mongoTemplate.find(query, Idea.class);
		long total = mongoTemplate.count(query, Idea.class);
		final List<IdeaDTO> dots = new ArrayList<>();
		IdeaDTO dto;
		for (Idea ide : idea) {
			dto = MapEntities.mapIdeaToDTO(ide);
			if(isLight(ide, username)!=null)
				dto.setIsLight(true);
			else
				dto.setIsLight(false);
			dots.add(dto);
		}
		return new PageImpl<>(dots, request, total);
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


	
}
