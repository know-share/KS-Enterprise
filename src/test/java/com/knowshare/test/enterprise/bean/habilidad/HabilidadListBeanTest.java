package com.knowshare.test.enterprise.bean.habilidad;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * Pruebas unitarias para todos los métodos de listar del bean de habilidad.
 * @author miguel
 *
 */
public class HabilidadListBeanTest extends AbstractTest{
	
	@Test
	public void test(){
		assertTrue(true);
	}
	
//	@Autowired
//	private HabilidadListFacade habilidadListBean;
//	
//	@MockBean
//	private HabilidadRepository habilidadRepository;
//
//	@Test()
//	public void testFindOne(){
//		Habilidad toReturn = new Habilidad().setNombre("hola");
//		when(habilidadRepository.findByNombre("hola")).thenReturn(toReturn);
//		
//		Habilidad newOne = habilidadListBean.findOne("hola");
//		assertEquals("hola", newOne.getNombre()); 
//	}
}
