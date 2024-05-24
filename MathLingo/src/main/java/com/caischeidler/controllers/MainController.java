package com.caischeidler.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	String[] SYMBOL_PATHS = {"images/multiply","images/subtract","images/plus","images/division"};
	
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
		return "mainPageSmall";
	}
	
	@GetMapping("game")
	public String homePage(Model model) {
		//Remember PostMapping and DataValidation from Data validation in Spring Boot tutorial and use that to get input for game
		//make input object to save game state 
		//use thymeleaf switch case statements to lock certain board sections
		//have setable number of board sections
		//To make use of layouts, create a header which has light and dark mode functionality and then make the other parts of the site bodies 
		//Maybe even try to make the background automatic across all pages including the game page but overlay it with a rounded square box of some sort
		//in the class for gamestate have variable for color of square and set it by that color in thymeleaf (possibly directly through inline css attribute)
		return "gamePage";
	}
	
	
}
