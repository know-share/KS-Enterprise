/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.enterprise.repository.perfilusuario.UsuarioRepository;
import com.knowshare.entities.perfilusuario.Usuario;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioListBean implements UsuarioListFacade{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean isUsernameTaken(String username) {
		Usuario usuario = usuarioRepository.findOne(username.toLowerCase());
		if(usuario != null)
			return true;
		return false;
	}

}
