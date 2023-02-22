package com.java.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	
	public String selectAll() {
		String id = "admin관리자";
		System.out.println("DB에서 가져온 id : "+id);
		return id;
	}
	
	
}
