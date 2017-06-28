/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.dto.perfilusuario.CualidadDTO;
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
	
	public static List<CarreraDTO> mapCarrerasToDTOs(List<Carrera> carreras){
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
	
	public static CarreraDTO mapCarreraToDTO(Carrera carrera){
		return new CarreraDTO()
				.setFacultad(carrera.getFacultad())
				.setNombre(carrera.getNombre())
				.setCarrerasAfines(carrerasAfinesNames(carrera.getCarrerasAfines()));
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
	
	public static List<HabilidadDTO> mapHabilidadesToDTOs(List<Habilidad> habilidades){
		List<HabilidadDTO> dtos = new ArrayList<>();
		for (Habilidad habilidad : habilidades) {
			dtos.add(mapHabilidadToDTO(habilidad));
		}
		return dtos;
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
				.setCantidad(habilidad.getAvales() == null ? 0 : habilidad.getAvales())
				.setHabilidad(new Habilidad().setId(habilidad.getId()));
	}
	
	public static CualidadAval mapDtoToCualidadAval(CualidadDTO cualidad){
		return new CualidadAval()
				.setCantidad(cualidad.getAvales() == null ? 0 : cualidad.getAvales())
				.setCualidad(new Cualidad().setId(cualidad.getId()));
	}
	
	public static CualidadDTO mapCualidadToDTO(Cualidad cualidad){
		return new CualidadDTO().setId(cualidad.getId())
				.setNombre(cualidad.getNombre())
				.setTipo(cualidad.getTipo());
	}
	
	public static List<CualidadDTO> mapCualidadesToDTOs(List<Cualidad> cualidades){
		List<CualidadDTO> dtos = new ArrayList<>();
		for (Cualidad cualidad : cualidades) {
			dtos.add(mapCualidadToDTO(cualidad));
		}
		return dtos;
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
		for (CualidadDTO cualidad: dto.getCualidades()) {
			cualidades.add(mapDtoToCualidadAval(cualidad));
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
	
	public static List<HabilidadDTO> mapAvalesHabilidad(List<HabilidadAval> habilidadesAvales){
		List<HabilidadDTO> dtos = new ArrayList<>();
		for(HabilidadAval aval : habilidadesAvales){
			HabilidadDTO dto = mapHabilidadToDTO(aval.getHabilidad());
			dto.setAvales(aval.getCantidad());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public static List<CualidadDTO> mapAvalesCualidad(List<CualidadAval> cualidadesAvales){
		List<CualidadDTO> dtos = new ArrayList<>();
		for (CualidadAval aval: cualidadesAvales) {
			CualidadDTO dto = mapCualidadToDTO(aval.getCualidad());
			dto.setAvales(aval.getCantidad());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public static UsuarioDTO mapUsuarioToDTO(Usuario usuario){
		UsuarioDTO dto = new UsuarioDTO();
		dto.setApellido(usuario.getApellido())
			.setCantidadAmigos(usuario.getAmigos().getCantidad())
			.setCantidadSeguidores(usuario.getSeguidores().getCantidad())
			.setCarrera(mapCarreraToDTO(usuario.getCarreras().get(0)))
			.setUsername(usuario.getUsername())
			.setTipoUsuario(usuario.getTipo())
			.setPersonalidad(usuario.getPersonalidad())
			.setHabilidades(mapAvalesHabilidad(usuario.getHabilidades()))
			.setNombre(usuario.getNombre())
			.setEnfasis(usuario.getEnfasis());
		switch(usuario.getTipo()){
			case PROFESOR:
				dto.setCualidades(mapAvalesCualidad(usuario.getCualidadesProfesor()));
				break;
			case ESTUDIANTE:
			case EGRESADO:
				if(usuario.getCarreras().size() > 1)
					dto.setSegundaCarrera(mapCarreraToDTO(usuario.getCarreras().get(1)));
				break;
			default:
				break;
			
		}
			
		return dto;
	}

}
