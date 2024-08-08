package com.example.samuraitravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeController {

	// index表示
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

}
