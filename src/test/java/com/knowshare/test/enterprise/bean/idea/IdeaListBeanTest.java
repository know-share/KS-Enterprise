/**
 * 
 */
package com.knowshare.test.enterprise.bean.idea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.bean.idea.IdeaFacade;
import com.knowshare.enterprise.bean.idea.IdeaListFacade;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.enums.TipoOperacionEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class IdeaListBeanTest extends AbstractTest{

	@Autowired
	private IdeaFacade ideaBean;
	
	@Autowired
	private IdeaListFacade ideaListBean;
	
	@Test
	public void test01FindByUsuario(){
		List<IdeaDTO> ideas = ideaBean.findByUsuario("MinMiguelM","Felipe-Bautista",0,0L).getContent();
		assertNotNull(ideas);
		assertEquals(0, ideas.size());
		
		ideas = ideaBean.findByUsuario("Felipe-Bautista","pablo.gaitan",0,1505618696840L).getContent();
		assertNotNull(ideas);
		assertEquals(3, ideas.size());
		assertEquals(Long.valueOf(2),ideas.get(0).getLights());
		
		ideas = ideaBean.findByUsuario("Felipe-Bautista","MinMiguelM",0,1505618696840L).getContent();
		assertNotNull(ideas);
		assertEquals(2, ideas.size());
		for(IdeaDTO idea:ideas){
			assertNotNull(idea.getFechaCreacion());
			assertEquals(Long.valueOf(0),idea.getComentarios());
			assertEquals(Long.valueOf(0),idea.getLights());
			assertEquals(false,idea.isCompartida());
		}
	}
	
	@Test
	public void test02IsLight(){
		Idea idea = mongoTemplate.findById("NU01", Idea.class);
		OperacionIdea operacion = ideaListBean.isLight(idea, "pablo.gaitan");
		assertNull(operacion);
		
		idea = mongoTemplate.findById("NU03", Idea.class);
		operacion = ideaListBean.isLight(idea, "pablo.gaitan");
		assertNull(operacion);
		
		operacion = ideaListBean.isLight(idea, "Felipe-Bautista");
		assertNotNull(operacion);
		
		operacion = ideaListBean.isLight(idea, "MinMiguelM");
		assertNotNull(operacion);
	}
	
	@Test
	public void test03FindById(){
		IdeaDTO idea = ideaBean.findById("NU01", "pablo.gaitan");
		assertNotNull(idea);
		assertFalse(idea.isIsLight());
		
		idea = ideaBean.findById("NU03", "pablo.gaitan");
		assertNotNull(idea);
		assertFalse(idea.isIsLight());
		
		idea = ideaBean.findById("NU03", "MinMiguelM");
		assertNotNull(idea);
		assertTrue(idea.isIsLight());
		
		idea = ideaBean.findById("NOEXIST", "MinMiguelM");
		assertNull(idea);
	}
	
	@Test
	public void test04FindByUsuarioProyecto(){
		List<IdeaDTO> ideas = ideaBean.findByUsuarioProyecto("pablo.gaitan");
		assertNotNull(ideas);
		assertEquals(1, ideas.size());
		
		for(IdeaDTO idea:ideas){
			assertNotEquals(TipoIdeaEnum.PR, idea.getTipo());
		}
		
		ideas = ideaBean.findByUsuarioProyecto("MinMiguelM");
		assertNotNull(ideas);
		assertEquals(2, ideas.size());
		
		for(IdeaDTO idea:ideas){
			assertNotEquals(TipoIdeaEnum.PR, idea.getTipo());
		}
	}
	
	@Test
	public void test05FindOperaciones(){
		List<OperacionIdea> operaciones =  ideaBean.findOperaciones("NU03","light");
		assertNotNull(operaciones);
		assertEquals(2, operaciones.size());
		for(OperacionIdea operacion: operaciones)
			assertEquals(TipoOperacionEnum.LIGHT, operacion.getTipo());
		
		operaciones =  ideaBean.findOperaciones("NU03","comentario");
		assertNotNull(operaciones);
		assertEquals(1, operaciones.size());
		for(OperacionIdea operacion: operaciones)
			assertEquals(TipoOperacionEnum.COMENTARIO, operacion.getTipo());
	}
	
	@AfterClass
	public static void tearDown(){
	}
	
}
