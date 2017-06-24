/**
 * 
 */
package com.knowshare.test.enterprise.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.knowshare.enterprise.bean.habilidad.HabilidadListBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadListFacade;
import com.knowshare.enterprise.bean.idea.IdeaModBean;
import com.knowshare.enterprise.bean.idea.IdeaModFacade;
import com.mongodb.MongoClient;

/**
 * Configuración de contexto para las pruebas. Se cargan los bean de negocio que
 * serán necesarios para la ejecución de las pruebas
 * 
 * @author miguel
 *
 */
@Lazy
@Configuration
@EnableMongoRepositories(basePackages = { "com.knowshare.enterprise.repository" })
@PropertySource("classpath:database.properties")
public class ConfigContext {
	
	@Autowired
	private Environment env;
	
	@Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(
        		new MongoClient(env.getProperty("db.host"),
        						Integer.parseInt(env.getProperty("db.port"))),
        			env.getProperty("db.name"));
    }

	@Bean
	public HabilidadListFacade getHabilidadListFacade() {
		return new HabilidadListBean();
	}

	@Bean
	public IdeaModFacade getIdeaModFacade() {
		return new IdeaModBean();
	}
}
