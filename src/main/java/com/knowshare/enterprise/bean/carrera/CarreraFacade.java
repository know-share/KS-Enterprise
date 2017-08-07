/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;

/**
 * @author miguel
 *
 */
public interface CarreraFacade {
	
	List<CarreraDTO> getAllCarreras();
	EnfasisAreaConocimientoDTO getEnfasisAreaConocimiento(String carrera);
	boolean update (CarreraDTO carrera);
}
