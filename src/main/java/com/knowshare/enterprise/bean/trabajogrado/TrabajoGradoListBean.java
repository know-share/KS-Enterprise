/**
 * 
 */
package com.knowshare.enterprise.bean.trabajoGrado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.academia.TrabajoGradoRepository;
import com.knowshare.entities.academia.TrabajoGrado;

/**
 * @author Pablo Gaitan
 *
 */
@Component
public class TrabajoGradoListBean implements TrabajoGradoListFacade {
	
	@Autowired
	private TrabajoGradoRepository tgRepository;

	@Override
	public List<TrabajoGrado> findAll() {
		return tgRepository.findAll();
	}

}
