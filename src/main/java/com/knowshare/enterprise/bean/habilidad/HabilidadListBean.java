package com.knowshare.enterprise.bean.habilidad;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.HabilidadDTO;
import com.knowshare.enterprise.repository.perfilusuario.HabilidadRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.perfilusuario.Habilidad;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

/**
 * {@link HabilidadListFacade}
 * @author Miguel Montañez
 *
 */
@Component
public class HabilidadListBean implements HabilidadListFacade{
	
	@Autowired
	private HabilidadRepository habilidadRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<HabilidadDTO> getHabilidades(String carrera) {
		final List<Habilidad> habilidades = habilidadRepository
				.getHabilidades(carrera);

		final List<HabilidadDTO> habilidadesDto = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			habilidadesDto.add(MapEntities.mapHabilidadToDTO(habilidad));
		}
		return habilidadesDto;
	}

	@Override
	public List<HabilidadDTO> getHabilidadesProfesionales(String carrera) {
		final List<Habilidad> habilidades = habilidadRepository
				.getHabilidadesProfesionales(carrera);

		final List<HabilidadDTO> habilidadesDto = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			habilidadesDto.add(MapEntities.mapHabilidadToDTO(habilidad));
		}
		return habilidadesDto;
	}

	@Override
	public List<ObjectId> buscarPorNombre(String nombre) {
		final Aggregation agg = newAggregation(
					match(TextCriteria.forDefaultLanguage().matching(nombre)),
					project("id")
				);
		AggregationResults<HabilidadDTO> result = mongoTemplate
				.aggregate(agg, Habilidad.class, HabilidadDTO.class);
		final List<ObjectId> ids = new ArrayList<>();
		result.getMappedResults().forEach(r -> ids.add(r.getId()));
		return ids;
	}

	@Override
	public List<HabilidadDTO> getAll() {
		final List<Habilidad> habilidades = habilidadRepository
				.findAll();
		final List<HabilidadDTO> habilidadesDto = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			habilidadesDto.add(MapEntities.mapHabilidadToDTO(habilidad));
		}
		return habilidadesDto;
	}
}
