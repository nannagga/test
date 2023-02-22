package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FController {

	@GetMapping("/login") //login.jsp 페이지를 열어줘
	public String login(Model model) {
		
		return "login";
	}
	
	@GetMapping("/event") //
	public String event(Model model) {
		
		return "event";
	}
}
