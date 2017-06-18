/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.util.ArrayList;
import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.entities.academia.Carrera;

/**
 * @author miguel
 *
 */
public class MapEntities {
	
	public static List<CarreraDTO> mapCarreraToDTO(List<Carrera> carreras){
		final List<CarreraDTO> dtos = new ArrayList<>();
		for (Carrera carrera : carreras) {
			CarreraDTO dto = new CarreraDTO()
					.setFacultad(carrera.getFacultad())
					.setNombre(carrera.getNombre())
					.setCarrerasAfines(carrerasAfinesNames(carrera.getCarrerasAfines()));
			dtos.add(dto);
		}
		return dtos;
	}
	
	private static List<String> carrerasAfinesNames(List<Carrera> carreras){
		final List<String> carrerasNames = new ArrayList<>();
		for (Carrera carrera : carreras) {
			carrerasNames.add(carrera.getNombre());
		}
		return carrerasNames;
	}

}
