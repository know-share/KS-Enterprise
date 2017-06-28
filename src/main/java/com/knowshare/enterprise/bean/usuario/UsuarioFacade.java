/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import com.knowshare.dto.perfilusuario.UsuarioDTO;

/**
 * @author miguel
 *
 */
public interface UsuarioFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean crearUsuario(UsuarioDTO usuario);
	
	boolean seguir(String usernameSol,String usernameObj);
	
	boolean login(String username,String password);
	
	boolean solicitudAmistad(String usernameSol,String usernameObj);
	
	UsuarioDTO getUsuario(String username);
	
}
