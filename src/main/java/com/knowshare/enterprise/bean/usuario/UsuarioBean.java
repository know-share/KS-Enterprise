/**
 * 
 */
package com.knowshare.enterprise.bean.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author miguel
 *
 */
@Component
public class UsuarioBean implements UsuarioFacade {
	
	@Autowired
	private UsuarioListFacade usuarioListBean;

	@Override
	public boolean isUsernameTaken(String username) {
		return usuarioListBean.isUsernameTaken(username);
	}
	
	
}
