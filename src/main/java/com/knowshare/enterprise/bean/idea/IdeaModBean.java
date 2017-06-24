/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

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
		return ideaRep.insert(MapEntities.mapDtoToIdea(dto));
	}
	
	public void agregarOperacion(IdeaDTO dto , OperacionIdea operacion){
		Idea idea = MapEntities.mapDtoToIdea(dto);
		idea.getOperaciones().add(operacion);
		if(operacion.getTipo().equals(TipoOperacionEnum.COMENTARIO)){
			idea.setComentarios(idea.getComentarios()+1);
		}else
			idea.setLights(idea.getLights()+1);
		
		ideaRep.save(idea);
	}
}
