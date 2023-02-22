package com.java.www;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
//		HelloWorldEn hello1 = new HelloWorldEn();
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beans.xml"); 
		//HelloWorldEn.clas
		Hello hello1 = ctx.getBean("hello1",Hello.class);
		callMethod(hello1);
	}
	
	public static void callMethod(Hello helloVo) {
		helloVo.sayHello();
	}
}
