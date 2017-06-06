package com.knowshare.test.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.knowshare.enterprise.bean.Sum;
import com.knowshare.enterprise.main.Main;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class SumTest {
	
	@Autowired
	private Sum sum;

	@Test()
	public void testSum(){
		int result = sum.sum(5, 6);
		assertEquals(result, 11);
//		assertEquals(true, false);
	}
}
