/**
 * 
 */
package com.knowshare.enterprise.bean.trabajoGrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Miguel Montañez
 *
 */
public interface TrabajoGradoFacade {
	
	List<TrabajoGrado> findAll();

}
