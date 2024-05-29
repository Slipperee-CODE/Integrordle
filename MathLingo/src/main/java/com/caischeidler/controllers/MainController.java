package com.caischeidler.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.caischeidler.models.Guess;
import com.caischeidler.models.Problem;
import com.caischeidler.models.ProblemHandler;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@GetMapping("") //maybe just put cuellar's favorite things in the menu like sponsors, have how to play be normal
	public String mainPage(Model model) {		
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		return "home";
	}
	
	@GetMapping("game")
	public String homePage(Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		

		problemHandler = getRandomProblem(1); //TO-DO: Create JSON files for problems 
		
		
		//Problem problem = new Problem("an easy start", "f", "constant", "upperBound", "lowerBound", "area", new String[] {Problem.CHAR_BOX, Problem.EXPO_BOX, Problem.CHAR_BOX, Problem.CHAR_BOX}, new String[] {"1","1","2","3"});
		//problemHandler = new ProblemHandler(problem, 2, 0);

		model.addAttribute("problemHandler", problemHandler);
		Guess guess = new Guess();
		guess.setProblem(problemHandler.getProblem());
		model.addAttribute("userGuess", guess);
		
		return "game";
	}
	
	@PostMapping("processGuess")
	public String processGuess(@ModelAttribute("userGuess") Guess userGuess, Model model) { //User Guess is not being received properly 
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		

		problemHandler.handleCurrGuess(userGuess);
		model.addAttribute("problemHandler", problemHandler);
		Guess guess = new Guess();
		guess.setProblem(problemHandler.getProblem());
		model.addAttribute("userGuess", guess);
		
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
	
	private ProblemHandler getRandomProblem(int problemCount) {
		ObjectMapper objectMapper = new ObjectMapper();
		int randomProblemID = (int) (Math.random()*problemCount);
		File problemJSONFile = new File("json/problem-" + randomProblemID + ".json");
		Problem problem = null;
		
		try {
			 problem = objectMapper.readValue(problemJSONFile, Problem.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ProblemHandler(problem, problem.getMaxGuesses(), randomProblemID);
	}
}
