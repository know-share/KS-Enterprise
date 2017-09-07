/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.dto.ludificacion.InsigniaDTO;
import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.app.PreferenciasUsuario;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.ludificacion.CualidadAval;
import com.knowshare.entities.ludificacion.HabilidadAval;
import com.knowshare.entities.ludificacion.InsigniaPreview;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;
import com.knowshare.enums.TipoHabilidadEnum;
import com.knowshare.enums.TipoIdeaEnum;

/**
 * Clase encargada de hacer los mapeos de entidades a dtos
 * o viceversa.
 * @author Miguel Montañez
 *
 */
public class MapEntities {
	
	private MapEntities(){}
	
	/**
	 * Mapea una lista de {@link Carrera Carreras} a {@link CarreraDTO CarrerasDTO}
	 * @param carreras
	 * @return Lista de {@link CarreraDTO CarrerasDTO}
	 */
	public static List<CarreraDTO> mapCarrerasToDTOs(List<Carrera> carreras){
		final List<CarreraDTO> dtos = new ArrayList<>();
		for (Carrera carrera : carreras)
			dtos.add(mapCarreraToDTO(carrera));
		return dtos;
	}
	
	/**
	 * Mapea una {@link Carrera} a {@link CarreraDTO}
	 * @param carrera
	 * @return {@link CarreraDTO}
	 */
	public static CarreraDTO mapCarreraToDTO(Carrera carrera){
		if(null == carrera)
			return null;
		return new CarreraDTO()
				.setFacultad(carrera.getFacultad())
				.setId(carrera.getId())
				.setNombre(carrera.getNombre())
				.setCarrerasAfines(carrerasAfinesNames(carrera.getCarrerasAfines()))
				.setEnfasis(carrera.getEnfasis());
	}
	
	/**
	 * Mapea una {@link CarreraDTO} a {@link Carrera}
	 * @param dto
	 * @return {@link Carrera}
	 */
	public static Carrera mapDtoToCarrera(CarreraDTO dto){
		if(null != dto)
			return new Carrera().setId(dto.getId())
					.setFacultad(dto.getFacultad())
					.setNombre(dto.getNombre())
					.setEnfasis(dto.getEnfasis());
		return null;
	}
	
	/**
	 * Mapea una lista de {@link Carrera Carreras} a lista de String
	 * @param carreras
	 * @return lista de String, que contiene los nombres
	 */
	public static List<String> carrerasAfinesNames(List<Carrera> carreras){
		final List<String> carrerasNames = new ArrayList<>();
		if(null != carreras)
			for (Carrera carrera : carreras) {
				carrerasNames.add(carrera.getNombre());
			}
		return carrerasNames;
	}
	
	/**
	 * Mapea una lista de {@link Habilidad Habilidades} a {@link HabilidadDTO habilidadesDTO}
	 * @param habilidades
	 * @return Lista de {@link HabilidadDTO habilidadesDTO}
	 */
	public static List<HabilidadDTO> mapHabilidadesToDTOs(List<Habilidad> habilidades){
		List<HabilidadDTO> dtos = new ArrayList<>();
		for (Habilidad habilidad : habilidades) {
			dtos.add(mapHabilidadToDTO(habilidad));
		}
		return dtos;
	}
	
	/**
	 * Mapea una {@link Habilidad} a {@link HabilidadDTO}
	 * @param habilidad
	 * @return {@link HabilidadDTO}
	 */
	public static HabilidadDTO mapHabilidadToDTO(Habilidad habilidad){
		final HabilidadDTO dto = new HabilidadDTO()
				.setId(habilidad.getId())
				.setNombre(habilidad.getNombre())
				.setTipo(habilidad.getTipo());
		if(habilidad.getTipo().equals(TipoHabilidadEnum.PROFESIONALES))
			dto.setCarrera(habilidad.getCarrera().getNombre());
				
		return dto;
	}
	
	/**
	 * Mapea una {@link HabilidadDTO} a {@link HabilidadAval}
	 * @param habilidad
	 * @return {@link HabilidadAval}
	 */
	public static HabilidadAval mapDtoToHabilidadAval(HabilidadDTO habilidad){
		return new HabilidadAval()
				.setCantidad(habilidad.getAvales() == null ? 0 : habilidad.getAvales())
				.setHabilidad(new Habilidad().setId(habilidad.getId()))
				.setNombre(habilidad.getNombre());
	}
	
