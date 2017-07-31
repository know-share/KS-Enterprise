/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;
import com.knowshare.enterprise.bean.habilidad.HabilidadFacade;
import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.enterprise.utils.MapEntities;
import com.knowshare.enterprise.utils.UtilsPassword;
import com.knowshare.entities.perfilusuario.InfoUsuario;
import com.knowshare.entities.perfilusuario.Usuario;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author Miguel Montañez
 *
 */
@Component
public class UsuarioListBean implements UsuarioListFacade{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private HabilidadFacade habilidadBean;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isUsernameTaken(String username) {
		Usuario usuario = usuarioRepository
				.findByUsernameIgnoreCase(username);
		if(usuario != null)
			return true;
		return false;
	}
	
	public Usuario login(String username,String password){
		Usuario usuario;
		try {
			usuario = usuarioRepository
					.findByPasswordAndUsernameIgnoreCase(
							UtilsPassword.hashPassword(username, password),
							username);
			return usuario;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public boolean esSeguidor(Usuario uSol,Usuario uObj){
		if(null != uSol && null != uObj){
			final InfoUsuario info = new InfoUsuario()
					.setNombre(uSol.getNombre()+" "+uSol.getApellido())
					.setUsername(uSol.getUsername());
			if(uObj.getSeguidores().contains(info)){
				return true;
			}
		}
		return false;
	}
	
	public boolean estaSolicitud(Usuario uSol,Usuario uObj){
		if(null != uSol && null != uObj){
			final InfoUsuario info = new InfoUsuario()
					.setNombre(uSol.getNombre()+" "+uSol.getApellido())
					.setUsername(uSol.getUsername());
			if(uObj.getSeguidores().contains(info) 
					|| uObj.getAmigos().contains(info)
					|| uObj.getSolicitudesAmistad().contains(uSol.getUsername())){
				return true;
			}
		}
		return false;
	}

	@Override
	public UsuarioDTO getUsuario(String username) {
		Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username);
		if(usuario == null)
			return null;
		return MapEntities.mapUsuarioToDTO(usuario);
	}

	/**
	 * El username se asume que llega igual como está en la base de datos, ya que
	 * este es sacado del token.
	 */
	@Override
	public List<UsuarioDTO> getMyNoConnections(String username) {
		final List<Usuario> usuarios = usuarioRepository.findMyNoConnections(username);
		final List<UsuarioDTO> usuarioRet = new ArrayList<>();
		for (Usuario usuario : usuarios)
			usuarioRet.add(MapEntities.mapUsuarioToDTO(usuario));
		return usuarioRet;
	}

	@Override
	public boolean isCorreoTaken(String correo) {
		return usuarioRepository.findByCorreoIgnoreCase(correo) != null;
	}

	@Override
	public List<UsuarioDTO> buscarPorNombre(UsuarioDTO usuarioActual, String param) {
		final List<Usuario> usuarios = usuarioRepository.searchByNombreOrApellido(param);
		final List<UsuarioDTO> usuarioRet = new ArrayList<>();
		for (Usuario usuario : usuarios)
			if(!usuario.getUsername().equalsIgnoreCase(usuarioActual.getUsername()))
				usuarioRet.add(MapEntities.mapUsuarioToDTO(usuario));
		return usuarioRet;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> buscarPorHabilidad(UsuarioDTO usuarioActual, String param){
		final List<ObjectId> idsHabilidades = habilidadBean.buscarPorNombre(param);
		
		final Aggregation agg = newAggregation(
					unwind("habilidades"),
					match(where("habilidades.habilidad.$id").in(idsHabilidades)),
					group("username","nombre","apellido","carreras")
						.max("habilidades.cantidad").as("maximo"),
					sort(Sort.Direction.DESC,"maximo")
				);
		
		AggregationResults<Map> result = 
				mongoTemplate.aggregate(agg, Usuario.class, Map.class);
		
		return result.getMappedResults();
	}

}
