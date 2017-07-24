/**
 * 
 */
package com.knowshare.test.enterprise.bean.personalidad;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.personalidad.PersonalidadListFacade;
import com.knowshare.entities.perfilusuario.Personalidad;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
public class PersonalidadListBeanTest extends AbstractTest{
	
	@Autowired
	private PersonalidadListFacade personalidadListBean;
	
	@Test
	public void test01GetAllPersonalidades(){
		List<Personalidad> personalidades = personalidadListBean.getAllPersonalidades();
		assertEquals(16, personalidades.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