	/**
	 * Mapea una lista de {@link HabilidadDTO habilidadesDTO} a {@link HabilidadAval habilidadesAval}
	 * @param dtos
	 * @return Lista de {@link HabilidadAval habilidadesAval}
	 */
	public static List<HabilidadAval> mapDtosToHabilidadAval(List<HabilidadDTO> dtos){
		final List<HabilidadAval> habilidades = new ArrayList<>();
		for(HabilidadDTO dto: dtos)
			habilidades.add(mapDtoToHabilidadAval(dto));
		return habilidades;
	}
	
	/**
	 * Mapea una lista de {@link CualidadDTO cualidades} a {@link CualidadAval cualidadesAval}
	 * @param dtos
	 * @return Lista de {@link CualidadAval cualidadesAval}
	 */
	public static List<CualidadAval> mapDtosToCualidadAval(List<CualidadDTO> dtos){
		final List<CualidadAval> cualidades = new ArrayList<>();
		for(CualidadDTO dto: dtos)
			cualidades.add(mapDtoToCualidadAval(dto));
		return cualidades;
	}
	
	/**
	 * Mapea una {@link CualidadDTO} a {@link CualidadAval}
	 * @param cualidad
	 * @return {@link CualidadAval}
	 */
	public static CualidadAval mapDtoToCualidadAval(CualidadDTO cualidad){
		return new CualidadAval()
				.setCantidad(cualidad.getAvales() == null ? 0 : cualidad.getAvales())
				.setCualidad(new Cualidad().setId(cualidad.getId()))
				.setNombre(cualidad.getNombre());
	}
	
	/**
	 * Mapea una {@link Cualidad} a {@link CualidadDTO}
	 * @param cualidad
	 * @return {@link CualidadDTO}
	 */
	public static CualidadDTO mapCualidadToDTO(Cualidad cualidad){
		return new CualidadDTO().setId(cualidad.getId())
				.setNombre(cualidad.getNombre())
				.setTipo(cualidad.getTipo());
	}
	
	/**
	 * Mapea una lista de {@link Cualidad cualidades} a {@link CualidadDTO cualidadesDTO}
	 * @param cualidades
	 * @return Lista de {@link CualidadDTO cualidadesDTO}
	 */
	public static List<CualidadDTO> mapCualidadesToDTOs(List<Cualidad> cualidades){
		List<CualidadDTO> dtos = new ArrayList<>();
		for (Cualidad cualidad : cualidades) {
			dtos.add(mapCualidadToDTO(cualidad));
		}
		return dtos;
	}
	
	/**
	 * Mapea una {@link Idea} a {@link IdeaDTO}
	 * @param idea
	 * @return {@link IdeaDTO}
	 */
	public static IdeaDTO mapIdeaToDTO (Idea idea){
		IdeaDTO dto = new IdeaDTO();
		dto.setId(idea.getId());
		dto.setAlcance(idea.getAlcance());
		dto.setNumeroEstudiantes(idea.getNumeroEstudiantes());
		dto.setComentarios(idea.getComentarios());
		dto.setContenido(idea.getContenido());
		dto.setEstado(idea.getEstado());
		dto.setLights(idea.getLights());
		dto.setProblematica(idea.getProblematica());
		dto.setTags(idea.getTags());
		dto.setTipo(idea.getTipo());
		dto.setFechaCreacion(idea.getFechaCreacion());
		dto.setUsuario(idea.getUsuario().getUsername());
		dto.setCompartida(idea.isCompartida());
		dto.setUsuarioOriginal(idea.getUsuarioOriginal());
		dto.setIdeasProyecto(new ArrayList<>());	
		if(idea.getTipo().equals(TipoIdeaEnum.PR)){
			for (Idea i : idea.getIdeasProyecto()) {
				dto.getIdeasProyecto().add(mapIdeaToDTO(i));
			}
		}
		dto.setTg(idea.getTg());
		return dto;
	}
	
	/**
	 * Mapea una {@link IdeaDTO} a {@link Idea}
	 * @param dto
	 * @param usuario creador de la idea
	 * @return {@link Idea}
	 * @throws NoSuchAlgorithmException
	 */
	public static Idea mapDtoToIdea(IdeaDTO dto,Usuario usuario) throws NoSuchAlgorithmException{
		Idea idea = new Idea();
		idea.setId(dto.getId());
		idea.setAlcance(dto.getAlcance());
		idea.setContenido(dto.getContenido());
		idea.setEstado(dto.getEstado());
		if(dto.getTipo().equals(TipoIdeaEnum.PR)){
			for (IdeaDTO i : dto.getIdeasProyecto()) {
				idea.getIdeasProyecto().add(mapDtoToIdea(i,usuario));
			}
		}
		if(dto.getTipo().equals(TipoIdeaEnum.PC)){
			idea.setTg(dto.getTg());
		}
		idea.setLugarEscritura(dto.getLugarEscritura());
		idea.setNumeroEstudiantes(dto.getNumeroEstudiantes());
		idea.setProblematica(dto.getProblematica());
		idea.setTags(dto.getTags());
		idea.setTipo(dto.getTipo());
		idea.setFechaCreacion(dto.getFechaCreacion());
		idea.setUsuario(usuario);
		idea.setCompartida(dto.isCompartida());
		idea.setUsuarioOriginal(dto.getUsuarioOriginal());
		if(idea.getTipo().equals(TipoIdeaEnum.PC))
			idea.setTg(dto.getTg());
		return idea;
	}
	
