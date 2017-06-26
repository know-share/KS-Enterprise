/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

/**
 * @author miguel
 *
 */
public interface UsuarioListFacade {
	
	boolean isUsernameTaken(String username);
	boolean login(String username,String password);
	public boolean esSeguidor(String usernameSol,String usernameObj);
}
