/**
 * 
 */
package com.knowshare.test.enterprise.bean.idea;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.bean.idea.IdeaModFacade;
import com.knowshare.entities.idea.Idea;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
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
	public void crearIdeaTest() throws Exception{
		Idea ideaNueva = ideaModBean.crearIdea(dto);
		
		assertNotNull(ideaNueva);
		assertNotNull(ideaNueva.getId());
	}

}