	/**
	 * Mapea un {@link UsuarioDTO} a {@link Usuario}
	 * (Usado solamente en el registro de usuarios)
	 * @param dto
	 * @return {@link Usuario}
	 * @throws NoSuchAlgorithmException
	 */
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
				.setId(dto.getId())
				.setNombre(dto.getNombre())
				.setApellido(dto.getApellido())
				.setCorreo(dto.getEmail())
				.setUsername(dto.getUsername())
				.setPassword(passwordHashed)
				.setFechaRegistro(new Date())
				.setGenero(dto.getGenero())
				.setPersonalidad(dto.getPersonalidad())
				.setEnfasis(dto.getEnfasis())
				.setAreasConocimiento(dto.getAreasConocimiento())
				.setHabilidades(habilidades)
				.setCarreras(carreras)
				.setPreferencias(new PreferenciasUsuario()
						.setPreferenciaIdea(PreferenciaIdeaEnum.ORDEN_CRONOLOGICO))
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
		usuario.setAmigos(new ArrayList<>())
			.setSeguidores(new ArrayList<>())
			.setSolicitudesAmistad(new ArrayList<>())
			.setPersonasAvaladas(new ArrayList<>())
			.setInsignias(new ArrayList<>())
			.setTrabajosGrado(new ArrayList<>())
			.setSiguiendo(new ArrayList<>())
			.setFormacionesAcademicas(new ArrayList<>())
			.setPreferenciaIdeas(new ArrayList<>());
		return usuario;
	}
	
	/**
	 * Mapea un {@link UsuarioDTO} a {@link Usuario}
	 * (Mapea algunos atributos importantes)
	 * @param dto
	 * @return {@link Usuario}
	 */
	public static Usuario mapDtoToUsuarioPartial(UsuarioDTO dto){
		final List<HabilidadAval> habilidades = new ArrayList<>();
		if(dto.getHabilidades() != null)
			for (HabilidadDTO habilidad : dto.getHabilidades()) {
				habilidades.add(mapDtoToHabilidadAval(habilidad));
			}
		final List<Carrera> carreras = new ArrayList<>();
		carreras.add(mapDtoToCarrera(dto.getCarrera()));
		final Usuario usuario = new Usuario()
				.setCarreras(carreras)
				.setEnfasis(dto.getEnfasis())
				.setAreasConocimiento(dto.getAreasConocimiento())
				.setHabilidades(habilidades);
		switch(dto.getTipoUsuario()){
			case ESTUDIANTE:
			case EGRESADO:
				if(dto.getSegundaCarrera() != null){
					usuario.getCarreras().add(mapDtoToCarrera(dto.getSegundaCarrera()));
				}
				break;
			default:
				break;
		}
		return usuario;
	}
	
	/**
	 * Mapea atributos que solo posee un TipoUsuariosEnum.PROFESOR
	 * @param usuario
	 * @param dto
	 */
	public static void mapProfesor(Usuario usuario, UsuarioDTO dto){
		List<CualidadAval> cualidades = new ArrayList<>();
		for (CualidadDTO cualidad: dto.getCualidades()) {
			cualidades.add(mapDtoToCualidadAval(cualidad));
		}
		usuario.setCualidadesProfesor(cualidades)
			.setDisponible(dto.isDisponible())
			.setGrupoInvestigacion(dto.getGrupoInvestigacion())
			.setTrabajosGradoDirigidos(new ArrayList<>());
	}
	
	/**
	 * Mapea atributos que solo posee un TipoUsuariosEnum.ESTUDIANTE
	 * @param usuario
	 * @param dto
	 */
	public static void mapEstudiante(Usuario usuario, UsuarioDTO dto){
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
	
	/**
	 * Mapea una lista de {@link HabilidadAval habilidadesAval} a {@link HabilidadDTO habilidadesDTO}
	 * @param habilidadesAvales
	 * @return Lista de {@link HabilidadDTO habilidadesDTO}
	 */
	public static List<HabilidadDTO> mapAvalesHabilidad(List<HabilidadAval> habilidadesAvales){
		List<HabilidadDTO> dtos = new ArrayList<>();
		for(HabilidadAval aval : habilidadesAvales){
			HabilidadDTO dto = mapHabilidadToDTO(aval.getHabilidad());
			dto.setAvales(aval.getCantidad());
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * Mapea una lista de {@link CualidadAval cualidadesAval} a {@link CualidadDTO cualidadesDTO}
	 * @param cualidadesAvales
	 * @return Lista de {@link CualidadDTO cualidadesDTO}
	 */
	public static List<CualidadDTO> mapAvalesCualidad(List<CualidadAval> cualidadesAvales){
		List<CualidadDTO> dtos = new ArrayList<>();
		for (CualidadAval aval: cualidadesAvales) {
			CualidadDTO dto = mapCualidadToDTO(aval.getCualidad());
			dto.setAvales(aval.getCantidad());
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * Mapea un {@link Usuario} a {@link UsuarioDTO}
	 * @param usuario
	 * @return {@link UsuarioDTO}
	 */
	public static UsuarioDTO mapUsuarioToDTO(Usuario usuario){
		UsuarioDTO dto = new UsuarioDTO();
		dto.setApellido(usuario.getApellido())
			.setId(usuario.getId())
			.setEmail(usuario.getCorreo())
			.setSemestre(usuario.getSemestre())
			.setGrupoInvestigacion(usuario.getGrupoInvestigacion())
			.setGenero(usuario.getGenero())
			.setCantidadAmigos(usuario.getAmigos().size())
			.setCantidadSeguidores(usuario.getSeguidores().size())
			.setCarrera(mapCarreraToDTO(usuario.getCarreras().get(0)))
			.setUsername(usuario.getUsername())
			.setTipoUsuario(usuario.getTipo())
			.setPersonalidad(usuario.getPersonalidad())
			.setHabilidades(mapAvalesHabilidad(usuario.getHabilidades()))
			.setNombre(usuario.getNombre())
			.setAreasConocimiento(usuario.getAreasConocimiento())
			.setSeminario(usuario.getPreferencias().isSeminario())
			.setTemaTG(usuario.getPreferencias().isTemaTG())
			.setEnfasis(usuario.getEnfasis())
			.setAmigos(usuario.getAmigos())
			.setSeguidores(usuario.getSeguidores())
			.setSiguiendo(usuario.getSiguiendo())
			.setSolicitudesAmistad(usuario.getSolicitudesAmistad())
			.setFormacionAcademica(usuario.getFormacionesAcademicas())
			.setPreferenciaIdea(usuario.getPreferencias().getPreferenciaIdea())
			.setInsignias(mapInsigniasToDTOs(usuario.getInsignias()))
			.setImagen(usuario.getImagen() != null);
		switch(usuario.getTipo()){
			case PROFESOR:
				dto.setCualidades(mapAvalesCualidad(usuario.getCualidadesProfesor()))
					.setDisponible(usuario.isDisponible())
					.setTgDirigidos(usuario.getTrabajosGradoDirigidos());
				break;
			case ESTUDIANTE:
				dto.setGustos(usuario.getGustos());
			case EGRESADO:
				dto.setTgDirigidos(usuario.getTrabajosGrado());
				if(usuario.getCarreras().size() > 1)
					dto.setSegundaCarrera(mapCarreraToDTO(usuario.getCarreras().get(1)));
				break;
			default:
				break;
			
		}
			
		return dto;
	}
	
	/**
	 * Mapea una lista de {@link InsigniaPreview insignias} a {@link InsigniaDTO insigniasDTO}
	 * @param insignias
	 * @return Lista de {@link InsigniaDTO insigniasDTO}
	 */
	public static List<InsigniaDTO> mapInsigniasToDTOs(List<InsigniaPreview> insignias){
		return insignias.stream()
				.map(ins -> mapInsigniaToDTO(ins))
				.collect(Collectors.toList());
	}

	/**
	 * Mapea una {@link InsigniaPreview} a {@link InsigniaDTO}
	 * @param insignia
	 * @return {@link InsigniaDTO}
	 */
	public static InsigniaDTO mapInsigniaToDTO(InsigniaPreview insignia){
		return new InsigniaDTO().setDescripcion(insignia.getInsignia().getDescripcion())
				.setIconoRef(insignia.getInsignia().getId() + ".png")
				.setId(insignia.getInsignia().getId())
				.setNombre(insignia.getInsignia().getNombre())
				.setVisto(insignia.isVisto());
	}
}
