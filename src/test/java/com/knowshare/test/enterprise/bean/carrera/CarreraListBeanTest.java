/**
 * 
 */
package com.knowshare.test.enterprise.bean.carrera;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;
import com.knowshare.enterprise.bean.carrera.CarreraFacade;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class CarreraListBeanTest extends AbstractTest{
	
	@Autowired
	private CarreraFacade carreraListBean;
	
	@Test
	public void test01GetAllCarreras(){
		List<CarreraDTO> carreras = carreraListBean.getAllCarreras();
		for (CarreraDTO carreraDTO : carreras) {
			carreraMapTest(carreraDTO);
		}
		
		assertEquals(3, carreras.size());
	}
	
	@Test
	public void test02GetEnfasisAreaConocimiento(){
		EnfasisAreaConocimientoDTO dto = carreraListBean
				.getEnfasisAreaConocimiento("");
		assertNull(dto);
		
		dto = carreraListBean
				.getEnfasisAreaConocimiento("Ingeniería X");
		assertNotNull(dto);
		assertEquals(2, dto.getEnfasis().size());
		assertEquals(2, dto.getAreaConocimiento().size());
		
		dto = carreraListBean
				.getEnfasisAreaConocimiento("Ingeniería de sistemas");
		assertNotNull(dto);
		assertEquals(4, dto.getEnfasis().size());
		assertEquals(3, dto.getAreaConocimiento().size());
	}
	
	private void carreraMapTest(CarreraDTO dto){
		assertNotNull(dto.getFacultad());
		assertNotNull(dto.getNombre());
	}

	@AfterClass
	public static void tearDown(){
	}
}
