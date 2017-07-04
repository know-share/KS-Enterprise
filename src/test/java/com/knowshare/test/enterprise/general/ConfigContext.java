/**
 * 
 */
package com.knowshare.test.enterprise.general;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowshare.enterprise.bean.carrera.CarreraListBean;
import com.knowshare.enterprise.bean.carrera.CarreraListFacade;
import com.knowshare.enterprise.bean.cualidad.CualidadListBean;
import com.knowshare.enterprise.bean.cualidad.CualidadListFacade;
import com.knowshare.enterprise.bean.gusto.GustoListBean;
import com.knowshare.enterprise.bean.gusto.GustoListFacade;
import com.knowshare.enterprise.bean.habilidad.HabilidadListBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadListFacade;
import com.knowshare.enterprise.bean.idea.IdeaModBean;
import com.knowshare.enterprise.bean.idea.IdeaModFacade;
import com.knowshare.enterprise.bean.personalidad.PersonalidadListBean;
import com.knowshare.enterprise.bean.personalidad.PersonalidadListFacade;
import com.knowshare.enterprise.bean.usuario.UsuarioListBean;
import com.knowshare.enterprise.bean.usuario.UsuarioListFacade;
import com.knowshare.enterprise.bean.usuario.UsuarioModBean;
import com.knowshare.enterprise.bean.usuario.UsuarioModFacade;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.perfilusuario.Cualidad;
import com.knowshare.entities.perfilusuario.Gusto;
import com.knowshare.entities.perfilusuario.Habilidad;
import com.knowshare.entities.perfilusuario.Personalidad;
import com.knowshare.entities.perfilusuario.Usuario;
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
	
	@PostConstruct
	public void initData() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		Carrera[] carreras = mapper.readValue(
				ResourceUtils.getURL("classpath:data/carreras.json").openStream(),Carrera[].class);
		Habilidad[] habilidades = mapper.readValue(
				ResourceUtils.getURL("classpath:data/habilidades.json").openStream(),Habilidad[].class);
		Cualidad[] cualidades = mapper.readValue(
				ResourceUtils.getURL("classpath:data/cualidades.json").openStream(),Cualidad[].class);
		Gusto[] gustos = mapper.readValue(
				ResourceUtils.getURL("classpath:data/gustos.json").openStream(),Gusto[].class);
		Personalidad[] personalidades = mapper.readValue(
				ResourceUtils.getURL("classpath:data/personalidades.json").openStream(),Personalidad[].class);
		Usuario[] usuarios = mapper.readValue(
				ResourceUtils.getURL("classpath:data/usuarios.json").openStream(),Usuario[].class);
		
		this.mongoTemplate().insertAll(Arrays.asList(carreras));
		this.mongoTemplate().insertAll(Arrays.asList(habilidades));
		this.mongoTemplate().insertAll(Arrays.asList(cualidades));
		this.mongoTemplate().insertAll(Arrays.asList(gustos));
		this.mongoTemplate().insertAll(Arrays.asList(personalidades));
		this.mongoTemplate().insertAll(Arrays.asList(usuarios));
	}
	
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
	
	@Bean
	public CarreraListFacade getCarreraListFacade(){
		return new CarreraListBean();
	}
	
	@Bean
	public CualidadListFacade getCualidadListFacade(){
		return new CualidadListBean();
	}
	
	@Bean
	public GustoListFacade getGustoListFacade(){
		return new GustoListBean();
	}
	
	@Bean
	public UsuarioListFacade getUsuarioListFacade(){
		return new UsuarioListBean();
	}
	
	@Bean
	public UsuarioModFacade getUsuarioModFacade(){
		return new UsuarioModBean();
	}
	
	@Bean
	public PersonalidadListFacade getPersonalidadListFacade(){
		return new PersonalidadListBean();
	}
	
	@PreDestroy
	public void destroy(){
		this.mongoTemplate().getDb().dropDatabase();
	}
}
