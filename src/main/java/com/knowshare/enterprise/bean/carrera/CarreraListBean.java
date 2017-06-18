/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.enterprise.repository.carrera.CarreraRepository;
import com.knowshare.enterprise.utils.MapEntities;

/**
 * @author miguel
 *
 */
@Component
public class CarreraListBean implements CarreraListFacade{
	
	@Autowired
	private CarreraRepository carreraRepository;

	@Override
	public List<CarreraDTO> getAllCarreras() {
		return MapEntities.mapCarreraToDTO(carreraRepository.findAll());
	}

}
