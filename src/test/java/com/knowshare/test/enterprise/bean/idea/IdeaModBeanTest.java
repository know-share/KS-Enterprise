/**
 * 
 */
package com.knowshare.test.enterprise.bean.idea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.bean.idea.IdeaModFacade;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.enums.TipoOperacionEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdeaModBeanTest extends AbstractTest {

	@Autowired
	private IdeaModFacade ideaModBean;
	
	private IdeaDTO dto;
	
	@Before
	public void init(){
		dto = new IdeaDTO()
				.setAlcance("alcance")
				.setComentarios(0L)
				.setContenido("idea nueva")
				.setEstado("en espera")
				.setLights(0L)
				.setNumeroEstudiantes(0)
				.setProblematica("problematica")
				.setTipo(TipoIdeaEnum.PC);
	}
	
	@Test
	public void test01CrearIdea() throws Exception{
		Idea ideaNueva = ideaModBean.crearIdea(dto);
		
		assertNotNull(ideaNueva);
		assertNotNull(ideaNueva.getId());
		
		Idea consulted = mongoTemplate.findById(ideaNueva.getId(), Idea.class);
		assertNotNull(consulted);
		
		dto.setId(ideaNueva.getId());
	}
	
	@Test()
	public void test02AgregarOperacion(){
		OperacionIdea operacion = new OperacionIdea()
				.setContenido("Comentario")
				.setFecha(new Date())
				.setTipo(TipoOperacionEnum.COMENTARIO)
				.setUsername("username");
		
		ideaModBean.agregarOperacion(dto, operacion);
		
		Idea idea = mongoTemplate.findById(dto.getId(), Idea.class);
		assertNotNull(idea);
		assertEquals(1, idea.getOperaciones().size());
	}

}
