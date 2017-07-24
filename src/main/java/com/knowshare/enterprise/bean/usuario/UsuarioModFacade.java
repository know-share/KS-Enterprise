/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad Usuario.
 * @author miguel
 *
 */
public interface UsuarioModFacade {
	
	boolean crearUsuario(UsuarioDTO usuario);
	
	boolean seguir(String usernameSol, String usernameObj);
	
	boolean dejarSeguir(String usernameSol,String usernameObj);
	
	boolean solicitudAmistad(String usernameSol,String usernameObj);

	boolean accionSolicitud(String username, String usernameObj, String action);
	
	boolean agregarTGDirigido(TrabajoGrado tg, String username);
	
	boolean agregarFormacionAcademica(FormacionAcademica formacion, String username);
	
	boolean eliminarAmigo(String username, String usernameEliminar);
	
	boolean actualizarInfoAcademica(UsuarioDTO usuario);
	
	boolean actualizarHabilidadCualidad(UsuarioDTO usuario);
	
	boolean actualizarBasis(UsuarioDTO usuario);
}
