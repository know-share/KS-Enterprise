/**
 * 
 */
package com.knowshare.enterprise.bean.trabajoGrado;

import java.util.List;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Pablo Gaitan
 *
 */
public interface TrabajoGradoListFacade {
	
	List<TrabajoGrado> findAll();

}
