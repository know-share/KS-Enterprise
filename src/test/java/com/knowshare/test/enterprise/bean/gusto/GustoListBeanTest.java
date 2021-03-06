/**
 * 
 */
package com.knowshare.test.enterprise.bean.gusto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.gusto.GustoFacade;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class GustoListBeanTest extends AbstractTest {

	@Autowired
	private GustoFacade gustoListBean;
	
	@Test
	public void test01GetAllGustos(){
		List<Gusto> gustos = gustoListBean.getAllGustos();
		assertEquals(4, gustos.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
