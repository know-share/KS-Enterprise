/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.knowshare.dto.perfilusuario.ImagenDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface UsuarioFacade {
	
	boolean isUsernameTaken(String username);
	
	boolean isCorreoTaken(String correo);
	
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
	
	List<UsuarioDTO> getMyNoConnections(String username);
	
	boolean actualizarInfoAcademica(UsuarioDTO usuario);
	
	boolean actualizarHabilidadCualidad(UsuarioDTO usuario);
	
	boolean actualizarBasis(UsuarioDTO usuario);
	
	List<UsuarioDTO> buscarPorNombre(UsuarioDTO usuarioActual,String param);
	
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorHabilidad(String param);
	
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorAreaConocimiento(String param);
	
	boolean uploadImage(String username, MultipartFile file);
	
	ImagenDTO getImage(String username);
	
	boolean updatePreferenciaIdea(String username, PreferenciaIdeaEnum preferencia);
	
	boolean updateInsignias(String username);
}
