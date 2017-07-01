/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;
import com.knowshare.enterprise.repository.academia.CarreraRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.academia.Carrera;

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
		List<CarreraDTO> carreras = MapEntities.mapCarrerasToDTOs(carreraRepository.findAll());
		Collections.sort(carreras,new Comparator<CarreraDTO>() {

			@Override
			public int compare(CarreraDTO o1, CarreraDTO o2) {
				return o1.getNombre().compareToIgnoreCase(o2.getNombre());
			}
		});
		return carreras;
	}

	@Override
	public EnfasisAreaConocimientoDTO getEnfasisAreaConocimiento(String carrera) {
		Carrera c = carreraRepository.findByNombreIgnoreCase(carrera);
		if(c == null)
			return null;
		
		EnfasisAreaConocimientoDTO dto = new EnfasisAreaConocimientoDTO()
				.setEnfasis(c.getEnfasis());
		
		if(c.getAreaConocimiento() == null)
			dto.setAreaConocimiento(c.getEnfasis());
		else
			dto.setAreaConocimiento(c.getAreaConocimiento());
		return dto;
	}

}
