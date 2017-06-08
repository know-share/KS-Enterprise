package com.knowshare.test.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.knowshare.enterprise.bean.ISum;
import com.knowshare.test.config.ConfigComponentScan;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConfigComponentScan.class)
public class SumTest {
	
	@Autowired
	private ISum sum;

	@Test()
	public void testSum(){
		int result = sum.sum(5, 6);
		assertEquals(result, 11);
//		assertEquals(true, false);
	}
}
