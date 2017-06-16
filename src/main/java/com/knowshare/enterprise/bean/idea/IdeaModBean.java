/**
 * 
 */
package com.knowshare.enterprise.bean.idea;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.habilidad.IdeaRepository;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.OperacionIdea;
import com.knowshare.entities.idea.Tag;
import com.knowshare.entities.perfilusuario.Usuario;
import com.knowshare.enums.TipoIdeaEnum;

/**
 * @author HP
 *
 */
@Component
public class IdeaModBean implements IdeaModFacade{
	
	@Autowired
	private IdeaRepository ideaRep;
	
	public void crearIdea(String alcance,String contenido,String estado,Date fechaCreacion,ArrayList<Idea> ideas,String lugarEscritura,
			int numeroEstudiantes, String problematica, Tag tag,TipoIdeaEnum tipo,Usuario usuario){
		Idea idea = new Idea();
		idea.setAlcance(alcance);
		idea.setComentarios(new Long(0));
		idea.setEstado(estado);
		idea.setFechaCreacion(fechaCreacion);
		idea.setIdeasProyecto(ideas);
		idea.setLights(new Long(0));
		idea.setLugarEscritura(lugarEscritura);
		idea.setNumeroEstudiantes(numeroEstudiantes);
		idea.setProblematica(problematica);
		idea.setTags(tag);
		idea.setTipo(tipo);
		idea.setUsuario(usuario);
		idea.setLights(new Long(0));
		idea.setOperaciones(new OperacionIdea());
		
		ideaRep.insert(idea);
	}
}
