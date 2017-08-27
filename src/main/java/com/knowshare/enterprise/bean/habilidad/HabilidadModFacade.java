package com.knowshare.enterprise.bean.habilidad;

import org.bson.types.ObjectId;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * Se encarga de operaciones que hagan modificaciones sobre
 * la entidad {@link Habilidad}.
 * @author Miguel Montañez
 *
 */
public interface HabilidadModFacade {
	
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
