package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.serive.Service;
import com.java.serive.ServiceImpl;

@Component
public class FController {
	
	@Autowired
	Service service;
//	Service service = new ServiceImpl(); 
	
	
	public void serviceList() {
		
		String id = service.list();
		System.out.println("DB에서 가져온 ID : "+ id);
		
		
	}
}
