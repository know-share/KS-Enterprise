/**
 * 
 */
package com.knowshare.enterprise.bean.trabajogrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Miguel Montañez
 *
 */
public interface TrabajoGradoFacade {
	
	List<TrabajoGrado> findAll();

}
