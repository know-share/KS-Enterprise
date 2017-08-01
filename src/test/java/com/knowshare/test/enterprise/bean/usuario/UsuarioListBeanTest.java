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

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.bean.usuario.UsuarioListFacade;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class UsuarioListBeanTest extends AbstractTest{

	@Autowired
	private UsuarioListFacade usuarioListBean;
	
	@Test
	public void test01IsUsernameTaken(){
		boolean res = usuarioListBean.isUsernameTaken("username not exist");
		assertEquals(false, res);
		
		res = usuarioListBean.isUsernameTaken("minMiguelm");
		assertEquals(true, res);
		
		res = usuarioListBean.isUsernameTaken("MINMIGUELM");
		assertEquals(true, res);
	}
	
	@Test
	public void test02Login(){
		Usuario usu = usuarioListBean.login("MINMIGUELM","INCROO");
		assertNull(usu);
		
		usu = usuarioListBean.login("MINMIGUELM","Asdf1234$");
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
		final UsuarioDTO usuario = usuarioListBean.getUsuario("pablo.Gaitan");
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
		List<UsuarioDTO> noConnections = usuarioListBean.getMyNoConnections("MinMiguelM");
		assertNotNull(noConnections);
		assertEquals(1, noConnections.size());
		
		noConnections = usuarioListBean.getMyNoConnections("pablo.gaitan");
		assertNotNull(noConnections);
		assertEquals(2, noConnections.size());
		
		noConnections = usuarioListBean.getMyNoConnections("Felipe-Bautista");
		assertNotNull(noConnections);
		assertEquals(1, noConnections.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
