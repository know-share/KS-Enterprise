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
 * {@link CarreraBean}
 * @author Miguel Montañez
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
	
	@Override 
	public boolean delete(String carrera) {
		return carreraModBean.delete(carrera);
	}

	@Override
	public boolean create (CarreraDTO carrera) {
		return carreraModBean.create(carrera);
	}

	@Override
	public boolean updateEnfasis(CarreraDTO carrera) {
		return carreraModBean.updateEnfasis(carrera);
	}
}
