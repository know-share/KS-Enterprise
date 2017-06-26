/**
 * 
 */
package com.knowshare.test.enterprise.bean.carrera;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.dto.academia.EnfasisAreaConocimientoDTO;
import com.knowshare.enterprise.bean.carrera.CarreraListFacade;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author miguel
 *
 */
public class CarreraListBeanTest extends AbstractTest{
	
	@Autowired
	private CarreraListFacade carreraListBean;
	
	@Test
	public void getAllCarrerasTest(){
		List<CarreraDTO> carreras = carreraListBean.getAllCarreras();
		for (CarreraDTO carreraDTO : carreras) {
			carreraMapTest(carreraDTO);
		}
		
		assertEquals(2, carreras.size());
	}
	
	@Test
	public void getEnfasisAreaConocimientoTest(){
		EnfasisAreaConocimientoDTO dto = carreraListBean
				.getEnfasisAreaConocimiento("Ingeniería de sistemas");
		assertEquals(4, dto.getEnfasis().size());
		assertEquals(3, dto.getAreaConocimiento().size());
	}
	
	private void carreraMapTest(CarreraDTO dto){
		assertNotNull(dto.getFacultad());
		assertNotNull(dto.getNombre());
	}

}
