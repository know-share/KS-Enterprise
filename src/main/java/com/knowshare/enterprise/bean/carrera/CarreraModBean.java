/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.enterprise.repository.academia.CarreraRepository;
import com.knowshare.entities.academia.Carrera;

/**
 * @author Felipe Bautista
 *
 */
@Component
public class CarreraModBean implements CarreraModFacade {
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean update(CarreraDTO carrera) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(carrera.getId()));
		update.set("nombre", carrera.getNombre())
			.set("facultad",carrera.getFacultad());
		return mongoTemplate.updateFirst(query, update, Carrera.class).getN() > 0;
	}

	
}
