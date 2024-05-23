package com.caischeidler.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	String[] SYMBOL_PATHS = {"multiply","subtract","plus","division"};
	
	@GetMapping("")
	public String mainPage(Model model) {
		List<ArrayList<Integer>> symbolList = new ArrayList<ArrayList<Integer>>();
		
		//randomly generate symbolList here
		
		int size = 20;
		
		for (int i = 0; i < size; i++) {
			symbolList.add(new ArrayList<Integer>());
			for (int k = 0; k < size; k++) {
				symbolList.get(i).add((int) (Math.random()*SYMBOL_PATHS.length));
			}
		}
		
		model.addAttribute("symbolList", symbolList);
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		return "mainPage";
	}
	
	@GetMapping("yo")
	public String homePage(Model model) {
		model.addAttribute("rating",6);
		//This is a comment for the sake of checking to see if GitHub will work with this setup lol
		return "homePage";
	}
	
	
}
