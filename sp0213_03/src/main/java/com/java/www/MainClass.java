package com.java.www;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
//		AbstractApplicationContext ctx = new GenericApplicationContext("classpath:beans.xml");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/java/www/cal_config.xml");//xml을 불러오는것
		
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:cal_config.xml");
		MyCal myCal = ctx.getBean(MyCal.class);
		myCal.add_class();
		
//		add(myCal);
	}
	
	public static void add(MyCal myCal) {
		int result = myCal.getFirstNum() + myCal.getSecondNum();
		System.out.println("결과 값 : "+result);
		
		
		
	}
}
