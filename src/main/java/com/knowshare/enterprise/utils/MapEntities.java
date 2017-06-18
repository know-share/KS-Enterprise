/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;

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
	
	public static IdeaDTO mapIdeaToDTO (Idea idea){
		IdeaDTO dto = new IdeaDTO();
		dto.setId(idea.getId());
		dto.setAlcance(idea.getAlcance());
		dto.setComentarios(idea.getComentarios());
		dto.setContenido(idea.getContenido());
		dto.setEstado(idea.getEstado());
		dto.setLights(idea.getLights());
		dto.setProblematica(idea.getProblematica());
		dto.setTags(idea.getTags());
		dto.setTipo(idea.getTipo());
		return dto;
	}
	
	public static Idea mapDtoToIdea(IdeaDTO dto){
		Idea idea = new Idea();
		idea.setId(dto.getId());
		idea.setAlcance(dto.getAlcance());
		idea.setContenido(dto.getContenido());
		idea.setEstado(dto.getEstado());
		idea.setIdeasProyecto(dto.getIdeasProyecto());
		idea.setLugarEscritura(dto.getLugarEscritura());
		idea.setNumeroEstudiantes(dto.getNumeroEstudiantes());
		idea.setProblematica(dto.getProblematica());
		idea.setTags(dto.getTags());
		idea.setTipo(dto.getTipo());
		idea.setUsuario(dto.getUsuario());
		return idea;
	}

}
