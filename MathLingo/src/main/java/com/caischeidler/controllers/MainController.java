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
import com.caischeidler.models.JSONStoredProblem;
import com.caischeidler.models.Problem;
import com.caischeidler.models.ProblemHandler;
import com.caischeidler.models.ProblemID;
import com.caischeidler.models.StreakHandler;
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
	List<Integer> problemBag;
	StreakHandler streakHandler;
	public static final int PROBLEM_COUNT = 5;
	
	@GetMapping("") //maybe just put cuellar's favorite things in the menu like sponsors, have how to play be normal
	public String homePage(Model model) {		
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		ProblemID problemID = new ProblemID();
		problemID.setTOTAL_PROBLEM_COUNT(PROBLEM_COUNT);
		model.addAttribute("problemID", problemID);
		streakHandler = new StreakHandler();
		model.addAttribute("streakHandler",streakHandler);
		return "home";
	}
	
	@PostMapping("streakHome") //maybe just put cuellar's favorite things in the menu like sponsors, have how to play be normal
	public String streakHomePage(Model model) {		
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		ProblemID problemID = new ProblemID();
		problemID.setTOTAL_PROBLEM_COUNT(PROBLEM_COUNT);
		model.addAttribute("problemID", problemID);
		streakHandler.handleCurrStreak(problemHandler);
		model.addAttribute("streakHandler", streakHandler);
		return "home";
	}
	
	@GetMapping("game")
	public String gamePage(Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		
		if (problemBag == null) {
			problemBag = new ArrayList<Integer>();
			for (int i = 0; i < PROBLEM_COUNT; i++) {
				problemBag.add(i);
			}
		}
		if (problemBag.size()==0) {
			for (int i = 0; i < PROBLEM_COUNT; i++) {
				problemBag.add(i);
			}
		}
		
		problemHandler = getRandomProblem(problemBag); //TO-DO: Create JSON files for problems 
		
		//Make custom class for problem data then construct instance of problem class using it
		
		//Problem problem = new Problem("an easy start", "f", "constant", "upperBound", "lowerBound", "area", new String[] {Problem.CHAR_BOX, Problem.EXPO_BOX, Problem.CHAR_BOX, Problem.CHAR_BOX}, new String[] {"1","1","2","3"}, 2);
		//problemHandler = new ProblemHandler(problem, problem.getMaxGuesses(), 0);
		//System.out.println(problemHandler);

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
	
	@PostMapping("processCustomProblemRequest")
	public String processCustomProblemRequest(@ModelAttribute("problemID") ProblemID problemID, Model model) { //User Guess is not being received properly 
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		
		
		problemHandler = getSpecificProblem(problemID.getProblemID());
		
		
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
	
	private ProblemHandler getRandomProblem(List<Integer> problemBag) {
		ObjectMapper objectMapper = new ObjectMapper();
		int randomProblemIndex = (int) (Math.random()*problemBag.size());
		int randomProblemID = problemBag.get(randomProblemIndex);
		File problemJSONFile = new File("src/main/resources/static/json/problem-" + randomProblemID + ".json");
		JSONStoredProblem jsonP = null;
		problemBag.remove(randomProblemIndex);
		
		try {
			 jsonP = objectMapper.readValue(problemJSONFile, JSONStoredProblem.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Problem problem = new Problem(jsonP.getDescription(),jsonP.getFunctionLetter(),jsonP.getConstant(),jsonP.getUpperBound(),jsonP.getLowerBound(),jsonP.getArea(),jsonP.getBoxIDs(),jsonP.getGuessBoxSolutions(),jsonP.getMaxGuesses());
		return new ProblemHandler(problem, problem.getMaxGuesses(), randomProblemID);
	}
	
	private ProblemHandler getSpecificProblem(int id) {
		ObjectMapper objectMapper = new ObjectMapper();
		File problemJSONFile = new File("src/main/resources/static/json/problem-" + id + ".json");
		JSONStoredProblem jsonP = null;
		
		try {
			 jsonP = objectMapper.readValue(problemJSONFile, JSONStoredProblem.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Problem problem = new Problem(jsonP.getDescription(),jsonP.getFunctionLetter(),jsonP.getConstant(),jsonP.getUpperBound(),jsonP.getLowerBound(),jsonP.getArea(),jsonP.getBoxIDs(),jsonP.getGuessBoxSolutions(),jsonP.getMaxGuesses());
		return new ProblemHandler(problem, problem.getMaxGuesses(), id);
	}
}
