package com.java.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.service.MService;

@Controller
public class FController {
	
	@Autowired
	MService mservice;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public String index2(Model model) {
		String name = "홍길동";
		model.addAttribute("fname",name); //request.setAttribute("name",name);
		System.out.println("controller name : "+ name);
		return "index";
	}
	
	
	@GetMapping("login")
	public String login(Model model) {
		String id = mservice.memberSelectOne();
		System.out.println("controller id : "+id);
		session.setAttribute("sessionId", id);
		return "event/login";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		session.invalidate();
		return "event/logout";
	}
	
	@GetMapping("event")
	public ModelAndView event() {
		ModelAndView mv = new ModelAndView();
		String name = "이순신";
		mv.addObject("fname", name);
		mv.setViewName("event/event");
		
		
		return mv;
	}
	
	@PostMapping("event")
	public String doEvent(Model model) {
		return "event/doEvent";
	}
	
	
	
	
	
	
	
}//class
