package com.caischeidler.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.caischeidler.models.GameController;
import com.caischeidler.models.Guess;

@Controller
public class MainController {
	
	String[] SYMBOL_PATHS = {"images/multiply","images/subtract","images/add","images/divide"};
	GameController gameController;
	
	@GetMapping("")
	public String mainPage(Model model) {		
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		return "home";
	}
	
	@GetMapping("game")
	public String homePage(Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		
		
		gameController = new GameController("a num", new Guess(new String[] {"1","b","a","x"}), 10);
		model.addAttribute("gameController", gameController);
		model.addAttribute("userGuess", new Guess());
		
		
		//Remember PostMapping and DataValidation from Data validation in Spring Boot tutorial and use that to get input for game
		//make input object to save game state 
		//use thymeleaf switch case statements to lock certain board sections
		//have setable number of board sections
		//To make use of layouts, create a header which has light and dark mode functionality and then make the other parts of the site bodies 
		//Maybe even try to make the background automatic across all pages including the game page but overlay it with a rounded square box of some sort
		//in the class for gamestate have variable for color of square and set it by that color in thymeleaf (possibly directly through inline css attribute)
		return "game";
	}
	
	@PostMapping("processGuess")
	public String processGuess(Guess userGuess, Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		
		
		//These print statements aren't running and the inputCurrGuess method does not seem to be running, need to fix for game to work
		if(gameController == null) {
			System.out.println("here");
		}
		gameController.inputCurrGuess(userGuess);
		
		
		model.addAttribute("userGuess", new Guess());
		model.addAttribute("gameController", gameController);
		return "game";
	}
	
	private List<ArrayList<Integer>> generateBackgroundSymbols(int size) {
		List<ArrayList<Integer>> symbolList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < size; i++) {
			symbolList.add(new ArrayList<Integer>());
			for (int k = 0; k < size; k++) {
				symbolList.get(i).add((int) (Math.random()*SYMBOL_PATHS.length));
			}
		}
		return symbolList;
	}
	
}
