/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * {@link IdeaFacade}
 * @author Pablo Gaitan
 *
 */
@Component
public class IdeaBean implements IdeaFacade{
	
	@Autowired
	private IdeaModFacade ideaMod;
	
	@Autowired
	private IdeaListFacade ideaList;
	
	public IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion){
		return ideaMod.agregarOperacion(dto, operacion);
	}
	
	public IdeaDTO crearIdea(IdeaDTO dto){
		return ideaMod.crearIdea(dto);
	}
	
	public Page<IdeaDTO> findByUsuario(String username, String usernameProfile,
			Integer page,long timestamp){
		return ideaList.findByUsuario(username,usernameProfile,page,timestamp);
	}

	@Override
	public IdeaDTO findById(String id, String username) {
		return ideaList.findById(id, username);
	}
	
	public IdeaDTO compartir(IdeaDTO dto,String username){
		return ideaMod.compartir(dto, username);
	}

	@Override
	public Page<IdeaDTO> findByUsuarioProyecto(String username,Integer page,
			long timestamp) {
		return ideaList.findByUsuarioProyecto(username,page,timestamp);
	}

	@Override
	public List<OperacionIdea> findOperaciones(String id, String tipo) {
		return ideaList.findOperaciones(id, tipo);
	}

	@Override
	public IdeaDTO cambiarEstado(IdeaDTO dto) {
		return ideaMod.cambiarEstado(dto);
	}
}
