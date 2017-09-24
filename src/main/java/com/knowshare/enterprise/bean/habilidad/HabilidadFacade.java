/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.bson.types.ObjectId;
import com.knowshare.dto.perfilusuario.HabilidadDTO;

/**
 * Reune los métodos de negocio de una entidad. Operaciones de
 * listado y de modificaciones.
 * @author Miguel Montañez
 *
 */
public interface HabilidadFacade {
	
	/**
	 * Obtiene todas las habilidades TipoHabilidadEnum.PERSONALES 
	 * y algunas habilidades TipoHabilidadEnum.PROFESIONALES de la 
	 * carrera especificada
	 * @param carrera 
	 * @return Lista de {@link HabilidadDTO habilidades}
	 */
	List<HabilidadDTO> getHabilidades(String carrera);
	
	/**
	 * Obtiene solamente las habilidades TipoHabilidadEnum.PROFESIONALES
	 * según la carrera especificada.
	 * @param carrera
	 * @return Lista de {@link HabilidadDTO habilidades}
	 */
	List<HabilidadDTO> getHabilidadesProfesionales(String carrera);
	
	/**
	 * Busca habilidades según el nombre especificado
	 * @param nombre
	 * @return Lista de los ids de las habilidades
	 */
	List<ObjectId> buscarPorNombre(String nombre);
	
	/**
	 * Obtiene todas las habilidades tanto TipoHabilidadEnum.PROFESIONALES
	 * y TipoHabilidadEnum.PERSONALES del sistema
	 * @return Lista de {@link HabilidadDTO habilidades}
	 */
	List<HabilidadDTO> getAll();
	
	/**
	 * Actualiza una habilidad
	 * @param habilidad a actualizar
	 * @return verdadero si pudo ejecutar la acción, de lo
	 * contrario falso.
	 */
	boolean update (HabilidadDTO habilidad);
	
	/**
	 * Elimina una habilidad con el id dado
	 * @param id de la habilidad
	 * @return verdadero si pudo ejecutar la acción, de lo
	 * contrario falso.
	 */
	boolean delete(ObjectId id);
	
	/**
	 * Crea la habilidad que llega como parámetro
	 * @param habilidad a crear
	 * @return verdadero si pudo ejecutar la acción, de lo
	 * contrario falso.
	 */
	boolean create (HabilidadDTO habilidad);
}
