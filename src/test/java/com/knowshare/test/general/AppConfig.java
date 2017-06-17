/**
 * 
 */
package com.knowshare.test.general;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.knowshare.enterprise.bean.habilidad.HabilidadListBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadListFacade;

/**
 * Clase de configuración para la inyección de beans en entorno de pruebas
 * @author miguel
 *
 */
@Configuration
public class AppConfig {
	
	@Bean
	public HabilidadListFacade getHabilidadListFacade(){
		return new HabilidadListBean();
	}

}
