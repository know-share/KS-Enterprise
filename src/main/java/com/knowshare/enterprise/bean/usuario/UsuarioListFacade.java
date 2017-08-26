/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;
import java.util.Map;

import com.knowshare.dto.perfilusuario.ImagenDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Usuario}
 * @author Miguel Montañez
 *
 */
public interface UsuarioListFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean isCorreoTaken(String correo);
	
	Usuario login(String username,String password);
	
	boolean esSeguidor(Usuario uSol,Usuario uObj);
	
	boolean estaSolicitud(Usuario uSol,Usuario uObj);
	
	UsuarioDTO getUsuario(String username);
	
	List<UsuarioDTO> getMyNoConnections(String username);
	
	List<UsuarioDTO> buscarPorNombre(UsuarioDTO usuarioActual,String param);
	
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorHabilidad(String param);
	
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorAreaConocimiento(String param);
	
	ImagenDTO getImage(String username);
}
