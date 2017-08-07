/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;

/**
 * @author miguel
 *
 */
@Component
public class CarreraBean implements CarreraFacade{
	
	@Autowired
	private CarreraListFacade carreraListBean;
	
	@Autowired
	private CarreraModFacade carreraModBean;

	@Override
	public List<CarreraDTO> getAllCarreras() {
		return carreraListBean.getAllCarreras();
	}

	@Override
	public EnfasisAreaConocimientoDTO getEnfasisAreaConocimiento(String carrera) {
		return carreraListBean.getEnfasisAreaConocimiento(carrera);
	}

	@Override
	public boolean update(CarreraDTO carrera) {
		return carreraModBean.update(carrera);
	}

}
