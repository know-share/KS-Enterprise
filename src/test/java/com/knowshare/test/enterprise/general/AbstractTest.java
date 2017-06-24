package com.knowshare.test.enterprise.general;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConfigContext.class, loader = AnnotationConfigContextLoader.class)
public abstract class AbstractTest {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
