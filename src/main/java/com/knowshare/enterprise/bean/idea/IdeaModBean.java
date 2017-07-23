/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
	
	@Autowired
	private IdeaListFacade ideaList;
	
	public IdeaDTO crearIdea(IdeaDTO dto){
		try {
			Idea creada = MapEntities.mapDtoToIdea(dto,usuRep.findByUsernameIgnoreCase(dto.getUsuario()));
			ideaRep.insert(creada);
			return MapEntities.mapIdeaToDTO(creada);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public IdeaDTO agregarOperacion(IdeaDTO dto , OperacionIdea operacion){
		Idea idea;
		OperacionIdea op;
		idea = ideaRep.findOne(dto.getId());
		List<OperacionIdea> operaciones;
		if(operacion.getTipo().equals(TipoOperacionEnum.COMENTARIO)){
			idea.setComentarios(idea.getComentarios()+1);
			idea.getOperaciones().add(operacion);
		}else{
			op = ideaList.isLight(idea, operacion.getUsername());
			operaciones = idea.getOperaciones();
			if(op != null){
				idea.setLights(idea.getLights()-1);
				operaciones.remove(op);
			}else{
				idea.setLights(idea.getLights()+1);
				operaciones.add(operacion);
			}
			idea.setOperaciones(operaciones);
		}
		return MapEntities.mapIdeaToDTO(ideaRep.save(idea));
	}
	
}
