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
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface UsuarioFacade {
	
	/**
	 * Revisa si el username dado ya está registrado
	 * en la aplicación
	 * @param username
	 * @return verdadero si ya está siendo usado, si no
	 * falso.
	 */
	boolean isUsernameTaken(String username);
	
	/**
	 * Revisa si el correo dado ya está registrado
	 * en la aplicación
	 * @param correo
	 * @return verdadero si ya está siendo usado, si no
	 * falso.
	 */
	boolean isCorreoTaken(String correo);
	
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
	boolean seguir(String usernameSol,String usernameObj);
	
	/**
	 * Ejecuta la acción de dejar de seguir a un usuario
	 * @param usernameSol Usuario que ejecuta la acción
	 * @param usernameObj Usuario a dejar de seguir
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean dejarSeguir(String usernameSol,String usernameObj);
	
	/**
	 * Permite revisar si las credenciales ingresadas en el
	 * login son correctas para darle acceso a la aplicación
	 * @param username 
	 * @param password
	 * @return {@link Usuario} si las credenciales son correctas,
	 * de lo contrario null.
	 */
	Usuario login(String username,String password);
	
	/**
	 * Ejecuta la acción de enviar solicitud de amistad a un usuario
	 * @param usernameSol Usuario que ejecuta la acción
	 * @param usernameObj Usuario al que se le envía la petición
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean solicitudAmistad(String usernameSol,String usernameObj);
	
	/**
	 * Dado el username se busca un usuario
	 * @param username a buscar
	 * @return {@link UsuarioDTO} si existe, de lo contrario
	 * retorna null
	 */
	UsuarioDTO getUsuario(String username);
	
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
	 * Obtiene la lista de usuarios que no están dentro de la
	 * red del usuario especificado
	 * @param username Usuario actual
	 * @return Lista de {@link UsuarioDTO usuarios}
	 */
	List<UsuarioDTO> getMyNoConnections(String username);
	
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
	 * Hace la búsqueda de usuarios por nombre
	 * @param usuarioActual para no traer la búsqueda de dicho usuario
	 * @param param nombre de usuarios a buscar
	 * @return Lista de {@link UsuarioDTO usuarios}
	 */
	List<UsuarioDTO> buscarPorNombre(UsuarioDTO usuarioActual,String param);
	
	/**
	 * Hace la búsqueda de usuarios por habilidad
	 * @param param nombre de la habilidad a buscar
	 * @return Lista con atributos de un usuario mapeados
	 */
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorHabilidad(String param);
	
	/**
	 * Hace la búsqueda de usuarios por area de conocimiento
	 * @param param nombre del area de conocimiento a buscar
	 * @return Lista con atributos de un usuario mapeados
	 */
	@SuppressWarnings("rawtypes")
	List<Map> buscarPorAreaConocimiento(String param);
	
	/**
	 * Sube y asocia una imagen al servidor
	 * @param username Dueño de la imagen
	 * @param file 
	 * @return verdadero si pudo ejecutar la acción de forma
	 * correcta, de lo contrario, falso
	 */
	boolean uploadImage(String username, MultipartFile file);
	
	/**
	 * Busca la imagen asociada a un usuario.
	 * @param username del usuario del cual se busca la imagen
	 * @return {@link ImagenDTO imagen del usuario especificado}
	 */
	ImagenDTO getImage(String username);
	
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
	
	/**
	 * Actualiza las preferencias de idea después de haber dado
	 * light sobre una idea.
	 * @param tags a agregar
	 * @param username
	 */
	void actualizarPreferenciaIdeas(List<Tag> tags, String username);
}
