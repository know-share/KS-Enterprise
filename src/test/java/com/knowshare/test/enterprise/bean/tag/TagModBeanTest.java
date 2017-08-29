/**
 * 
 */
package com.knowshare.test.enterprise.bean.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.knowshare.enterprise.bean.tag.TagFacade;
import com.knowshare.entities.idea.Tag;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagModBeanTest extends AbstractTest{

	@Autowired
	private TagFacade tagModBean;
	
	@Test
	public void test01Create(){
		final boolean result = tagModBean.create("NEW TAG");
		assertTrue(result);
		final List<Tag> tags = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("NEW TAG")), Tag.class);
		assertEquals(1, tags.size());
		assertNotNull(tags.get(0).getId());
		assertEquals("NEW TAG",tags.get(0).getNombre());
	}
	
	@Test
	public void test02Update(){
		List<Tag> tags = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("NEW TAG")), Tag.class);
		final Tag tag = tags.get(0).setNombre("TAG UPDATED");
		final boolean result = tagModBean.update(tag);
		assertTrue(result);
		tags = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("TAG UPDATED")), Tag.class);
		assertEquals(1, tags.size());
		assertNotNull(tags.get(0).getId());
		assertEquals("TAG UPDATED",tags.get(0).getNombre());
	}
	
	@Test
	public void test03Delete(){
		List<Tag> tags = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("TAG UPDATED")), Tag.class);
		final boolean result = tagModBean.delete(tags.get(0).getId());
		assertTrue(result);
		tags = mongoTemplate
				.find(new Query(Criteria.where("nombre").is("TAG UPDATED")), Tag.class);
		assertEquals(0, tags.size());
	}
	
	@AfterClass
	public static void tearDown(){
	}
}
