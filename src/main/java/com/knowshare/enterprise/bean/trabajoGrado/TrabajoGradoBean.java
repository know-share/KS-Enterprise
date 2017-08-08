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
 * @author HP
 *
 */
@Component
public class TrabajoGradoBean implements TrabajoGradoFacade {
	
	@Autowired
	private TrabajoGradoRepository tgRepository;

	/* (non-Javadoc)
	 * @see com.knowshare.enterprise.bean.trabajoGrado.TrabajoGradoFacade#findAll()
	 */
	
	@Override
	public List<TrabajoGrado> findAll() {
		return tgRepository.findAll();
	}

}
