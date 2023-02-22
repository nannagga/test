package com.java.www;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// spring이 DI해줌
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:bean.xml");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/java/www/bean.xml"); //같은 패키지 않에 있을때?
		
		MyCal myCal = ctx.getBean("myCal", MyCal.class);
//      myCal.setCalculator(new Calculator());
//      myCal.setFirstNum(10);
//      myCal.setSecondNum(2);

		myCal.add();
		myCal.sub();

	}// main

}// class