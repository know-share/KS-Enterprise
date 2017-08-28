/**
 * 
 */
package com.knowshare.test.enterprise.bean.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowshare.enterprise.bean.tag.TagFacade;
import com.knowshare.entities.idea.Tag;
import com.knowshare.test.enterprise.general.AbstractTest;

/**
 * @author Miguel Montañez
 *
 */
public class TagListBeanTest extends AbstractTest{
	
	@Autowired
	private TagFacade tagListBean;
	
	@Test
	public void test01FindAll(){
		final List<Tag> tags = tagListBean.findAll();
		assertNotNull(tags);
		assertEquals(tags.size(), 4);
	}
	
	@AfterClass
	public static void tearDown(){
	}

}
