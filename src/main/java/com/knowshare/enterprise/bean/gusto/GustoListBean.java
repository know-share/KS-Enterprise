/**
 * 
 */
package com.knowshare.enterprise.bean.gusto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.perfilusuario.GustoRepository;
import com.knowshare.entities.perfilusuario.Gusto;

/**
 * @author Miguel Montañez
 *
 */
@Component
public class GustoListBean implements GustoListFacade{
	
	@Autowired
	private GustoRepository gustoRepository;

	@Override
	public List<Gusto> getAllGustos() {
		return this.gustoRepository.findAll();
	}
}
