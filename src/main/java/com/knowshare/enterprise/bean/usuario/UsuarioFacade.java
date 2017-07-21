/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoUsuariosEnum;

/**
 * @author miguel
 *
 */
public interface UsuarioFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean crearUsuario(UsuarioDTO usuario);
	
	boolean seguir(String usernameSol,String usernameObj);
	
	boolean dejarSeguir(String usernameSol,String usernameObj);
	
	Usuario login(String username,String password);
	
	boolean solicitudAmistad(String usernameSol,String usernameObj);
	
	UsuarioDTO getUsuario(String username);
	
	boolean accionSolicitud(String username, String usernameObj, String action);
	
	boolean agregarTGDirigido(TrabajoGrado tg, String username);
	
	boolean agregarFormacionAcademica(FormacionAcademica formacion, String username);
	
	boolean eliminarAmigo(String username, String usernameEliminar);
	
	List<UsuarioDTO> getMyNoConnections(String username,TipoUsuariosEnum tipo);
	
	boolean actualizarInfoAcademica(UsuarioDTO usuario);
}
