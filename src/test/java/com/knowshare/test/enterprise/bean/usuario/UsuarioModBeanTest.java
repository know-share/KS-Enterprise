/**
 * 
 */
package com.knowshare.test.enterprise.bean.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.bean.usuario.UsuarioFacade;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.enterprise.utils.UtilsPassword;
import com.knowshare.entities.academia.AreaConocimiento;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.ludificacion.CualidadAval;
import com.knowshare.entities.ludificacion.HabilidadAval;
import com.knowshare.entities.ludificacion.InsigniaPreview;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Enfasis;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Personalidad;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;
import com.knowshare.enums.TipoHabilidadEnum;
import com.knowshare.enums.TipoImagenEnum;
import com.knowshare.enums.TipoUsuariosEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * Pruebas de la creación de usuarios de tipo Profesor,
 * Egresado y Estudiante
 * @author Miguel Montañez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioModBeanTest extends AbstractTest {

	@Autowired
	private UsuarioFacade usuarioModBean;

	private UsuarioDTO usuarioProfesor;
	private UsuarioDTO usuarioEstudiante;
	private UsuarioDTO usuarioEgresado;

	@Before
	public void setup() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException{
		List<Cualidad> cualidades = mongoTemplate.find(new Query(
				new Criteria().orOperator(Criteria.where("nombre").is("Cualidad 1"),Criteria.where("nombre").is("Cualidad 2"))), Cualidad.class);
		List<Gusto> gustos = mongoTemplate.findAll(Gusto.class);
		List<Habilidad> habilidades = mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("PROFESIONALES")
				.andOperator(Criteria.where("carrera.id").is("idCarreraSistemas"))), Habilidad.class);
		List<HabilidadDTO> habilidadesDto = MapEntities.mapHabilidadesToDTOs(habilidades);
		
		usuarioProfesor = crearUsuarioProfesor(MapEntities.mapCualidadesToDTOs(cualidades),habilidadesDto);
		usuarioEgresado = crearUsuarioEgreado(habilidadesDto);
		usuarioEstudiante = crearUsuarioEstudiante(habilidadesDto,gustos);
	}

	@Test
	public void test01CrearUsuarioProfesor() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioProfesor);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioProfesor.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().size());
		assertEquals(0, usuarioDB.getSeguidores().size());
		assertEquals(0, usuarioDB.getInsignias().size());
		//for hash
		assertNotEquals(usuarioProfesor.getPassword(), usuarioDB.getPassword());
		assertEquals(UtilsPassword.hashPassword(usuarioProfesor.getUsername(), usuarioProfesor.getPassword()), 
				usuarioDB.getPassword());
		assertEquals(2, usuarioDB.getAreasConocimiento().size());
		assertEquals(2, usuarioDB.getEnfasis().size());
		assertEquals(2, usuarioDB.getCualidadesProfesor().size());
		for (CualidadAval cualidades: usuarioDB.getCualidadesProfesor()) {
			assertEquals(0, cualidades.getCantidad().intValue());
		}
		assertEquals(2, usuarioDB.getHabilidades().size());
		for (HabilidadAval habilidades: usuarioDB.getHabilidades()) {
			assertEquals(0, habilidades.getCantidad().intValue());
		}
		assertEquals(0, usuarioDB.getPersonasAvaladas().size());
		assertEquals(TipoUsuariosEnum.PROFESOR, usuarioDB.getTipo());
	}
	
	@Test
	public void test02CrearUsuarioEgresado() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioEgresado);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioEgresado.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().size());
		assertEquals(0, usuarioDB.getSeguidores().size());
		assertEquals(0, usuarioDB.getInsignias().size());
		//for hash
		assertNotEquals(usuarioEgresado.getPassword(), usuarioDB.getPassword());
		assertEquals(UtilsPassword.hashPassword(usuarioEgresado.getUsername(), usuarioEgresado.getPassword()), 
				usuarioDB.getPassword());
		assertEquals(2, usuarioDB.getAreasConocimiento().size());
		assertEquals(2, usuarioDB.getEnfasis().size());
		assertEquals(2, usuarioDB.getHabilidades().size());
		for (HabilidadAval habilidades: usuarioDB.getHabilidades()) {
			assertEquals(0, habilidades.getCantidad().intValue());
		}
		assertEquals(0, usuarioDB.getPersonasAvaladas().size());
		assertEquals(TipoUsuariosEnum.EGRESADO, usuarioDB.getTipo());
	}
	
	@Test
	public void test03CrearUsuarioEstudiante() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioEstudiante);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioEstudiante.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().size());
		assertEquals(0, usuarioDB.getSeguidores().size());
		assertEquals(0, usuarioDB.getInsignias().size());
		//for hash
		assertNotEquals(usuarioEstudiante.getPassword(), usuarioDB.getPassword());
		assertEquals(UtilsPassword.hashPassword(usuarioEstudiante.getUsername(), usuarioEstudiante.getPassword()), 
				usuarioDB.getPassword());
		assertEquals(2, usuarioDB.getAreasConocimiento().size());
		assertEquals(2, usuarioDB.getEnfasis().size());
		assertEquals(4, usuarioDB.getGustos().size());
		assertEquals(2, usuarioDB.getHabilidades().size());
		for (HabilidadAval habilidades: usuarioDB.getHabilidades()) {
			assertEquals(0, habilidades.getCantidad().intValue());
		}
		assertEquals(0, usuarioDB.getPersonasAvaladas().size());
		assertNotNull(usuarioDB.getSemestre());
		assertNotNull(usuarioDB.getPreferencias().isSeminario());
		assertNotNull(usuarioDB.getPreferencias().isTemaTG());
		assertEquals(TipoUsuariosEnum.ESTUDIANTE, usuarioDB.getTipo());
	}
	
	@Test
	public void test04Seguir(){
		boolean res = usuarioModBean.seguir("minmiguelm", "felipe-bautista");
		assertFalse(res);
		
		res = usuarioModBean.seguir("minmiguelm", "pablo.gaitan");
		assertTrue(res);
	}
	
	@Test
	public void test05DejarSeguir(){
		boolean res = usuarioModBean.dejarSeguir("minmiguelm","felipe-bautista");
		assertTrue(res);
		
		res = usuarioModBean.dejarSeguir("minmiguelm","felipe-bautista");
		assertFalse(res);
	}
	
	@Test
	public void test06SolicitudAmistad(){
		boolean res = usuarioModBean.solicitudAmistad("felipe-bautista","pablo.gaitan");
		assertFalse(res);
		
		res = usuarioModBean.solicitudAmistad("pablo.gaitan","minmiguelm");
		assertTrue(res);
	}
	
	@Test
	public void test07AccionSolicitud(){
		boolean res = usuarioModBean.accionSolicitud("pablo.gaitan","felipe-bautista", "reject");
		assertTrue(res);
		
		Usuario usuario = ((List<Usuario>) mongoTemplate.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		assertNotNull(usuario);
		assertEquals(0, usuario.getSolicitudesAmistad().size());
		assertEquals(0, usuario.getAmigos().size());
		
		usuarioModBean.solicitudAmistad("pablo.gaitan","felipe-bautista");
		
		res = usuarioModBean.accionSolicitud("felipe-bautista","pablo.gaitan", "accept");
		assertTrue(res);
		
		usuario = ((List<Usuario>)mongoTemplate.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		assertNotNull(usuario);
		assertEquals(0, usuario.getSolicitudesAmistad().size());
		assertEquals(1, usuario.getAmigos().size());
	}
	
	@Test
	public void test08AgregarTGDirigido(){
		final TrabajoGrado tg = new TrabajoGrado()
				.setNombre("TG for Profesor-Inserted")
				.setNumEstudiantes(5)
				.setPeriodoFin("2017-3")
				.setResumen("Summary for TG inserted.");
		boolean res = usuarioModBean.agregarTGDirigido(tg, usuarioProfesor.getUsername());
		assertTrue(res);
		
		Usuario usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		assertNotNull(usuario);
		assertEquals(1, usuario.getTrabajosGradoDirigidos().size());
	}
	
	@Test
	public void test09AgregarFormacionAcademica(){
		final FormacionAcademica fa = new FormacionAcademica()
				.setAnio(2016)
				.setTitulo("Titulo pregrado")
				.setTituloTG("Titulo de TG")
				.setUniversidad("Universidad para profesor inserted");
		boolean res = usuarioModBean.agregarFormacionAcademica(fa, usuarioProfesor.getUsername());
		assertTrue(res);
		
		Usuario usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		assertNotNull(usuario);
		assertEquals(1, usuario.getFormacionesAcademicas().size());
	}
	
	@Test
	public void test10EliminarAmigo(){
		boolean res = usuarioModBean.eliminarAmigo("felipe-bautista", "minmiguelm");
		assertFalse(res);
		
		res = usuarioModBean.eliminarAmigo("felipe-bautista", "pablo.gaitan");
		assertTrue(res);
		
		Usuario usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		assertNotNull(usuario);
		assertEquals(0, usuario.getAmigos().size());
	}
	
	@Test
	public void test11ActualizarInfoAcademica(){
		Usuario usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		final List<Habilidad> habilidades = mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("PROFESIONALES")
				.andOperator(Criteria.where("carrera.id").is("idCarreraCivil"))), Habilidad.class);
		final UsuarioDTO dto = MapEntities.mapUsuarioToDTO(usuario);
		if(null != dto.getSegundaCarrera())
			dto.getSegundaCarrera().setId("idCarreraCivil");
		else
			dto.setSegundaCarrera(new CarreraDTO()
					.setId("idCarreraCivil"));
		dto.getEnfasis().get(0).setNombre("Enfasis sistemas 2");
		dto.getEnfasis().add(new Enfasis().setCarrera("Ingeniería civil").setNombre("Enfasis civil 2"));
		dto.getEnfasis().add(null);
		dto.getAreasConocimiento().add(new AreaConocimiento().setNombre("AC civil 1").setCarrera("Ingeniería civil")
				.setPorcentaje(0d));
		dto.getHabilidades().add(new HabilidadDTO().setAvales(0).setCarrera("Ingeniería civil").setNombre("Habilidad Profesional civil 1")
				.setTipo(TipoHabilidadEnum.PROFESIONALES).setId(habilidades.get(0).getId()));
		
		assertTrue(usuarioModBean.actualizarInfoAcademica(dto));
		
		usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		assertEquals(2, usuario.getCarreras().size());
		assertEquals("idCarreraCivil", usuario.getCarreras().get(1).getId());
		
		assertEquals(4, usuario.getEnfasis().size());
		assertEquals("Enfasis sistemas 2", usuario.getEnfasis().get(0).getNombre());
		assertEquals("Enfasis civil 2", usuario.getEnfasis().get(2).getNombre());
		assertNull(usuario.getEnfasis().get(3));
		
		assertEquals(2, usuario.getAreasConocimiento().size());
		assertEquals("AC civil 1", usuario.getAreasConocimiento().get(1).getNombre());
		
		assertEquals(3, usuario.getHabilidades().size());
		assertEquals("Habilidad Profesional civil 1", usuario.getHabilidades().get(2).getHabilidad().getNombre());
		
		assertFalse(usuarioModBean.actualizarInfoAcademica(
				new UsuarioDTO().setTipoUsuario(TipoUsuariosEnum.ADMIN)));
	}
	
	@Test
	public void test12ActualizarHabilidad(){
		Usuario usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		usuario.getHabilidades().clear();
		assertTrue(usuarioModBean.actualizarHabilidadCualidad(MapEntities.mapUsuarioToDTO(usuario)));
		
		usuario = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		assertEquals(0,usuario.getHabilidades().size());
		
		assertFalse(usuarioModBean.actualizarHabilidadCualidad(
				new UsuarioDTO().setTipoUsuario(TipoUsuariosEnum.ADMIN)));
	}
	
	@Test
	public void test13ActualizarCualidad(){
		final List<Cualidad> cualidades = mongoTemplate.find(new Query(
				new Criteria().orOperator(Criteria.where("nombre").is("Cualidad 3"),Criteria.where("nombre").is("Cualidad 4"))), Cualidad.class);
		Usuario profesor = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		final UsuarioDTO dto = MapEntities.mapUsuarioToDTO(profesor);
		dto.getCualidades().addAll(MapEntities.mapCualidadesToDTOs(cualidades));
		assertTrue(usuarioModBean.actualizarHabilidadCualidad(dto));
		
		profesor = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		assertEquals(4, profesor.getCualidadesProfesor().size());
		assertEquals("Cualidad 3", profesor.getCualidadesProfesor().get(2).getCualidad().getNombre());
		assertEquals("Cualidad 4", profesor.getCualidadesProfesor().get(3).getCualidad().getNombre());
	}
	
	@Test
	public void test14ActualizarBasisEstudiante(){
		Usuario estudiante = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioEstudiante.getUsername())), Usuario.class)).get(0);
		estudiante.setApellido("This is a new last name")
			.setNombre("This is a new name")
			.setCorreo("correoforupdate@mail.com")
			.setSemestre(8);
		assertTrue(usuarioModBean.actualizarBasis(MapEntities.mapUsuarioToDTO(estudiante)));
		
		estudiante = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioEstudiante.getUsername())), Usuario.class)).get(0);
		
		assertEquals("This is a new last name", estudiante.getApellido());
		assertEquals("This is a new name", estudiante.getNombre());
		assertEquals("correoforupdate@mail.com", estudiante.getCorreo());
		assertEquals(Integer.valueOf(8), estudiante.getSemestre());
		
		assertFalse(usuarioModBean.actualizarBasis(
				new UsuarioDTO().setTipoUsuario(TipoUsuariosEnum.ADMIN)));
	}
	
	@Test
	public void test15ActualizarBasisProfesor(){
		Usuario profesor = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		profesor.setApellido("This is a new last name profesor")
			.setNombre("This is a new name profesor")
			.setCorreo("correoforupdateprofesor@mail.com")
			.setGrupoInvestigacion("ISTAR");
		assertTrue(usuarioModBean.actualizarBasis(MapEntities.mapUsuarioToDTO(profesor)));
		
		profesor = ((List<Usuario>)mongoTemplate
				.find(new Query(Criteria.where("username").is(usuarioProfesor.getUsername())), Usuario.class)).get(0);
		
		assertEquals("This is a new last name profesor", profesor.getApellido());
		assertEquals("This is a new name profesor", profesor.getNombre());
		assertEquals("correoforupdateprofesor@mail.com", profesor.getCorreo());
		assertEquals("ISTAR", profesor.getGrupoInvestigacion());
	}
	
	@Test
	public void test16UploadImage(){
		MultipartFile file = getFile("test.txt","image/text");
		boolean result = usuarioModBean.uploadImage("pablo.gaitan", file);
		assertFalse(result);
		
		// jpg
		file = getFile("upload_image.jpg","image/jpg");
		result = usuarioModBean.uploadImage("pablo.gaitan", file);
		assertTrue(result);
		assertsImage(TipoImagenEnum.JPG,"pablo.gaitan.jpg");
		
		// jpeg
		file = getFile("upload_image.jpeg","image/jpeg");
		result = usuarioModBean.uploadImage("pablo.gaitan", file);
		assertTrue(result);
		assertsImage(TipoImagenEnum.JPEG,"pablo.gaitan.jpeg");
		
		// png
		file = getFile("upload_image.png","image/png");
		result = usuarioModBean.uploadImage("pablo.gaitan", file);
		assertTrue(result);
		assertsImage(TipoImagenEnum.PNG,"pablo.gaitan.png");
	}
	
	@Test
	public void test17UpdatePreferenciaIdea(){
		boolean result = usuarioModBean
				.updatePreferenciaIdea("MinMiguelM", PreferenciaIdeaEnum.ORDEN_CRONOLOGICO);
		assertTrue(result);
		assertEquals(PreferenciaIdeaEnum.ORDEN_CRONOLOGICO,mongoTemplate.findOne(new Query(Criteria.where("username").is("MinMiguelM")), Usuario.class)
			.getPreferencias().getPreferenciaIdea());
		
		result = usuarioModBean
				.updatePreferenciaIdea("MinMiguelM", PreferenciaIdeaEnum.POR_RELEVANCIA);
		assertTrue(result);
		assertEquals(PreferenciaIdeaEnum.POR_RELEVANCIA,mongoTemplate.findOne(new Query(Criteria.where("username").is("MinMiguelM")), Usuario.class)
			.getPreferencias().getPreferenciaIdea());
		
		result = usuarioModBean
				.updatePreferenciaIdea("", PreferenciaIdeaEnum.POR_RELEVANCIA);
		assertFalse(result);
	}
	
	@Test
	public void test18UpdateInsignias(){
		boolean result = usuarioModBean.updateInsignias("Felipe-Bautista");
		assertTrue(result);
		final Usuario usuario = mongoTemplate.findOne(
				new Query(Criteria.where("username").is("Felipe-Bautista")), Usuario.class);
		for(InsigniaPreview i:usuario.getInsignias()){
			assertTrue(i.isVisto());
		}
	}
	
	@Test
	public void test19PromoteEstudiante(){
		boolean result = usuarioModBean.promoteEstudiante("");
		assertFalse(result);
		
		result = usuarioModBean.promoteEstudiante(usuarioEgresado.getNombre());
		assertFalse(result);
		
		result = usuarioModBean.promoteEstudiante("Felipe-Bautista");
		assertTrue(result);
		
		assertEquals(TipoUsuariosEnum.EGRESADO, mongoTemplate.findOne(
				new Query(Criteria.where("username").is("Felipe-Bautista")), 
				Usuario.class)
					.getTipo());
	}
	
	@Test
	public void test20ActualizarGustos(){
		boolean result = usuarioModBean.actualizarGustos(new ArrayList<>(), "");
		assertFalse(result);
		
		result = usuarioModBean.actualizarGustos(new ArrayList<>(), "Felipe-Bautista");
		assertTrue(result);
		assertEquals(0, mongoTemplate.findOne(
				new Query(Criteria.where("username").is("Felipe-Bautista")), 
				Usuario.class)
					.getGustos().size());
	}
	
	private void assertsImage(TipoImagenEnum type, String image){
		byte[] content = null;
		try{
			Path path = Paths.get(ResourceUtils
					.getURL("classpath:knowshare_uploads_test/"+image)
					.getPath());
			content = Files.readAllBytes(path);
		}catch(IOException e){
			content = null;
		}
		assertNotNull(content);
		assertEquals(type,mongoTemplate.findOne(new Query(Criteria.where("username")
					.is("pablo.gaitan")), Usuario.class)
				.getImagen().getType());
	}
	
	private MultipartFile getFile(String image, String contentType){
		Path path = Paths.get("classpath:"+image);
		byte[] content = null;
		try{
			content = Files.readAllBytes(path);
		}catch(IOException e){
			e.printStackTrace();
		}
		return new MockMultipartFile(image,image,contentType,content);
	}
	
	private UsuarioDTO crearUsuarioEstudiante(List<HabilidadDTO> habilidadesDto, List<Gusto> gustos) {
		return new UsuarioDTO().setApellido("Apellido estudiante inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 2")
								.setPorcentaje(0d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 3")
								.setPorcentaje(0d)))
				.setCarrera(new CarreraDTO().setId("idCarreraSistemas"))
				.setEmail("Email estudiante inserted").setGustos(gustos)
				.setEnfasis(Arrays.asList(
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 2"),
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 3")))
				.setHabilidades(habilidadesDto).setNombre("Nombre estudiante inserted").setPassword("PasswordEstudiante1")
				.setPersonalidad(new Personalidad().setNombre("INTP"))
				.setPreferenciaIdea(PreferenciaIdeaEnum.ORDEN_CRONOLOGICO).setTipoUsuario(TipoUsuariosEnum.ESTUDIANTE)
				.setUsername("Estudiante1-Inserted")
				.setSemestre(5).setSeminario(false).setTemaTG(false);
	}
	
	private UsuarioDTO crearUsuarioEgreado(List<HabilidadDTO> habilidadesDto) {
		return new UsuarioDTO().setApellido("Apellido egresado inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 1")
								.setPorcentaje(0d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 3")
								.setPorcentaje(0d)))
				.setCarrera(new CarreraDTO().setId("idCarreraSistemas"))
				.setEmail("Email egresado inserted")
				.setEnfasis(Arrays.asList(
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 2"),
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 4")))
				.setHabilidades(habilidadesDto).setNombre("Nombre egresado inserted").setPassword("PasswordEgresado1")
				.setPersonalidad(new Personalidad().setNombre("INTP"))
				.setPreferenciaIdea(PreferenciaIdeaEnum.POR_RELEVANCIA).setTipoUsuario(TipoUsuariosEnum.EGRESADO)
				.setUsername("Egresado1-Inserted");
	}
	
	private UsuarioDTO crearUsuarioProfesor(List<CualidadDTO> cualidades, List<HabilidadDTO> habilidadesDto) {
		return new UsuarioDTO().setApellido("Apellido profesor inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 1")
								.setPorcentaje(20d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 2")
								.setPorcentaje(30d)))
				.setCarrera(new CarreraDTO().setId("idCarreraSistemas")).setCualidades(cualidades)
				.setEmail("Email profesor inserted")
				.setEnfasis(Arrays.asList(
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 1"),
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 4")))
				.setHabilidades(habilidadesDto).setNombre("Nombre profesor inserted").setPassword("PasswordProfesor1")
				.setPersonalidad(new Personalidad().setNombre("INTJ"))
				.setPreferenciaIdea(PreferenciaIdeaEnum.POR_RELEVANCIA).setTipoUsuario(TipoUsuariosEnum.PROFESOR)
				.setUsername("Profesor1-Inserted");
	}
}
