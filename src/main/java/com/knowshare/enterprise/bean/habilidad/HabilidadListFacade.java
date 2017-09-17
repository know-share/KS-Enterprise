package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.bson.types.ObjectId;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * Se encarga de operaciones de consulta, es decir,
 * no hacen modificaciones en la base de datos, sobre
 * la entidad {@link Habilidad}
 * @author Miguel Montañez
 *
 */
public interface HabilidadListFacade {
	
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
}
