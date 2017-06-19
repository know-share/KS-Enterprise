/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * @author HP
 *
 */
@Component
public class IdeaModBean implements IdeaModFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	public void crearIdea(IdeaDTO dto) throws Exception{
		ideaRep.insert(MapEntities.mapDtoToIdea(dto));
	}
	
	public void agregarOperacion(IdeaDTO dto , OperacionIdea operacion) throws Exception{
		Idea idea = MapEntities.mapDtoToIdea(dto);
		idea.getOperaciones().add(operacion);
		if(operacion.getTipo().equals(TipoOperacionEnum.COMENTARIO)){
			idea.setComentarios(idea.getComentarios()+1);
		}else
			idea.setLights(idea.getLights()+1);
		
		ideaRep.save(idea);
	}
}
