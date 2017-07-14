/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import com.knowshare.dto.perfilusuario.UsuarioDTO;

/**
 * @author miguel
 *
 */
public interface UsuarioListFacade {
	
	boolean isUsernameTaken(String username);
	boolean login(String username,String password);
	boolean esSeguidor(String usernameSol,String usernameObj);
	boolean estaSolicitud(String usernameSol,String usernameObj);
	UsuarioDTO getUsuario(String username);
	List<UsuarioDTO> getAllEstudiantesExceptOne(String username);
}
