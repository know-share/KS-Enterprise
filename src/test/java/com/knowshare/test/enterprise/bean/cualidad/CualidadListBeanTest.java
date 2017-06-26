/**
 * 
 */
package com.knowshare.test.enterprise.bean.cualidad;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.cualidad.CualidadListFacade;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
public class CualidadListBeanTest extends AbstractTest {

	@Autowired
	private CualidadListFacade cualidadListBean;
	
	@Test
	public void getAllTest(){
		List<Cualidad> cualidades = cualidadListBean.getAll();
		
		assertEquals(5, cualidades.size());
	}
}
