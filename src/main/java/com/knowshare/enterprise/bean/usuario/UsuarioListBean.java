/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.enterprise.utils.UtilsPassword;
import com.knowshare.entities.perfilusuario.InfoUsuario;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioListBean implements UsuarioListFacade{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean isUsernameTaken(String username) {
		Usuario usuario = usuarioRepository
				.findByUsernameIgnoreCase(username);
		if(usuario != null)
			return true;
		return false;
	}
	
	public Usuario login(String username,String password){
		Usuario usuario;
		try {
			usuario = usuarioRepository
					.findByPasswordAndUsernameIgnoreCase(
							UtilsPassword.hashPassword(username, password),
							username);
			return usuario;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public boolean esSeguidor(Usuario uSol,Usuario uObj){
		if(null != uSol && null != uObj){
			final InfoUsuario info = new InfoUsuario()
					.setNombre(uSol.getNombre()+" "+uSol.getApellido())
					.setUsername(uSol.getUsername());
			if(uObj.getSeguidores().contains(info)){
				return true;
			}
		}
		return false;
	}
	
	public boolean estaSolicitud(Usuario uSol,Usuario uObj){
		if(null != uSol && null != uObj){
			final InfoUsuario info = new InfoUsuario()
					.setNombre(uSol.getNombre()+" "+uSol.getApellido())
					.setUsername(uSol.getUsername());
			if(uObj.getSeguidores().contains(info) 
					|| uObj.getAmigos().contains(info)
					|| uObj.getSolicitudesAmistad().contains(uSol.getUsername())){
				return true;
			}
		}
		return false;
	}

	@Override
	public UsuarioDTO getUsuario(String username) {
		Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username);
		if(usuario == null)
			return null;
		return MapEntities.mapUsuarioToDTO(usuario);
	}

	/**
	 * El username se asume que llega igual como está en la base de datos, ya que
	 * este es sacado del token.
	 */
	@Override
	public List<UsuarioDTO> getMyNoConnections(String username) {
		final List<Usuario> usuarios = usuarioRepository.findMyNoConnections(username);
		final List<UsuarioDTO> usuarioRet = new ArrayList<>();
		for (Usuario usuario : usuarios)
			usuarioRet.add(MapEntities.mapUsuarioToDTO(usuario));
		return usuarioRet;
	}

	@Override
	public boolean isCorreoTaken(String correo) {
		return usuarioRepository.findByCorreoIgnoreCase(correo) != null;
	}

}
