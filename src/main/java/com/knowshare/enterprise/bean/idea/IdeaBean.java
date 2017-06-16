/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;

/**
 * @author HP
 *
 */
@Component
public class IdeaBean implements IdeaFacade{
	
	@Autowired
	private IdeaModFacade ideaMod;
	
	
	
	public void crearIdea(String alcance,String contenido,String estado,Date fechaCreacion,ArrayList<Idea> ideas,String lugarEscritura,
			int numeroEstudiantes, String problematica, Tag tag,TipoIdeaEnum tipo,Usuario usuario){
		ideaMod.crearIdea(alcance, contenido, estado, fechaCreacion, ideas, lugarEscritura, numeroEstudiantes, problematica, tag, tipo, usuario);
	}
}
