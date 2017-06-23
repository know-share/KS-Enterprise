/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowshare.dto.perfilusuario.UsuarioDTO;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioBean implements UsuarioFacade {
	
	@Autowired
	private UsuarioListFacade usuarioListBean;
	
	@Autowired
	private UsuarioModFacade usuarioModBean;

	@Override
	public boolean isUsernameTaken(String username) {
		return usuarioListBean.isUsernameTaken(username);
	}

	@Override
	public boolean crearUsuario(UsuarioDTO usuario){
		return usuarioModBean.crearUsuario(usuario);
	}
	
	
}
