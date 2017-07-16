/**
 * 
 */
package com.knowshare.test.enterprise.bean.cualidad;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.enterprise.bean.cualidad.CualidadListFacade;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
public class CualidadListBeanTest extends AbstractTest {

	@Autowired
	private CualidadListFacade cualidadListBean;
	
	@Test
	public void test01GetAll(){
		List<CualidadDTO> cualidades = cualidadListBean.getAll();
		
		assertEquals(5, cualidades.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
