/**
 * 
 */
package com.knowshare.enterprise.bean.idea;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.repository.idea.IdeaRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.mongodb.DBCursor;



/**
 * @author pablo
 *
 */
@Component
public class IdeaListBean implements IdeaListFacade{
	
	@Autowired
	IdeaRepository ideaRep;
	
	public List<IdeaDTO> find10(){
		List<IdeaDTO> ret = new ArrayList<>();
		List<Idea> lista = ideaRep.find10();
		for (Idea idea : lista) {
			ret.add(MapEntities.mapIdeaToDTO(idea));
		}
		return ret;
	}
	
	

}
