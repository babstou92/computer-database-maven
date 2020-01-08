package com.excilys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ComputerController {
	
	@RequestMapping("/")
	public String dashboard() {
		

		return "/view/dashboard.jsp";
	}
}
