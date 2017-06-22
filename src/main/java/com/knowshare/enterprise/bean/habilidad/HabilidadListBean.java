package com.knowshare.enterprise.bean.habilidad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.enterprise.repository.perfilusuario.HabilidadRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * 
 * @author miguel
 *
 */
@Component
public class HabilidadListBean implements HabilidadListFacade{
	
	@Autowired
	private HabilidadRepository habilidadRepository;
	
	public Habilidad findOne(String nombre){
		return habilidadRepository.findByNombre(nombre);
	}

	@Override
	public List<HabilidadDTO> getHabilidades(String carrera) {
		final List<Habilidad> habilidades = habilidadRepository
				.getHabilidades(carrera);

		final List<HabilidadDTO> habilidadesDto = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			habilidadesDto.add(MapEntities.mapHabilidadToDTO(habilidad));
		}
		return habilidadesDto;
	}
	
	public Page<Habilidad> getAll(){
		return habilidadRepository.findAll(new PageRequest(1, 10));
	}

	@Override
	public List<HabilidadDTO> getHabilidadesProfesionales(String carrera) {
		final List<Habilidad> habilidades = habilidadRepository
				.getHabilidadesProfesionales(carrera);

		final List<HabilidadDTO> habilidadesDto = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			habilidadesDto.add(MapEntities.mapHabilidadToDTO(habilidad));
		}
		return habilidadesDto;
	}
}
