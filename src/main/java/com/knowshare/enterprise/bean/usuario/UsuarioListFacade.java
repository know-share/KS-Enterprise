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
	 * Permite revisar si las credenciales ingresadas en el
	 * login son correctas para darle acceso a la aplicación
	 * @param username 
	 * @param password
	 * @return {@link Usuario} si las credenciales son correctas,
	 * de lo contrario null.
	 */
	Usuario login(String username,String password);
	
	/**
	 * Revisa si uSol ya es seguidor de uObj
	 * @param uSol Usuario actual
	 * @param uObj Usuario al cual se mira si seguidores
	 * @return verdadero si ya uSol sigue a uObj, de lo
	 * contrario falso.
	 */
	boolean esSeguidor(Usuario uSol,Usuario uObj);
	
	/**
	 * Revisa si uSol está en la lista de amigos, seguidores
	 * o solicitudes
	 * @param uSol Usuario actual
	 * @param uObj Usuario al cual se compara
	 * @return verdadero si ya está en alguna de las listas,
	 * de lo contrario falso.
	 */
	boolean estaSolicitud(Usuario uSol,Usuario uObj);
	
	/**
	 * Dado el username se busca un usuario
	 * @param username a buscar
	 * @return {@link UsuarioDTO} si existe, de lo contrario
	 * retorna null
	 */
	UsuarioDTO getUsuario(String username);
	
	/**
	 * Obtiene la lista de usuarios que no están dentro de la
	 * red del usuario especificado
	 * @param username Usuario actual
	 * @return Lista de {@link UsuarioDTO usuarios}
	 */
	List<UsuarioDTO> getMyNoConnections(String username);
	
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
	 * Busca la imagen asociada a un usuario.
	 * @param username del usuario del cual se busca la imagen
	 * @return {@link ImagenDTO imagen del usuario especificado}
	 */
	ImagenDTO getImage(String username);
}
