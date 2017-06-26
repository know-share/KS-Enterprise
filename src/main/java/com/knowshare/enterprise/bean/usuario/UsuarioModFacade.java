/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import com.knowshare.dto.perfilusuario.UsuarioDTO;

/**
 * @author miguel
 *
 */
public interface UsuarioModFacade {
	
	boolean crearUsuario(UsuarioDTO usuario);
	
	boolean seguir(String usernameSol, String usernameObj);

}
