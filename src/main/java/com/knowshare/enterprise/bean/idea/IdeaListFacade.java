package com.knowshare.enterprise.bean.idea;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Idea}
 * @author Pablo Gaitán
 *
 */
public interface IdeaListFacade {
	
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
	Page<IdeaDTO> findByUsuario(String username, String usernameProfile,Integer page,long timestamp);
	
	/**
	 * Dada la idea mira si ya tiene un light del
	 * usuario ingresado por parámetro.
	 * @param idea
	 * @param username
	 * @return {@link OperacionIdea} si existe el light de 
	 * lo contrario retorna null.
	 */
	OperacionIdea isLight(Idea idea, String username);
	
	/**
	 * Busca una idea por su id y con el usuario revisa 
	 * que operaciones ha hecho dicho usuario sobre la idea.
	 * @param id
	 * @param username
	 * @return {@link IdeaDTO idea} encontrada
	 */
	IdeaDTO findById(String id, String username);
	
	/**
	 * Busca solamente las ideas que puedens ser usadas para
	 * crear ideas de tipo proyecto.
	 * @param username
	 * @param page
	 * @param timestamp
	 * @return Lista de {@link IdeaDTO ideas} paginadas
	 */
	Page<IdeaDTO> findByUsuarioProyecto(String username,Integer page, long timestamp);
	
	/**
	 * Busca las operaciones hechas sobre una idea especificada
	 * @param id de la idea
	 * @param tipo {@link TipoOperacionEnum operación}
	 * @return Lista de {@link OperacionIdea operaciones}
	 */
	List<OperacionIdea> findOperaciones(String id,String tipo);
}
