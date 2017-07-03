/**
 * 
 */
package com.knowshare.test.enterprise.bean.gusto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.gusto.GustoListFacade;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
public class GustoListBeanTest extends AbstractTest {

	@Autowired
	private GustoListFacade gustoListBean;
	
	@Test
	public void test01GetAllGustos(){
		List<Gusto> gustos = gustoListBean.getAllGustos();
		assertEquals(4, gustos.size());
	}
}
