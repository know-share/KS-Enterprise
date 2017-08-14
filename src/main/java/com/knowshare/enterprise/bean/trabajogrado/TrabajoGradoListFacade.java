/**
 * 
 */
package com.knowshare.enterprise.bean.trabajogrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Pablo Gaitan
 *
 */
public interface TrabajoGradoListFacade {
	
	List<TrabajoGrado> findAll();

}
