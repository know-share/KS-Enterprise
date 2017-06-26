/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioModBean implements UsuarioModFacade {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioListFacade usuarioListBean;

	@Override
	public boolean crearUsuario(UsuarioDTO usuario) {
		logger.debug("::::: Start method crearUsuario(UsuarioDTO) in UsuarioModBean :::::");
		Usuario nuevoUsuario;
		try {
			nuevoUsuario = MapEntities.mapDtoToUsuario(usuario);
			usuarioRepository.insert(nuevoUsuario);
			if (nuevoUsuario.getId() != null)
				return true;
		} catch (NoSuchAlgorithmException e) {
			logger.debug("::::: Error with algorithm hash :::::");
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean seguir(String usernameSol, String usernameObj){
		Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(!usuarioListBean.esSeguidor(usernameSol, usernameObj)){
			objetivo.getSeguidores().getAmigos().add(solicitante);
			objetivo.getSeguidores().setCantidad(objetivo.getSeguidores().getCantidad()+1);
			if(usuarioRepository.save(objetivo)!=null){
				return true;
			}
		}
		return false;
	}
}
