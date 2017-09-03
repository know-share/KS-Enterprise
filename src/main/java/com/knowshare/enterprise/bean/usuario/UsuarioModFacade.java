/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.enums.PreferenciaIdeaEnum;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad {@link Usuario}.
 * @author Miguel Montañez
 *
 */
public interface UsuarioModFacade {
	
	/**
	 * Registra un usuario en la aplicación
	 * @param usuario a registrar
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean crearUsuario(UsuarioDTO usuario);
	
	/**
	 * Ejecuta la acción de seguir a un usuario
	 * @param usernameSol Usuario que ejecuta la acción
	 * @param usernameObj Usuario a seguir
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean seguir(String usernameSol, String usernameObj);
	
	/**
	 * Ejecuta la acción de dejar de seguir a un usuario
	 * @param usernameSol Usuario que ejecuta la acción
	 * @param usernameObj Usuario a dejar de seguir
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean dejarSeguir(String usernameSol,String usernameObj);
	
	/**
	 * Ejecuta la acción de enviar solicitud de amistad a un usuario
	 * @param usernameSol Usuario que ejecuta la acción
	 * @param usernameObj Usuario al que se le envía la petición
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean solicitudAmistad(String usernameSol,String usernameObj);

	/**
	 * Acepta o rechaza una solicitud de amistad
	 * @param username Usuario que ejecuta la acción
	 * @param usernameObj Usuario al cual se le aceptará o
	 * rechazará la solicitud
	 * @param action ACCEPT or REJECT
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean accionSolicitud(String username, String usernameObj, String action);
	
	/**
	 * Agrega un {@link TrabajoGrado trabajo de grado}  
	 * @param tg a agregar
	 * @param username Usuario que ejecuta la acción
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean agregarTGDirigido(TrabajoGrado tg, String username);
	
	/**
	 * Agrega una {@link FormacionAcademica formación académica}
	 * @param formacion a agregar
	 * @param username Usuario que ejecuta la acción
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean agregarFormacionAcademica(FormacionAcademica formacion, String username);
	
	/**
	 * Acción de eliminar un amigo
	 * @param username Usuario que ejecuta la acción
	 * @param usernameEliminar Usuario que será eliminado
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean eliminarAmigo(String username, String usernameEliminar);
	
	/**
	 * Actualiza la información académica de un usuario
	 * especificado
	 * @param usuario Usuario con los nuevos cambios
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean actualizarInfoAcademica(UsuarioDTO usuario);
	
	/**
	 * Actualiza las {@link Habilidad habilidades} o {@link Cualidad cualidades}
	 * de un usuario
	 * @param usuario Usuario con los nuevos cambios
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean actualizarHabilidadCualidad(UsuarioDTO usuario);
	
	/**
	 * Actualiza la información básica o de contacto de 
	 * un usuario
	 * @param usuario Usuario con los nuevos cambios
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean actualizarBasis(UsuarioDTO usuario);
	
	/**
	 * Sube y asocia una imagen al servidor
	 * @param username Dueño de la imagen
	 * @param file 
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean uploadImage(String username, MultipartFile file);
	
	/**
	 * Actualiza la preferencia de idea de un usuario
	 * @param username del usuario actual
	 * @param preferencia 
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean updatePreferenciaIdea(String username, PreferenciaIdeaEnum preferencia);
	
	/**
	 * Actualiza las insignias que están no vistas a vistas
	 * @param username del usuario actual
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean updateInsignias(String username);
	
	/**
	 * Promueve un Estudiante a Egresado. Operación que solo puede
	 * ser ejecutada por un Profesor
	 * @param username del usuario actual
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean promoteEstudiante(String username);
	
	/**
	 * Actualiza los gustos de un usuarios
	 * @param gustos Nuevos a ser actualizados
	 * @param username Usuario actual
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean actualizarGustos(List<Gusto> gustos, String username);
}
