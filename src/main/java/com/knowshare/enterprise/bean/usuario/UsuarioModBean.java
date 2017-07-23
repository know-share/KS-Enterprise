/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.repository.academia.TrabajoGradoRepository;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.entities.academia.FormacionAcademica;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.perfilusuario.InfoUsuario;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * {@link UsuarioModFacade}
 * @author miguel
 *
 */
@Component
public class UsuarioModBean implements UsuarioModFacade {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TrabajoGradoRepository trabajoGradoRepository;
	
	@Autowired
	private UsuarioListFacade usuarioListBean;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean crearUsuario(UsuarioDTO usuario) {
		logger.debug("::::: Start method crearUsuario(UsuarioDTO) in UsuarioModBean :::::");
		Usuario nuevoUsuario;
		try {
			nuevoUsuario = MapEntities.mapDtoToUsuario(usuario);
			usuarioRepository.insert(nuevoUsuario);
			if (nuevoUsuario.getId() != null)
				return true;
		} catch (NoSuchAlgorithmException e) {
			logger.debug("::::: Error with algorithm hash :::::");
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean seguir(String usernameSol, String usernameObj){
		final Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		final Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(!usuarioListBean.esSeguidor(solicitante, objetivo)){
			final InfoUsuario sol = new InfoUsuario()
					.setUsername(solicitante.getUsername())
					.setNombre(solicitante.getNombre() +" "+ solicitante.getApellido());
			final InfoUsuario obj = new InfoUsuario()
					.setUsername(objetivo.getUsername())
					.setNombre(objetivo.getNombre() +" "+ objetivo.getApellido());
			objetivo.getSeguidores().add(sol);
			solicitante.getSiguiendo().add(obj);
			if(usuarioRepository.save(objetivo)!=null && 
					null != usuarioRepository.save(solicitante)){
				return true;
			}
		}
		return false;
	}
	
	public boolean dejarSeguir(String usernameSol,String usernameObj){
		final Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		final Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(usuarioListBean.esSeguidor(solicitante, objetivo)){
			objetivo.getSeguidores().removeIf(usu -> usu.equals(solicitante.getUsername()));
			solicitante.getSiguiendo().removeIf(usu -> usu.equals(objetivo.getUsername()));
			if(usuarioRepository.save(objetivo)!=null &&
					null != usuarioRepository.save(solicitante)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean solicitudAmistad(String usernameSol,String usernameObj){
		final Usuario solicitante = usuarioRepository.findByUsernameIgnoreCase(usernameSol);
		final Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		if(!usuarioListBean.estaSolicitud(solicitante, objetivo)){
			objetivo.getSolicitudesAmistad().add(solicitante.getUsername());
			if(usuarioRepository.save(objetivo)!=null){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean accionSolicitud(String username, String usernameObj, String action) {
		final Usuario actual = usuarioRepository.findByUsernameIgnoreCase(username);
		final Usuario objetivo = usuarioRepository.findByUsernameIgnoreCase(usernameObj);
		
		actual.getSolicitudesAmistad().remove(objetivo.getUsername());
		
		if(action.equalsIgnoreCase("accept")){
			final InfoUsuario obj = new InfoUsuario()
					.setUsername(objetivo.getUsername())
					.setNombre(objetivo.getNombre() +" "+objetivo.getApellido());
			final InfoUsuario act = new InfoUsuario()
					.setUsername(actual.getUsername())
					.setNombre(actual.getNombre()+" "+actual.getApellido());
			actual.getAmigos().add(obj);
			objetivo.getAmigos().add(act);
			if(usuarioRepository.save(actual) != null && null != usuarioRepository.save(objetivo))
				return true;
			return false;
		}
		if(usuarioRepository.save(actual) != null)
			return true;
		return false;
	}
	
	public boolean agregarTGDirigido(TrabajoGrado tg, String username){
		final Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username);
		
		tg = trabajoGradoRepository.insert(tg);
		if(null == usuario.getTrabajosGradoDirigidos()){
			final List<TrabajoGrado> tgs = new ArrayList<>();
			tgs.add(tg);
			usuario.setTrabajosGradoDirigidos(tgs);
		}else
			usuario.getTrabajosGradoDirigidos().add(tg);
		
		return (null != usuarioRepository.save(usuario));
	}
	
	public boolean agregarFormacionAcademica(FormacionAcademica formacion, String username){
		final Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username);
		if(null == usuario.getFormacionesAcademicas()){
			final List<FormacionAcademica> formaciones = new ArrayList<>();
			formaciones.add(formacion);
			usuario.setFormacionesAcademicas(formaciones);
		}else
			usuario.getFormacionesAcademicas().add(formacion);
		
		return (null != usuarioRepository.save(usuario));
	}
	
	public boolean eliminarAmigo(String username, String usernameEliminar){
		final Usuario actual = usuarioRepository.findByUsernameIgnoreCase(username);
		final Usuario eliminar = usuarioRepository.findByUsernameIgnoreCase(usernameEliminar);
		if(!actual.getAmigos().removeIf(usu -> usu.equals(usernameEliminar)))
			return false;
		if(!eliminar.getAmigos().removeIf(usu -> usu.equals(username)))
			return false;
		return (null != usuarioRepository.save(actual) && null != usuarioRepository.save(eliminar));
	}

	@Override
	public boolean actualizarInfoAcademica(UsuarioDTO usuario) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(usuario.getId()));
		Usuario usuarioUpdate = MapEntities.mapDtoToUsuarioPartial(usuario);
		switch(usuario.getTipoUsuario()){
			case EGRESADO:
			case ESTUDIANTE:
			case PROFESOR:
				update.set("carreras", usuarioUpdate.getCarreras())
					.set("enfasis", usuarioUpdate.getEnfasis())
					.set("areasConocimiento", usuarioUpdate.getAreasConocimiento())
					.set("habilidades",usuarioUpdate.getHabilidades());
				break;
			default:
				break;
		}
		return mongoTemplate.updateFirst(query, update, Usuario.class).getN() > 0;
	}

	@Override
	public boolean actualizarHabilidadCualidad(UsuarioDTO usuario) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(usuario.getId()));
		switch(usuario.getTipoUsuario()){
			case EGRESADO:
			case ESTUDIANTE:
				update.set("habilidades", MapEntities
						.mapDtosToHabilidadAval(usuario.getHabilidades()));
				break;
			case PROFESOR:
				update.set("cualidadesProfesor", MapEntities
						.mapDtosToCualidadAval(usuario.getCualidades()));
				break;
			default:
				break;
		}
		return mongoTemplate.updateFirst(query, update, Usuario.class).getN() > 0;
	}

	@Override
	public boolean actualizarBasis(UsuarioDTO usuario) {
		final Update update = new Update();
		final Query query = Query
				.query( Criteria.where("_id").is(usuario.getId()));
		switch(usuario.getTipoUsuario()){
			case ESTUDIANTE:
				update.set("nombre",usuario.getNombre())
					.set("apellido", usuario.getApellido())
					.set("correo", usuario.getEmail())
					.set("semestre", usuario.getSemestre());
				break;
			case PROFESOR:
				update.set("grupoInvestigacion", "-");
			case EGRESADO:
				update.set("nombre",usuario.getNombre())
					.set("apellido", usuario.getApellido())
					.set("correo", usuario.getEmail());
				break;
			default:
				break;
		}
		return mongoTemplate.updateFirst(query, update, Usuario.class).getN() > 0;
	}
}
