package com.knowshare.test.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.knowshare.test.config.ConfigComponentScan;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConfigComponentScan.class)
public class SumTest {

	@Test()
	public void testSum(){

	}
}
