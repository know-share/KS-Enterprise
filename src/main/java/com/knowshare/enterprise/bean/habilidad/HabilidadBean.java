/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author miguel
 *
 */
@Component
public class HabilidadBean implements HabilidadFacade{
	
	@Autowired
	private HabilidadModFacade habilidadModBean;
	
	@Autowired
	private HabilidadModFacade habilidadListBean;

	@Override
	public void createHabilidad(String nombre) {
		habilidadModBean.createHabilidad(nombre);
	}
}
