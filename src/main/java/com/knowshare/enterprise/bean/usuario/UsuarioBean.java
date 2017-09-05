/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.knowshare.dto.perfilusuario.ImagenDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;

/**
 * {@link UsuarioFacade}
 * @author Miguel Montañez
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
	public Usuario login(String username, String password) {
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
	public List<UsuarioDTO> getMyNoConnections(String username) {
		return this.usuarioListBean.getMyNoConnections(username);
	}

	@Override
	public boolean actualizarInfoAcademica(UsuarioDTO usuario) {
		return this.usuarioModBean.actualizarInfoAcademica(usuario);
	}

	@Override
	public boolean actualizarHabilidadCualidad(UsuarioDTO usuario) {
		return this.usuarioModBean.actualizarHabilidadCualidad(usuario);
	}

	@Override
	public boolean actualizarBasis(UsuarioDTO usuario) {
		return this.usuarioModBean.actualizarBasis(usuario);
	}

	@Override
	public boolean isCorreoTaken(String correo) {
		return this.usuarioListBean.isCorreoTaken(correo);
	}

	@Override
	public List<UsuarioDTO> buscarPorNombre(UsuarioDTO usuarioActual, String param) {
		return this.usuarioListBean.buscarPorNombre(usuarioActual,param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> buscarPorHabilidad(String param) {
		return this.usuarioListBean.buscarPorHabilidad(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> buscarPorAreaConocimiento(String param) {
		return this.usuarioListBean.buscarPorAreaConocimiento(param);
	}

	@Override
	public boolean uploadImage(String username, MultipartFile file) {
		return this.usuarioModBean.uploadImage(username, file);
	}

	@Override
	public ImagenDTO getImage(String username) {
		return this.usuarioListBean.getImage(username);
	}

	@Override
	public boolean updatePreferenciaIdea(String username, PreferenciaIdeaEnum preferencia) {
		return this.usuarioModBean.updatePreferenciaIdea(username, preferencia);
	}

	@Override
	public boolean updateInsignias(String username) {
		return this.usuarioModBean.updateInsignias(username);
	}

	@Override
	public boolean promoteEstudiante(String username) {
		return this.usuarioModBean.promoteEstudiante(username);
	}

	@Override
	public boolean actualizarGustos(List<Gusto> gustos, String username) {
		return this.usuarioModBean.actualizarGustos(gustos, username);
	}

	@Override
	public void actualizarPreferenciaIdeas(List<Tag> tags, String username) {
		this.usuarioModBean.actualizarPreferenciaIdeas(tags, username);
	}
}