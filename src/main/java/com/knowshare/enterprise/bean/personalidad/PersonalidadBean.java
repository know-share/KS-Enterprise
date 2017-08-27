/**
 * 
 */
package com.knowshare.enterprise.bean.personalidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.entities.perfilusuario.Personalidad;

/**
 * {@link PersonalidadFacade}
 * @author Miguel Montañez
 *
 */
@Component
public class PersonalidadBean implements PersonalidadFacade{
	
	@Autowired
	private PersonalidadListFacade personalidadListBean;

	@Override
	public List<Personalidad> getAllPersonalidades() {
		return personalidadListBean.getAllPersonalidades();
	}
}
