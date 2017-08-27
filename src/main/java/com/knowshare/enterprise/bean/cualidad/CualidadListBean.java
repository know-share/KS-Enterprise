/**
 * 
 */
package com.knowshare.enterprise.bean.cualidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.CualidadDTO;
import com.knowshare.enterprise.repository.perfilusuario.CualidadRepository;
import com.knowshare.enterprise.utils.MapEntities;

/**
 * {@link CualidadListFacade}
 * @author Miguel Montañez
 *
 */
@Component
public class CualidadListBean implements CualidadListFacade {
	
	@Autowired
	private CualidadRepository cualidadRepository;

	@Override
	public List<CualidadDTO> getAll() {
		return MapEntities.mapCualidadesToDTOs(this.cualidadRepository.findAll());
	}
}
