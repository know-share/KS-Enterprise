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
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowshare.enterprise.bean.carrera.CarreraListBean;
import com.knowshare.enterprise.bean.carrera.CarreraListFacade;
import com.knowshare.enterprise.bean.carrera.CarreraModBean;
import com.knowshare.enterprise.bean.carrera.CarreraModFacade;
import com.knowshare.enterprise.bean.cualidad.CualidadListBean;
import com.knowshare.enterprise.bean.cualidad.CualidadListFacade;
import com.knowshare.enterprise.bean.gusto.GustoListBean;
import com.knowshare.enterprise.bean.gusto.GustoListFacade;
import com.knowshare.enterprise.bean.habilidad.HabilidadBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadFacade;
import com.knowshare.enterprise.bean.habilidad.HabilidadListBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadListFacade;
import com.knowshare.enterprise.bean.habilidad.HabilidadModBean;
import com.knowshare.enterprise.bean.habilidad.HabilidadModFacade;
import com.knowshare.enterprise.bean.idea.IdeaListBean;
import com.knowshare.enterprise.bean.idea.IdeaListFacade;
import com.knowshare.enterprise.bean.idea.IdeaModBean;
import com.knowshare.enterprise.bean.idea.IdeaModFacade;
import com.knowshare.enterprise.bean.personalidad.PersonalidadListBean;
import com.knowshare.enterprise.bean.personalidad.PersonalidadListFacade;
import com.knowshare.enterprise.bean.tag.TagListBean;
import com.knowshare.enterprise.bean.tag.TagListFacade;
import com.knowshare.enterprise.bean.tag.TagModBean;
import com.knowshare.enterprise.bean.tag.TagModFacade;
import com.knowshare.enterprise.bean.trabajogrado.TrabajoGradoListBean;
import com.knowshare.enterprise.bean.trabajogrado.TrabajoGradoListFacade;
import com.knowshare.enterprise.bean.usuario.UsuarioListBean;
import com.knowshare.enterprise.bean.usuario.UsuarioListFacade;
import com.knowshare.enterprise.bean.usuario.UsuarioModBean;
import com.knowshare.enterprise.bean.usuario.UsuarioModFacade;
import com.knowshare.entities.academia.Carrera;
import com.knowshare.entities.academia.TrabajoGrado;
import com.knowshare.entities.idea.Idea;
import com.knowshare.entities.idea.Tag;
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
 * @author Miguel Montañez
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
		Tag[] tags = mapper.readValue(
				ResourceUtils.getURL("classpath:data/tags.json").openStream(),Tag[].class);
		TrabajoGrado[] trabajoGrados = mapper.readValue(
				ResourceUtils.getURL("classpath:data/trabajo_grados.json").openStream(),TrabajoGrado[].class);
		Idea[] ideas = mapper.readValue(
				ResourceUtils.getURL("classpath:data/ideas.json").openStream(),Idea[].class);
		
		this.mongoTemplate().insertAll(Arrays.asList(carreras));
		this.mongoTemplate().insertAll(Arrays.asList(habilidades));
		this.mongoTemplate().insertAll(Arrays.asList(cualidades));
		this.mongoTemplate().insertAll(Arrays.asList(gustos));
		this.mongoTemplate().insertAll(Arrays.asList(personalidades));
		this.mongoTemplate().insertAll(Arrays.asList(usuarios));
		this.mongoTemplate().insertAll(Arrays.asList(tags));
		this.mongoTemplate().insertAll(Arrays.asList(trabajoGrados));
		this.mongoTemplate().insertAll(Arrays.asList(ideas));
		
		this.createIndexes();
		String command = "mongodump --host " +env.getProperty("db.host") + " --port " + env.getProperty("db.port")
	            + " -d " + env.getProperty("db.name") +" -o \"./target/\"";
		Runtime.getRuntime().exec(command);
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
	public HabilidadModFacade getHabilidadModFacade() {
		return new HabilidadModBean();
	}
	
	@Bean
	public HabilidadFacade getHabilidadFacade(){
		return new HabilidadBean();
	}

	@Bean
	public IdeaModFacade getIdeaModFacade() {
		return new IdeaModBean();
	}
	
	@Bean
	public IdeaListFacade getIdeaListFacade(){
		return new IdeaListBean();
	}
	
	@Bean
	public CarreraListFacade getCarreraListFacade(){
		return new CarreraListBean();
	}
	
	@Bean
	public CarreraModFacade getCarreraModFacade(){
		return new CarreraModBean();
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
	
	@Bean
	public TagListFacade getTagListFacade(){
		return new TagListBean();
	}
	
	@Bean
	public TagModFacade getTagModFacade(){
		return new TagModBean();
	}
	
	@Bean
	public TrabajoGradoListFacade getTrabajoGradoListFacade(){
		return new TrabajoGradoListBean();
	}
	
	@PreDestroy
	public void destroy() throws IOException{
		this.mongoTemplate().getDb().dropDatabase();
	}
	
	// ---------------------------
	// PRIVATE METHODS
	// ---------------------------
	
	private void createIndexes(){
		//db.habilidad.createIndex({'nombre':'text'},{ default_language: 'spanish' });
		TextIndexDefinition textIndex = TextIndexDefinition.builder()
				.onField("nombre")
				.withDefaultLanguage("spanish")
				.build();
		this.mongoTemplate().indexOps(Habilidad.class).ensureIndex(textIndex);
		
		//db.usuario.createIndex( { 'nombre': 'text','apellido':'text' },{ default_language: 'spanish' } );
		textIndex = TextIndexDefinition.builder()
				.onField("nombre")
				.onField("apellido")
				.withDefaultLanguage("spanish")
				.build();
		this.mongoTemplate().indexOps(Usuario.class).ensureIndex(textIndex);
	}
}
