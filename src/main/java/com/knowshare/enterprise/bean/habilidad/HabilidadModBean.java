/**
 * 
 */
package com.knowshare.enterprise.bean.habilidad;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.enterprise.repository.perfilusuario.HabilidadRepository;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * {@link HabilidadModFacade}
 * @author Miguel Montañez
 *
 */
@Component
public class HabilidadModBean implements HabilidadModFacade{
	
	@Autowired
	private HabilidadRepository habilidadRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean update(HabilidadDTO habilidad) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(habilidad.getId()));
		update.set("nombre", habilidad.getNombre());
		return mongoTemplate.updateFirst(query, update, Habilidad.class).getN() > 0;
	}

	@Override
	public boolean delete(ObjectId id) {
		return habilidadRepository.removeById(id)==1;
	}

	
	@Override
	public boolean create(HabilidadDTO habilidad) {
		Habilidad habilidadOriginal = new Habilidad();
		if(habilidad!=null) {
			habilidadOriginal.setNombre(habilidad.getNombre());
			habilidadOriginal.setTipo(habilidad.getTipo());
			habilidadOriginal.setCarrera( new Carrera().setId(habilidad.getIdCarrera()));
			if(habilidadRepository.insert(habilidadOriginal)!=null) 
				return true;
		}
		return false;
	}
}
