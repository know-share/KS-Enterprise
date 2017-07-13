/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author miguel
 *
 */
public interface UsuarioFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean crearUsuario(UsuarioDTO usuario);
	
	boolean seguir(String usernameSol,String usernameObj);
	
	boolean dejarSeguir(String usernameSol,String usernameObj);
	
	boolean login(String username,String password);
	
	boolean solicitudAmistad(String usernameSol,String usernameObj);
	
	UsuarioDTO getUsuario(String username);
	
	boolean accionSolicitud(String username, String usernameObj, String action);
	
	boolean agregarTGDirigido(TrabajoGrado tg, String username);
	
	boolean agregarFormacionAcademica(FormacionAcademica formacion, String username);
	
	boolean eliminarAmigo(String username, String usernameEliminar);
}
