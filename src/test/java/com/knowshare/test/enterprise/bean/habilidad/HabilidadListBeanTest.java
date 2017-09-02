package com.knowshare.test.enterprise.bean.habilidad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.enterprise.bean.habilidad.HabilidadFacade;
import com.knowshare.enums.TipoHabilidadEnum;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * Pruebas unitarias para todos los métodos de listar del bean de habilidad.
 * @author Miguel Montañez
 *
 */
public class HabilidadListBeanTest extends AbstractTest{
	
	@Autowired
	private HabilidadFacade habilidadListBean;

	@Test
	public void test01GetHabilidades(){
		List<HabilidadDTO> habilidades = habilidadListBean
				.getHabilidades("idCarreraSistemas");
		
		List<HabilidadDTO> habilidadesPersonales = new ArrayList<>();
		List<HabilidadDTO> habilidadesProfesionales = new ArrayList<>();
		for (HabilidadDTO habilidadDTO : habilidades) {
			habilidadMapTest(habilidadDTO);
			if(habilidadDTO.getTipo().equals(TipoHabilidadEnum.PERSONALES))
				habilidadesPersonales.add(habilidadDTO);
			else
				habilidadesProfesionales.add(habilidadDTO);
		}
		
		assertTrue(habilidadesPersonales.size() == 3);
		assertTrue(habilidadesProfesionales.size() == 2);
	}
	
	@Test
	public void test02GetHabilidadesProfesionales(){
		List<HabilidadDTO> habilidades = habilidadListBean
				.getHabilidadesProfesionales("idCarreraCivil");
		
		for (HabilidadDTO habilidadDTO : habilidades) {
			habilidadMapTest(habilidadDTO);
		}
		
		assertEquals(2, habilidades.size());
	}
	
	@Test
	public void test03BuscarPorNombre(){
		List<ObjectId> ids = habilidadListBean.buscarPorNombre("profesional");
		assertEquals(4,ids.size());
		
		ids = habilidadListBean.buscarPorNombre("personal");
		assertEquals(3,ids.size());
		
		ids = habilidadListBean.buscarPorNombre("sistemas");
		assertEquals(2,ids.size());
	}
	
	@Test
	public void test04GetAll(){
		List<HabilidadDTO> habilidades = habilidadListBean.getAll();
		assertEquals(7, habilidades.size());
	}
	
	private void habilidadMapTest(HabilidadDTO dto){
		assertNotNull(dto.getId());
		assertNotNull(dto.getNombre());
		assertNotNull(dto.getTipo());
		if(dto.getTipo().equals(TipoHabilidadEnum.PROFESIONALES))
			assertNotNull(dto.getCarrera());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
