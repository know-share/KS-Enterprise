/**
 * 
 */
package com.knowshare.test.enterprise.bean.idea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.bean.idea.IdeaFacade;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.enums.TipoOperacionEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdeaModBeanTest extends AbstractTest {

	@Autowired
	private IdeaFacade ideaModBean;
	
	private IdeaDTO dto;
	
	private Idea idea;
	
	@Before
	public void setup(){
		Usuario usuario = (Usuario) mongoTemplate.findOne(new Query(
				Criteria.where("username").is("MinMiguelM")), Usuario.class);
		
		dto = new IdeaDTO()
				.setAlcance("alcance")
				.setComentarios(0L)
				.setContenido("idea nueva")
				.setEstado("en espera")
				.setLights(0L)
				.setNumeroEstudiantes(0)
				.setProblematica("problematica")
				.setUsuario("MinMiguelM")
				.setTipo(TipoIdeaEnum.PC);
		
		idea = new Idea()
				.setAlcance("alcance")
				.setContenido("idea para continuar")
				.setEstado("no tg")
				.setFechaCreacion(new Date())
				.setLights(0L)
				.setUsuario(usuario)
				.setTipo(TipoIdeaEnum.PC);
		
		mongoTemplate.insert(idea);
	}
	
	@Test
	public void test01CrearIdea() throws Exception{
		
		IdeaDTO ideaNueva = ideaModBean.crearIdea(dto);
		
		assertNotNull(ideaNueva);
		assertNotNull(ideaNueva.getId());
		
		Idea consulted = mongoTemplate.findById(ideaNueva.getId(), Idea.class);
		assertNotNull(consulted);
	}
	
	@Test()
	public void test02AgregarOperacion(){
		OperacionIdea operacion = new OperacionIdea()
				.setContenido("Comentario")
				.setFecha(new Date())
				.setTipo(TipoOperacionEnum.COMENTARIO)
				.setUsername("username");
		
		ideaModBean.agregarOperacion(MapEntities.mapIdeaToDTO(idea), operacion);
		
		Idea ideaManaged = mongoTemplate.findById(idea.getId(), Idea.class);
		assertNotNull(ideaManaged);
		assertEquals(1, ideaManaged.getOperaciones().size());
	}
	
	@Test
	public void test03Compartir(){
		final IdeaDTO dto = ideaModBean
				.compartir(MapEntities.mapIdeaToDTO(idea), "Pablo.gaitan");
		assertNotNull(dto);
		
		Idea ideaManaged = mongoTemplate.findById(dto.getId(), Idea.class);
		assertNotNull(ideaManaged);
		assertTrue(ideaManaged.isCompartida());
		assertEquals("pablo.gaitan",ideaManaged.getUsuario().getUsername().toLowerCase());
		assertNotEquals(idea.getId(), ideaManaged.getId());
	}

}
