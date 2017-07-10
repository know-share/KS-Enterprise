/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
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
	 
	@Autowired
	private UsuarioRepository usuRep;
	
	public IdeaDTO crearIdea(IdeaDTO dto){
		try {
			Idea creada = MapEntities.mapDtoToIdea(dto,usuRep.findByUsernameIgnoreCase(dto.getUsuario()));
			ideaRep.insert(creada);
			return MapEntities.mapIdeaToDTO(creada);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public Idea agregarOperacion(IdeaDTO dto , OperacionIdea operacion){
		Idea idea;
		try {
			idea = MapEntities.mapDtoToIdea(dto,usuRep.findByUsernameIgnoreCase(dto.getUsuario()));
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
