/**
 * 
 */
package com.knowshare.enterprise.bean.gusto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.entities.perfilusuario.Gusto;

/**
 * @author Miguel Montañez
 *
 */
@Component
public class GustoBean implements GustoFacade{
	
	@Autowired
	private GustoListFacade gustoListBean;

	@Override
	public List<Gusto> getAllGustos() {
		return this.gustoListBean.getAllGustos();
	}
	
	
}
