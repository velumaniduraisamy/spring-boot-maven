package com.basic.utils;
import org.junit.Test;
import org.junit.runner.RunWith;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.basic.utils.Arith;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)

// @SpringBootTest(classes =MyWebApplication.class)

public class ArithTest {

	@Test
	public void testAddition() {
		Arith arith = new Arith();
		arith.addition(1, 2);
		//return a+b;
	}
}
