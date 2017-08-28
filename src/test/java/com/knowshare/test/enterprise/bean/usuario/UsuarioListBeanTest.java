/**
 * 
 */
package com.knowshare.test.enterprise.bean.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.bean.usuario.UsuarioFacade;
import com.knowshare.enterprise.bean.usuario.UsuarioListFacade;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class UsuarioListBeanTest extends AbstractTest{

	@Autowired
	private UsuarioFacade usuarioBean;
	
	@Autowired
	private UsuarioListFacade usuarioListBean;
	
	@Test
	public void test01IsUsernameTaken(){
		boolean res = usuarioBean.isUsernameTaken("username not exist");
		assertEquals(false, res);
		
		res = usuarioBean.isUsernameTaken("minMiguelm");
		assertEquals(true, res);
		
		res = usuarioBean.isUsernameTaken("MINMIGUELM");
		assertEquals(true, res);
	}
	
	@Test
	public void test02Login(){
		Usuario usu = usuarioBean.login("MINMIGUELM","INCROO");
		assertNull(usu);
		
		usu = usuarioBean.login("MINMIGUELM","Asdf1234$");
		assertNotNull(usu);
		assertEquals("MinMiguelM", usu.getUsername());
	}
	
	@Test
	public void test03EsSeguidor(){
		final Usuario usuarioMiguel = ((List<Usuario>) mongoTemplate
				.find(new Query(Criteria.where("username").is("MinMiguelM")), Usuario.class)).get(0);
		final Usuario usuarioPablo = ((List<Usuario>) mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		final Usuario usuarioFelipe = ((List<Usuario>) mongoTemplate
				.find(new Query(Criteria.where("username").is("Felipe-Bautista")), Usuario.class)).get(0);
		
		boolean res = usuarioListBean.esSeguidor(usuarioMiguel,usuarioPablo);
		assertFalse(res);
		
		res = usuarioListBean.esSeguidor(usuarioMiguel,usuarioFelipe);
		assertTrue(res);
		
		// Usuario no existente
		res = usuarioListBean.esSeguidor(usuarioMiguel,null);
		assertFalse(res);
	}
	
	@Test
	public void test04EstaSolicitud(){
		final Usuario usuarioPablo = ((List<Usuario>) mongoTemplate
				.find(new Query(Criteria.where("username").is("pablo.gaitan")), Usuario.class)).get(0);
		final Usuario usuarioFelipe = ((List<Usuario>) mongoTemplate
				.find(new Query(Criteria.where("username").is("Felipe-Bautista")), Usuario.class)).get(0);
		//Usuario no existe
		boolean res = usuarioListBean.estaSolicitud(usuarioPablo,null);
		assertFalse(res);
		
		res = usuarioListBean.estaSolicitud(usuarioPablo,usuarioFelipe);
		assertFalse(res);
		
		res = usuarioListBean.estaSolicitud(usuarioFelipe,usuarioPablo);
		assertTrue(res);
	}
	
	@Test
	public void test05GetUsuario(){
		final UsuarioDTO usuario = usuarioBean.getUsuario("pablo.Gaitan");
		assertNotNull(usuario);
		
		assertNotNull(usuario.getAmigos());
		assertNotNull(usuario.getSeguidores());
		assertNotNull(usuario.getPersonalidad());
		assertNotNull(usuario.getCarrera());
		//assertNotNull(usuario.getInsignias()); // Aún no hay insigneas
		
		assertNotNull(usuario.getSolicitudesAmistad());
		assertEquals(1,usuario.getSolicitudesAmistad().size());
		
		assertNotNull(usuario.getHabilidades());
		assertEquals(2, usuario.getHabilidades().size());
		
		assertNull(usuario.getPassword());
		assertNull(usuario.getSegundaCarrera());
		
		assertNotNull(usuario.getEnfasis());
		assertEquals(2,usuario.getEnfasis().size());
		
		assertNotNull(usuario.getAreasConocimiento());
		assertEquals(1,usuario.getAreasConocimiento().size());
		
		assertNotNull(usuario.getGustos());
		assertEquals(2, usuario.getGustos().size());
	}
	
	@Test
	public void test06GetMyNoConnections(){
		List<UsuarioDTO> noConnections = usuarioBean.getMyNoConnections("MinMiguelM");
		assertNotNull(noConnections);
		assertEquals(1, noConnections.size());
		
		noConnections = usuarioBean.getMyNoConnections("pablo.gaitan");
		assertNotNull(noConnections);
		assertEquals(2, noConnections.size());
		
		noConnections = usuarioBean.getMyNoConnections("Felipe-Bautista");
		assertNotNull(noConnections);
		assertEquals(1, noConnections.size());
	}
	
	@Test
	public void test07IsCorreoTaken(){
		assertTrue(usuarioBean.isCorreoTaken("miguel@mail.com"));
		assertFalse(usuarioBean.isCorreoTaken("miguelm@mail.com"));
	}
	
	@Test
	public void test08BuscarPorNombre(){
		final UsuarioDTO dto = new UsuarioDTO().setUsername("MinMiguelM");
		List<UsuarioDTO> usuarios = usuarioBean.buscarPorNombre(dto, "paBLo gaitan");
		assertTrue(usuarios.size() == 1);
		
		usuarios = usuarioBean.buscarPorNombre(dto, "paBLo gaitan miguel felipe");
		assertTrue(usuarios.size() == 2);
		
		usuarios = usuarioBean.buscarPorNombre(dto, "bautista miguel felipe");
		assertTrue(usuarios.size() == 1);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test09BuscarPorHabilidad(){
		List<Map> result = usuarioBean.buscarPorHabilidad("habilidad");
		assertEquals(3,result.size());
		
		result = usuarioBean.buscarPorHabilidad("civil");
		assertEquals(1,result.size());
		
		result = usuarioBean.buscarPorHabilidad("sistemas");
		assertEquals(2,result.size());
		
		result = usuarioBean.buscarPorHabilidad("sistemas electronica habilidad");
		assertEquals(3,result.size());
		
		result = usuarioBean.buscarPorHabilidad("electronica industrial");
		assertEquals(0,result.size());
		
		result = usuarioBean.buscarPorHabilidad("electronica personal");
		assertEquals(3,result.size());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test10BuscarPorAreaConocimiento(){
		List<Map> result = usuarioBean.buscarPorAreaConocimiento("1");
		assertEquals(3,result.size());
		
		result = usuarioBean.buscarPorAreaConocimiento("sistemas civil");
		assertEquals(3,result.size());
		
		result = usuarioBean.buscarPorAreaConocimiento("sistemas 3");
		assertEquals(3,result.size());
		
		result = usuarioBean.buscarPorAreaConocimiento("sistemas 4");
		assertEquals(2,result.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
