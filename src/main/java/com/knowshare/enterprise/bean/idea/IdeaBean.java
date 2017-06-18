/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;
import com.knowshare.enums.TipoOperacionEnum;

/**
 * @author pablo
 *
 */
@Component
public class IdeaBean implements IdeaFacade{
	
	@Autowired
	private IdeaModFacade ideaMod;
	
	@Autowired
	private IdeaListFacade ideaList;
	
	public void agregarOperacion(IdeaDTO dto, OperacionIdea operacion) throws Exception{
		ideaMod.agregarOperacion(dto, operacion);
	}
	
	public void crearIdea(IdeaDTO dto) throws Exception{
		ideaMod.crearIdea(dto);
	}
	
	public List<IdeaDTO> find10(){
		return ideaList.find10();
	}
}
