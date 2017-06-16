/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;

import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;

/**
 * @author HP
 *
 */
public interface IdeaFacade {
	void crearIdea(String alcance,String contenido,String estado,Date fechaCreacion,ArrayList<Idea> ideas,String lugarEscritura,
			int numeroEstudiantes, String problematica, Tag tag,TipoIdeaEnum tipo,Usuario usuario);
}
