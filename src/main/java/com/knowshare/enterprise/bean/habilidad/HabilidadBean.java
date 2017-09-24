/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.HabilidadDTO;

/**
 * {@link HabilidadFacade}
 * @author Miguel Montañez
 *
 */
@Component
public class HabilidadBean implements HabilidadFacade{
	
	@Autowired
	private HabilidadModFacade habilidadModBean;
	
	@Autowired
	private HabilidadListFacade habilidadListBean;

	@Override
	public List<HabilidadDTO> getHabilidades(String carrera) {
		return habilidadListBean.getHabilidades(carrera);
	}

	@Override
	public List<HabilidadDTO> getHabilidadesProfesionales(String carrera) {
		return habilidadListBean.getHabilidadesProfesionales(carrera);
	}

	@Override
	public List<ObjectId> buscarPorNombre(String nombre) {
		return this.habilidadListBean.buscarPorNombre(nombre);
	}

	@Override
	public List<HabilidadDTO> getAll() {
		return habilidadListBean.getAll();
	}

	@Override
	public boolean update(HabilidadDTO habilidad) {
		return habilidadModBean.update(habilidad);
	}

	@Override
	public boolean delete(ObjectId id) {
		return habilidadModBean.delete(id);
	}

	@Override
	public boolean create(HabilidadDTO habilidad) {
		return habilidadModBean.create(habilidad);
	}
}
