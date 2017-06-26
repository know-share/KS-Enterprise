/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.app.PreferenciasUsuario;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.ludificacion.CualidadAval;
import com.knowshare.entities.ludificacion.HabilidadAval;
import com.knowshare.entities.perfilusuario.Amigos;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoHabilidadEnum;

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
	
	public static Carrera mapDtoToCarrera(CarreraDTO dto){
		return new Carrera().setNombre(dto.getNombre());
	}
	
	private static List<String> carrerasAfinesNames(List<Carrera> carreras){
		final List<String> carrerasNames = new ArrayList<>();
		for (Carrera carrera : carreras) {
			carrerasNames.add(carrera.getNombre());
		}
		return carrerasNames;
	}
	
	public static HabilidadDTO mapHabilidadToDTO(Habilidad habilidad){
		final HabilidadDTO dto = new HabilidadDTO()
				.setId(habilidad.getId())
				.setNombre(habilidad.getNombre())
				.setTipo(habilidad.getTipo());
		
		if(habilidad.getTipo().equals(TipoHabilidadEnum.PROFESIONALES))
			dto.setCarrera(habilidad.getCarrera().getNombre());
				
		return dto;
	}
	
	public static HabilidadAval mapDtoToHabilidadAval(HabilidadDTO habilidad){
		return new HabilidadAval()
				.setCantidad(0)
				.setHabilidad(new Habilidad().setId(habilidad.getId()));
	}
	
	public static CualidadAval mapCualidadToCualidadAval(Cualidad cualidad){
		return new CualidadAval()
				.setCantidad(0)
				.setCualidad(cualidad);
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
	
	public static Usuario mapDtoToUsuario(UsuarioDTO dto) throws NoSuchAlgorithmException{
		final List<HabilidadAval> habilidades = new ArrayList<>();
		for (HabilidadDTO habilidad : dto.getHabilidades()) {
			habilidades.add(mapDtoToHabilidadAval(habilidad));
		}
		final List<Carrera> carreras = new ArrayList<>();
		final String passwordHashed = UtilsPassword
				.hashPassword(dto.getUsername(), dto.getPassword());
		carreras.add(mapDtoToCarrera(dto.getCarrera()));
		final Usuario usuario = new Usuario()
				.setNombre(dto.getNombre())
				.setApellido(dto.getApellido())
				.setCorreo(dto.getEmail())
				.setUsername(dto.getUsername())
				.setPassword(passwordHashed)
				.setPersonalidad(dto.getPersonalidad())
				.setEnfasis(dto.getEnfasis())
				.setAreasConocimiento(dto.getAreasConocimiento())
				.setHabilidades(habilidades)
				.setCarreras(carreras)
				.setPreferencias(new PreferenciasUsuario()
						.setPreferenciaIdea(dto.getPreferenciaIdea()))
				.setTipo(dto.getTipoUsuario());
		
		switch(dto.getTipoUsuario()){
			case PROFESOR:
				mapProfesor(usuario, dto);
				break;
			case ESTUDIANTE:
				mapEstudiante(usuario, dto);
				break;
			case EGRESADO:
				if(dto.getSegundaCarrera() != null){
					usuario.getCarreras().add(mapDtoToCarrera(dto.getSegundaCarrera()));
				}
				break;
			default:
				break;
		}
		
		// inicializaciones
		usuario.setAmigos(new Amigos())
			.setSeguidores(new Amigos())
			.setPersonasAvaladas(new ArrayList<>())
			.setInsignias(new ArrayList<>());
		return usuario;
	}
	
	private static void mapProfesor(Usuario usuario, UsuarioDTO dto){
		List<CualidadAval> cualidades = new ArrayList<>();
		for (Cualidad cualidad: dto.getCualidades()) {
			cualidades.add(mapCualidadToCualidadAval(cualidad));
		}
		usuario.setCualidadesProfesor(cualidades)
			.setDisponibilidad(new String(""))
			.setGrupoInvestigacion(new String(""));
	}
	
	private static void mapEstudiante(Usuario usuario, UsuarioDTO dto){
		PreferenciasUsuario preferencias = usuario.getPreferencias();
		preferencias
			.setSeminario(dto.isSeminario())
			.setTemaTG(dto.isTemaTG());
		
		usuario.setPreferencias(preferencias)
			.setGustos(dto.getGustos())
			.setSemestre(dto.getSemestre());
		
		if(dto.getSegundaCarrera() != null){
			usuario.getCarreras().add(mapDtoToCarrera(dto.getSegundaCarrera()));
		}
	}

}
