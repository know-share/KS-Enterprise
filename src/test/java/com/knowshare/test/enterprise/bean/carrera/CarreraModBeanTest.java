/**
 * 
 */
package com.knowshare.test.enterprise.bean.carrera;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.enterprise.bean.carrera.CarreraFacade;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.test.enterprise.general.AbstractTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Montañez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarreraModBeanTest extends AbstractTest{
	
	@Autowired
	private CarreraFacade carreraModBean;
	
	@Test
	public void test01Create(){
		final CarreraDTO dto = new CarreraDTO()
				.setNombre("Carrera mod Bean")
				.setEnfasis(new ArrayList<>())
				.setFacultad("Facultad carrera mod bean");
		boolean result = carreraModBean.create(dto);
		assertTrue(result);
		
		final List<Carrera> carrera = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("Carrera mod Bean")), Carrera.class);
		
		assertEquals(1,carrera.size());
		
		assertEquals("Carrera mod Bean", carrera.get(0).getNombre());
		assertEquals("Facultad carrera mod bean", carrera.get(0).getFacultad());
		assertNotNull(carrera.get(0).getEnfasis());
		assertNotNull(carrera.get(0).getCarrerasAfines());
		
		result = carreraModBean.create(null);
		assertFalse(result);
	}
	
	@Test
	public void test02Update(){
		final List<Carrera> carreras = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("Carrera mod Bean")), Carrera.class);
		
		final CarreraDTO dto = new CarreraDTO()
				.setId(carreras.get(0).getId())
				.setNombre("Carrera mod Bean updated")
				.setFacultad("Facultad carrera mod bean updated");
		
		boolean result = carreraModBean.update(dto);
		assertTrue(result);
		
		final List<Carrera> carrera = mongoTemplate
				.find(new Query(Criteria.where("_id").is(dto.getId())), Carrera.class);
		
		assertEquals(1,carrera.size());
		
		assertEquals("Carrera mod Bean updated", carrera.get(0).getNombre());
		assertEquals("Facultad carrera mod bean updated", carrera.get(0).getFacultad());
		assertNotNull(carrera.get(0).getEnfasis());
		assertNotNull(carrera.get(0).getCarrerasAfines());
		
		result = carreraModBean.update(new CarreraDTO());
		assertFalse(result);
	}
	
	@Test
	public void test03UpdateEnfasis(){
		final List<Carrera> carreras = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("Carrera mod Bean updated")), Carrera.class);
		final List<String> enfasis = new ArrayList<>();
		enfasis.add("enfasis nuevo 1");
		enfasis.add("enfasis nuevo 2");
		enfasis.add("enfasis nuevo 3");
		final CarreraDTO dto = new CarreraDTO()
				.setId(carreras.get(0).getId())
				.setEnfasis(enfasis);
		
		boolean result = carreraModBean.updateEnfasis(dto);
		assertTrue(result);
		
		final List<Carrera> carrera = mongoTemplate
				.find(new Query(Criteria.where("_id").is(dto.getId())), Carrera.class);
		
		assertEquals(1,carrera.size());
		
		assertNotNull(carrera.get(0).getEnfasis());
		assertEquals(3, carrera.get(0).getEnfasis().size());
		
		result = carreraModBean.update(new CarreraDTO());
		assertFalse(result);
	}
	
	@Test
	public void test04Delete(){
		final List<Carrera> carreras = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("Carrera mod Bean updated")), Carrera.class);
		boolean result = carreraModBean.delete(carreras.get(0).getId());
		assertTrue(result);
		
		final List<Carrera> carrera = mongoTemplate
				.find(new Query(Criteria.where("_id").is(carreras.get(0).getId())), Carrera.class);
		
		assertEquals(0,carrera.size());
		
		result = carreraModBean.delete("");
		assertFalse(result);
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
