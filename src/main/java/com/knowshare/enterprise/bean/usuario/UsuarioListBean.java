/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.enterprise.utils.UtilsPassword;
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
	
	public boolean login(String username,String password){
		Usuario usuario;
		try {
			usuario = usuarioRepository
					.findByPasswordAndUsernameIgnoreCase(
							UtilsPassword.hashPassword(username, password),
							username);
			if(usuario != null)
				return true;
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		
		return false;
	}
	
	public boolean esSeguidor(String usernameSol,String usernameObj){
		Usuario uSol = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		Usuario uObj = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(uObj.getSeguidores().contains(uSol.getUsername())){
			return true;
		}
		return false;
	}
	
	public boolean estaSolicitud(String usernameSol,String usernameObj){
		Usuario uSol = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		Usuario uObj = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(uObj.getSeguidores().contains(uSol.getUsername()) || uObj.getAmigos().contains(uSol.getUsername())){
			return true;
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

}
