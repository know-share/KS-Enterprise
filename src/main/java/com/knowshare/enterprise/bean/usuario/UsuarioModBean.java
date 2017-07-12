/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.academia.TrabajoGrado;
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
			objetivo.getSeguidores().add(solicitante.getUsername());
			if(usuarioRepository.save(objetivo)!=null){
				return true;
			}
		}
		return false;
	}
	
	public boolean dejarSeguir(String usernameSol,String usernameObj){
		Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(usuarioListBean.esSeguidor(usernameSol, usernameObj)){
			objetivo.getSeguidores().remove(solicitante.getUsername());
			if(usuarioRepository.save(objetivo)!=null){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean solicitudAmistad(String usernameSol,String usernameObj){
		Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(!usuarioListBean.estaSolicitud(usernameSol, usernameObj)){
			objetivo.getSolicitudesAmistad().add(solicitante.getUsername());
			if(usuarioRepository.save(objetivo)!=null){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean accionSolicitud(String username, String usernameObj, String action) {
		final Usuario actual = usuarioRepository.findByUsernameIgnoreCase(username);
		final Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		
		actual.getSolicitudesAmistad().remove(usernameObj);
		
		if(action.equalsIgnoreCase("accept")){
			actual.getAmigos().add(objetivo.getUsername());
			objetivo.getAmigos().add(actual.getUsername());
			if(usuarioRepository.save(actual) != null && null != usuarioRepository.save(objetivo))
				return true;
			return false;
		}
		if(usuarioRepository.save(actual) != null)
			return true;
		return false;
	}
	
	public boolean agregarTGDirigido(TrabajoGrado tg, String username){
		final Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username);
		if(null == usuario.getTrabajosGradoDirigidos()){
			final List<TrabajoGrado> tgs = new ArrayList<>();
			tgs.add(tg);
			usuario.setTrabajosGradoDirigidos(tgs);
		}else
			usuario.getTrabajosGradoDirigidos().add(tg);
		
		return (null != usuarioRepository.save(usuario));
	}
}
