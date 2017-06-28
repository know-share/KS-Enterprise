/**
 * 
 */
package com.knowshare.enterprise.bean.cualidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.CualidadDTO;

/**
 * @author miguel
 *
 */
@Component
public class CualidadBean implements CualidadFacade {
	
	@Autowired
	private CualidadListFacade cualidadListBean;

	@Override
	public List<CualidadDTO> getAll() {
		return cualidadListBean.getAll();
	}

}