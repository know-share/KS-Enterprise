/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.knowshare.dto.idea.IdeaDTO;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;

/**
 * @author pablo
 *
 */
public interface IdeaModFacade {
	
	void crearIdea(IdeaDTO dto) throws Exception;
	public void agregarOperacion(IdeaDTO dto, OperacionIdea operacion) throws Exception;

}
