/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;

/**
 * @author miguel
 *
 */
@Component
public class CarreraBean implements CarreraFacade{
	
	@Autowired
	private CarreraListFacade carreraListBean;

	@Override
	public List<CarreraDTO> getAllCarreras() {
		return carreraListBean.getAllCarreras();
	}

}
