/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioBean implements UsuarioFacade {
	
	@Autowired
	private UsuarioListFacade usuarioListBean;
	
	@Autowired
	private UsuarioModFacade usuarioModBean;

	@Override
	public boolean isUsernameTaken(String username) {
		return usuarioListBean.isUsernameTaken(username);
	}

	@Override
	public boolean crearUsuario(UsuarioDTO usuario){
		return usuarioModBean.crearUsuario(usuario);
	}

	@Override
	public boolean login(String username, String password) {
		return usuarioListBean.login(username, password);
	}
	
	public boolean seguir(String usernameSol,String usernameObj){
		return usuarioModBean.seguir(usernameSol, usernameObj);
	}

	@Override
	public UsuarioDTO getUsuario(String username) {
		return usuarioListBean.getUsuario(username);
	}
	
	public boolean solicitudAmistad(String usernameSol,String usernameObj){
		return usuarioModBean.solicitudAmistad(usernameSol, usernameObj);
	}

	@Override
	public boolean accionSolicitud(String username, String usernameObj, String action){
		return this.usuarioModBean.accionSolicitud(username, usernameObj, action);
	}

	@Override
	public boolean dejarSeguir(String usernameSol, String usernameObj) {
		return this.usuarioModBean.dejarSeguir(usernameSol, usernameObj);
	}

	@Override
	public boolean agregarTGDirigido(TrabajoGrado tg, String username) {
		return this.usuarioModBean.agregarTGDirigido(tg, username);
	}

	@Override
	public boolean agregarFormacionAcademica(FormacionAcademica formacion, String username) {
		return this.usuarioModBean.agregarFormacionAcademica(formacion, username);
	}

	@Override
	public boolean eliminarAmigo(String username, String usernameEliminar) {
		return this.usuarioModBean.eliminarAmigo(username, usernameEliminar);
	}

	@Override
	public List<UsuarioDTO> getAllEstudiantesExceptOne(String username) {
		return this.usuarioListBean.getAllEstudiantesExceptOne(username);
	}
	
}
