/**
 * 
 */
package com.knowshare.test.enterprise.bean.trabajogrado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.trabajogrado.TrabajoGradoListFacade;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class TrabajoGradoListBeanTest extends AbstractTest{
	
	@Autowired
	private TrabajoGradoListFacade tgListBean;
	
	@Test
	public void test01FindAll(){
		final List<TrabajoGrado> tgs = tgListBean.findAll();
		assertNotNull(tgs);
		assertEquals(10, tgs.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
