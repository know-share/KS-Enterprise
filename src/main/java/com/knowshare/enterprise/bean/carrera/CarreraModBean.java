/**
 * 
 */
package com.knowshare.enterprise.bean.carrera;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.knowshare.dto.academia.CarreraDTO;
import com.knowshare.enterprise.repository.academia.CarreraRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.perfilusuario.Habilidad;

/**
 * {@link CarreraModFacade}
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
			.set("facultad",carrera.getFacultad())
			.set("enfasis",carrera.getEnfasis());
		return mongoTemplate.updateFirst(query, update, Carrera.class).getN() > 0;
	}

	@Override
	public boolean delete(String carrera){
		mongoTemplate.remove(new Query(Criteria.where("carrera.$id").is(carrera)), 
				Habilidad.class);
		return carreraRepository.removeById(carrera)==1;
	}

	@Override
	public boolean create(CarreraDTO carrera) {
		Carrera carreraOriginal = MapEntities.mapDtoToCarrera(carrera);
		if(carreraOriginal!=null) {
			carreraOriginal.setCarrerasAfines(new ArrayList<Carrera>());
			carreraOriginal.setEnfasis(new ArrayList<String>());	
			if(carreraRepository.insert(carreraOriginal)!=null) // usar save para los mas sencillos
				return true;
		}
		return false;
	}
	
	
}
