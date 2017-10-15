/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Pablo Gaitan
 *
 */
public interface IdeaFacade {
	
	/**
	 * Dada la idea que llega, crea la idea en la 
	 * base de datos
	 * @param dto a crear
	 * @return {@link IdeaDTO} con información nueva
	 * del servidor
	 */
	IdeaDTO crearIdea(IdeaDTO dto);
	
	/**
	 * Se encarga de agregar un comentario o un light
	 * @param dto
	 * @param operacion, si fue un light o un comentario
	 * @return {@link IdeaDTO}
	 */
	IdeaDTO agregarOperacion(IdeaDTO dto, OperacionIdea operacion);
	
	/**
	 * Ideas creadas del usuario especificado. Este tipo de consuta
	 * está paginado por si el número crece de manera rápida, no saturar
	 * el envío de mensaje.
	 * @param username usuario que visita el perfil
	 * @param usernameProfile usuario al cual se está visitando
	 * @param page número de página a generar
	 * @param timestamp fecha del momento en que se está visitando
	 * el perfil
	 * @return Lista de {@link IdeaDTO ideas} paginadas
	 */
	Page<IdeaDTO> findByUsuario(String username,String usernameProfile,Integer page, long timestamp);
	
	/**
	 * Busca una idea por su id y con el usuario revisa 
	 * que operaciones ha hecho dicho usuario sobre la idea.
	 * @param id
	 * @param username
	 * @return {@link IdeaDTO idea} encontrada
	 */
	IdeaDTO findById(String id,String username);
	
	/**
	 * Comparte una idea que llega como parámetro. Realiza la
	 * copia básica de la idea.
	 * @param dto idea a compartir
	 * @param username
	 * @return la nueva {@link IdeaDTO idea} generada de compartir
	 */
	IdeaDTO compartir(IdeaDTO dto,String username);
	
	/**
	 * Busca solamente las ideas que puedens ser usadas para
	 * crear ideas de tipo proyecto.
	 * @param username
	 * @param page
	 * @param timestamp
	 * @return Lista de {@link IdeaDTO ideas} paginadas
	 */
	Page<IdeaDTO> findByUsuarioProyecto(String username,Integer page,long timestamp);
	
	/**
	 * Busca las operaciones hechas sobre una idea especificada
	 * @param id de la idea
	 * @param tipo {@link TipoOperacionEnum operación}
	 * @return Lista de {@link OperacionIdea operaciones}
	 */
	List<OperacionIdea> findOperaciones(String id,String tipo);
	
	/**
	 * Cambia el estado de una idea de NOTG a TG
	 * @param dto
	 * @return {@link IdeaDTO idea} con el nuevo estado
	 */
	IdeaDTO cambiarEstado(IdeaDTO dto);
}
