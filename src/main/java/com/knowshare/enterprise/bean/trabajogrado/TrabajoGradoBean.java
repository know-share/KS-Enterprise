/**
 * 
 */
package com.knowshare.enterprise.bean.trabajogrado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Miguel Montañez
 *
 */
@Component
public class TrabajoGradoBean implements TrabajoGradoFacade{
	
	@Autowired
	private TrabajoGradoListFacade tgListBean;

	@Override
	public List<TrabajoGrado> findAll() {
		return tgListBean.findAll();
	}

}
