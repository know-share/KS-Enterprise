/**
 * 
 */
package com.knowshare.test.enterprise.bean.habilidad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.enterprise.bean.habilidad.HabilidadFacade;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.enums.TipoHabilidadEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HabilidadModBeanTest extends AbstractTest{

	@Autowired
	private HabilidadFacade habilidadModBean;
	
	private String nameHabilidad = "Habilidad creada test";
	private String updateNameHabilidad = nameHabilidad+" updated";
	
	@Test
	public void test01Create(){
		final HabilidadDTO dto = new HabilidadDTO()
				.setCarrera("idCarreraSistemas")
				.setNombre(nameHabilidad)
				.setTipo(TipoHabilidadEnum.PERSONALES)
				.setIdCarrera("idCarreraSistemas");
		boolean result = habilidadModBean.create(null);
		assertFalse(result);
		
		result = habilidadModBean.create(dto);
		assertTrue(result);
		
		final Habilidad habilidad = mongoTemplate
				.findOne(new Query(Criteria.where("nombre").is(nameHabilidad)), 
						Habilidad.class);
		assertNotNull(habilidad);
		assertEquals("idCarreraSistemas", habilidad.getCarrera().getId());
	}
	
	@Test
	public void test02Update(){
		Habilidad habilidad = mongoTemplate
				.findOne(new Query(Criteria.where("nombre").is(nameHabilidad)), 
						Habilidad.class);
		habilidad.setNombre(updateNameHabilidad);
		boolean result = habilidadModBean.update(new HabilidadDTO());
		assertFalse(result);
		
		result = habilidadModBean.update(MapEntities.mapHabilidadToDTO(habilidad));
		assertTrue(result);
		
		habilidad = mongoTemplate
				.findOne(new Query(Criteria.where("nombre").is(updateNameHabilidad)), 
						Habilidad.class);
		assertNotNull(habilidad);
	}
	
	@Test
	public void test03Delete(){
		Habilidad habilidad = mongoTemplate
				.findOne(new Query(Criteria.where("nombre").is(updateNameHabilidad)), 
						Habilidad.class);
		boolean result = habilidadModBean.delete(new ObjectId());
		assertFalse(result);
		
		result = habilidadModBean.delete(habilidad.getId());
		assertTrue(result);
		
		habilidad = mongoTemplate
				.findOne(new Query(Criteria.where("nombre").is(updateNameHabilidad)), 
						Habilidad.class);
		
		assertNull(habilidad);
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
