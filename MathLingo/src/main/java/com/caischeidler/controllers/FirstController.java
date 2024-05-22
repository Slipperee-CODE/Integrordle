package com.caischeidler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
	
	@GetMapping("/dailyPage")
	public String dailyPage(Model model) {
		model.addAttribute("rating",6);
		return "homePage";
	}
}
