package com.knowshare.test.bean.habilidad;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.knowshare.enterprise.bean.habilidad.HabilidadListFacade;
import com.knowshare.enterprise.repository.habilidad.HabilidadRepository;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.test.general.AbstractTest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias para todos los métodos de listar del bean de habilidad.
 * @author miguel
 *
 */
@Import({HabilidadRepository.class})
public class HabilidadListBeanTest extends AbstractTest{
	
	@Autowired
	private HabilidadListFacade habilidadListBean;
	
	@MockBean
	private HabilidadRepository habilidadRepository;

	@Test()
	public void testFindOne(){
		Habilidad toReturn = new Habilidad().setNombre("hola");
		when(habilidadRepository.findOne(anyString())).thenReturn(toReturn);
		
		Habilidad newOne = habilidadListBean.findOne("");
		assertEquals("hola", newOne.getNombre());
	}
}
