/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * @author HP
 *
 */
@Component
public class IdeaModBean implements IdeaModFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	public Idea crearIdea(IdeaDTO dto){
		try {
			return ideaRep.insert(MapEntities.mapDtoToIdea(dto));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public Idea agregarOperacion(IdeaDTO dto , OperacionIdea operacion){
		Idea idea;
		try {
			idea = MapEntities.mapDtoToIdea(dto);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		idea.getOperaciones().add(operacion);
		if(operacion.getTipo().equals(TipoOperacionEnum.COMENTARIO)){
			idea.setComentarios(idea.getComentarios()+1);
		}else
			idea.setLights(idea.getLights()+1);
		
		return ideaRep.save(idea);
	}
}
