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
/*
MainController.java
This class is the main class for handling the loading and processing of both html pages and the data received from said html pages
*/
public class MainController {
	String[] SYMBOL_PATHS = {"images/multiply","images/subtract","images/add","images/divide"};
	ProblemHandler problemHandler;
	List<Integer> problemBag;
	StreakHandler streakHandler;
	public static final int PROBLEM_COUNT = 6;
	
	@GetMapping("") 
	/*
	 This method loads the home page for the first time when the website is loaded
	 
	 Details:
	 - Any time model.addAttribute(Object anObject) is called, I am sending "anObject" to the page to use its data in some form to be displayed
	 - The first two lines send the required list of background symbols and paths to generate the randomized background and are 
	 		repeated in each subsequent method of this class
 	 - This method also creates the initial storage containers for both the current problem ID and the streak data
	*/
	public String homePage(Model model) {		
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		ProblemID problemID = new ProblemID();
		problemID.setTOTAL_PROBLEM_COUNT(PROBLEM_COUNT);
		model.addAttribute("problemID", problemID);
		streakHandler = new StreakHandler();
		model.addAttribute("streakHandler", streakHandler);
		return "home";
	}
	
	@PostMapping("streakHome")
	/*
	 This method loads the home page for any subsequent time the page is requested in order to properly display the streak info
	 
	 Details:
	 - Everything else is the same as the normal home but this method is seperated just in case I want to change any of this 
	 		page's settings separate from the main home page's
	*/
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
	/*
	 This method gets a random problem from an array containing all the indexes of problems that are currently available based off the 
	 current PROBLEM_COUNT, creates a new Guess instance for the user to fill out and creates the problemHandler to handle the current 
	 Problem selected, it then shows the game page with this information
	*/
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
		
		problemHandler = getRandomProblem(problemBag); 
		

		model.addAttribute("problemHandler", problemHandler);
		Guess guess = new Guess();
		guess.setProblem(problemHandler.getProblem());
		model.addAttribute("userGuess", guess);
		
		return "game";
	}
	
	@PostMapping("processGuess")
	/*
	 This method processes the user's inputed Guess and updates the game page while getting ready to receive another Guess instance
	*/
	public String processGuess(@ModelAttribute("userGuess") Guess userGuess, Model model) {
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
	/*
	 This method is similar to the code of gamePage(Model model) except that it is meant to open a specific problem indicated by the user 
	 submitted problemID
	*/
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
	
	/*
	 This method creates a list of indexes of SYMBOL_PATHS which are later used to generate the randomized background
	 
	 Details:
	 - The reason it doesn't directly add the strings present in SYMBOL_PATHS into a list is because I wanted to test the iteration mechanics of 
	 		Thymeleaf while creating this + it was the first thing I created for this site
	*/
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
	
	/*
	 This method gets a random problem out of the current problemBag to make sure that the player experiences every other 
	 problem before getting the same one twice
	 
	 Details:
	 - It is still possible for the player to get the same problem twice if the problem is at the end of one bag and the start 
	 		of another but this is just a part of the design
	 - The problem data is loaded from a .json file to keep the MainController class clean (same as the method below)
	*/
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
	
	/*
	 This method gets a specific problem from a .json file with the corresponding id that is passed in
	*/
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
