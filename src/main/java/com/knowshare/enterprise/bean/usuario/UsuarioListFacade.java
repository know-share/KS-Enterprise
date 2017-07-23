/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoUsuariosEnum;

/**
 * @author miguel
 *
 */
public interface UsuarioListFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean isCorreoTaken(String correo);
	
	Usuario login(String username,String password);
	
	boolean esSeguidor(Usuario uSol,Usuario uObj);
	
	boolean estaSolicitud(Usuario uSol,Usuario uObj);
	
	UsuarioDTO getUsuario(String username);
	
	List<UsuarioDTO> getMyNoConnections(String username,TipoUsuariosEnum tipo);
}
