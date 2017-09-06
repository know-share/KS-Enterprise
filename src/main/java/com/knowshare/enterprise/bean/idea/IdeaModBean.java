/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.bean.usuario.UsuarioFacade;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * {@link IdeaModFacade}
 * @author Pablo Gaitán
 *
 */
@Component
public class IdeaModBean implements IdeaModFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	 
	@Autowired
	private UsuarioRepository usuRep;
	
	@Autowired
	private UsuarioFacade usuarioBean;
	
	@Autowired
	private IdeaListFacade ideaList;
	
	public IdeaDTO crearIdea(IdeaDTO dto){
		try {
			dto.setFechaCreacion(new Date());
			Idea creada = MapEntities.mapDtoToIdea(dto,usuRep.findByUsernameIgnoreCase(dto.getUsuario()));
			creada.setEstado("NOTG");
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
				this.usuarioBean.actualizarPreferenciaIdeas(idea.getTags(), operacion.getUsername());
			}
			idea.setOperaciones(operaciones);
		}
		return MapEntities.mapIdeaToDTO(ideaRep.save(idea));
	}
	
	public IdeaDTO compartir(IdeaDTO dto, String username){
		IdeaDTO compartida = new IdeaDTO();
		compartida.setAlcance(dto.getAlcance());
		compartida.setComentarios(Long.valueOf(0));
		compartida.setCompartida(true);
		compartida.setContenido(dto.getContenido());
		compartida.setEstado(dto.getEstado());
		compartida.setIsLight(false);
		compartida.setLights(Long.valueOf(0));
		compartida.setLugarEscritura(dto.getLugarEscritura());
		compartida.setNumeroEstudiantes(dto.getNumeroEstudiantes());
		compartida.setProblematica(dto.getProblematica());
		compartida.setTags(dto.getTags());
		compartida.setTipo(dto.getTipo());
		compartida.setFechaCreacion(new Date());
		compartida.setUsuario(username);
		if(!dto.isCompartida()){
			compartida.setUsuarioOriginal(dto.getUsuario());
		}else{
			compartida.setUsuarioOriginal(dto.getUsuarioOriginal());
		}
		Idea ret = new Idea();
		try {
			 ret = ideaRep.insert(MapEntities.mapDtoToIdea(compartida, usuRep.findByUsernameIgnoreCase(username)));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return MapEntities.mapIdeaToDTO(ret);
	}

	@Override
	public IdeaDTO cambiarEstado(IdeaDTO dto) {
		Idea idea = ideaRep.findOne(dto.getId());
		idea.setEstado(dto.getEstado());
		return MapEntities.mapIdeaToDTO(ideaRep.save(idea));
	}
}
