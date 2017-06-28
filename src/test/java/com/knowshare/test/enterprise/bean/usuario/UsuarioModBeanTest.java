/**
 * 
 */
package com.knowshare.test.enterprise.bean.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.bean.usuario.UsuarioModFacade;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.enterprise.utils.UtilsPassword;
import com.knowshare.entities.academia.AreaConocimiento;
import com.knowshare.entities.ludificacion.CualidadAval;
import com.knowshare.entities.ludificacion.HabilidadAval;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Enfasis;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Personalidad;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.PreferenciaIdeaEnum;
import com.knowshare.enums.TipoUsuariosEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * Pruebas de la creación de usuarios de tipo Profesor,
 * Egresado y Estudiante
 * @author miguel
 *
 */
public class UsuarioModBeanTest extends AbstractTest {

	@Autowired
	private UsuarioModFacade usuarioModBean;

	private UsuarioDTO usuarioProfesor;
	private UsuarioDTO usuarioEstudiante;
	private UsuarioDTO usuarioEgresado;

	@Before
	public void setup(){
		List<Cualidad> cualidades = mongoTemplate.find(new Query(
				new Criteria().orOperator(Criteria.where("nombre").is("Cualidad 1"),Criteria.where("nombre").is("Cualidad 2"))), Cualidad.class);
		List<Gusto> gustos = mongoTemplate.findAll(Gusto.class);
		List<Habilidad> habilidades = mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("PROFESIONALES")
				.andOperator(Criteria.where("carrera.nombre").is("Ingeniería de sistemas"))), Habilidad.class);
		List<HabilidadDTO> habilidadesDto = MapEntities.mapHabilidadesToDTOs(habilidades);
		
		usuarioProfesor = crearUsuarioProfesor(MapEntities.mapCualidadesToDTOs(cualidades),habilidadesDto);
		usuarioEgresado = crearUsuarioEgreado(habilidadesDto);
		usuarioEstudiante = crearUsuarioEstudiante(habilidadesDto,gustos);
	}

	@Test
	public void crearUsuarioProfesorTest() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioProfesor);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioProfesor.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().getCantidad().intValue());
		assertEquals(0, usuarioDB.getSeguidores().getCantidad().intValue());
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
	public void crearUsuarioEgresadoTest() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioEgresado);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioEgresado.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().getCantidad().intValue());
		assertEquals(0, usuarioDB.getSeguidores().getCantidad().intValue());
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
	public void crearUsuarioEstudianteTest() throws NoSuchAlgorithmException {
		boolean creacion = usuarioModBean.crearUsuario(usuarioEstudiante);
		assertTrue(creacion);
		
		Usuario usuarioDB = mongoTemplate.findOne(new Query()
				.addCriteria(Criteria.where("username").is(usuarioEstudiante.getUsername())),Usuario.class);
		
		assertEquals(0, usuarioDB.getAmigos().getCantidad().intValue());
		assertEquals(0, usuarioDB.getSeguidores().getCantidad().intValue());
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

	private UsuarioDTO crearUsuarioProfesor(List<CualidadDTO> cualidades, List<HabilidadDTO> habilidadesDto) {
		return new UsuarioDTO().setApellido("Apellido profesor inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 1")
								.setPorcentaje(20d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 2")
								.setPorcentaje(30d)))
				.setCarrera(new CarreraDTO().setNombre("Ingeniería de sistemas")).setCualidades(cualidades)
				.setEmail("Email profesor inserted")
				.setEnfasis(Arrays.asList(
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 1"),
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 4")))
				.setHabilidades(habilidadesDto).setNombre("Nombre profesor inserted").setPassword("PasswordProfesor1")
				.setPersonalidad(new Personalidad().setNombre("INTJ"))
				.setPreferenciaIdea(PreferenciaIdeaEnum.POR_RELEVANCIA).setTipoUsuario(TipoUsuariosEnum.PROFESOR)
				.setUsername("Profesor1-Inserted");
	}
	
	private UsuarioDTO crearUsuarioEgreado(List<HabilidadDTO> habilidadesDto) {
		return new UsuarioDTO().setApellido("Apellido egresado inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 1")
								.setPorcentaje(0d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 3")
								.setPorcentaje(0d)))
				.setCarrera(new CarreraDTO().setNombre("Ingeniería de sistemas"))
				.setEmail("Email egresado inserted")
				.setEnfasis(Arrays.asList(
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 2"),
						new Enfasis().setCarrera("Ingeniería de sistemas").setNombre("Enfasis sistemas 4")))
				.setHabilidades(habilidadesDto).setNombre("Nombre egresado inserted").setPassword("PasswordEgresado1")
				.setPersonalidad(new Personalidad().setNombre("INTP"))
				.setPreferenciaIdea(PreferenciaIdeaEnum.POR_RELEVANCIA).setTipoUsuario(TipoUsuariosEnum.EGRESADO)
				.setUsername("Egresado1-Inserted");
	}
	
	private UsuarioDTO crearUsuarioEstudiante(List<HabilidadDTO> habilidadesDto, List<Gusto> gustos) {
		return new UsuarioDTO().setApellido("Apellido estudiante inserted")
				.setAreasConocimiento(Arrays.asList(
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 2")
								.setPorcentaje(0d),
						new AreaConocimiento().setCarrera("Ingeniería de sistemas").setNombre("AC sistemas 3")
								.setPorcentaje(0d)))
				.setCarrera(new CarreraDTO().setNombre("Ingeniería de sistemas"))
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

}
