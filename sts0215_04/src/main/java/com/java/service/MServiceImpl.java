package com.java.service;

import org.springframework.stereotype.Service;

@Service
public class MServiceImpl implements MService {

	@Override
	public String selectOne() {
		String id = "admin";
		return id;
	}

	@Override
	public void eventSelectOne() {
		// TODO Auto-generated method stub
		
	}
	
}
