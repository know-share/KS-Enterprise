/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.OperacionIdea;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad {@link Idea}.
 * @author Pablo Gaitán
 *
 */
public interface IdeaModFacade {
	
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
	 * Comparte una idea que llega como parámetro. Realiza la
	 * copia básica de la idea.
	 * @param dto idea a compartir
	 * @param username
	 * @return la nueva {@link IdeaDTO idea} generada de compartir
	 */
	IdeaDTO compartir(IdeaDTO dto,String username);
	
	/**
	 * Cambia el estado de una idea de NOTG a TG
	 * @param dto
	 * @return {@link IdeaDTO idea} con el nuevo estado
	 */
	IdeaDTO cambiarEstado(IdeaDTO dto);
}
