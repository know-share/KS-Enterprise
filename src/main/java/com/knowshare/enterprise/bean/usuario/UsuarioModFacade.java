/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.enums.PreferenciaIdeaEnum;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad Usuario.
 * @author Miguel Montañez
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
	
	boolean uploadImage(String username, MultipartFile file);
	
	boolean updatePreferenciaIdea(String username, PreferenciaIdeaEnum preferencia);
	
	boolean updateInsignias(String username);
	
	boolean promoteEstudiante(String username);
	
	boolean actualizarGustos(List<Gusto> gustos, String username);
}
