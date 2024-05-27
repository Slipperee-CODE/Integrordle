package com.caischeidler.controllers;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.caischeidler.models.Guess;
import com.caischeidler.models.Problem;
import com.caischeidler.models.ProblemHandler;

@Controller
public class MainController {
	
	//TO IMPLEMENT
	// - FORMAT MATH TEXT
	
	// - FIX COLORING SCHEME (ADD COLORS)
	// - FORMAT INTEGRAL INPUT
	// - WIN AND LOSE SCREENS
	// - PLAY AGAIN
	// - TEXT FILE FULL OF PROBLEMS   
	
	String[] SYMBOL_PATHS = {"images/multiply","images/subtract","images/add","images/divide"};
	ProblemHandler problemHandler;
	
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
		
		
		Problem problem = new Problem("an easy start", "f", "constant", "upperBound", "lowerBound", "area", new String[] {Problem.CHAR_BOX, Problem.EXPO_BOX}, new String[] {"1","2"});
		problemHandler = new ProblemHandler(problem, 2);
		
		model.addAttribute("problemHandler", problemHandler);
		model.addAttribute("userGuess", new Guess(problem));
		
		return "game";
	}
	
	@PostMapping("processGuess")
	public String processGuess(Guess userGuess, Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		

		problemHandler.handleCurrGuess(userGuess);
		model.addAttribute("problemHandler", problemHandler);
		model.addAttribute("userGuess", new Guess(problemHandler.getProblem())); //The problem in problemHandler is null for some reason here
		
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
