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
		
		
		gameController = new GameController("a num", new Guess(new String[] {"1","b","a","x"}), 2);
		model.addAttribute("gameController", gameController);
		model.addAttribute("userGuess", new Guess());
		
		return "game";
	}
	
	@PostMapping("processGuess")
	public String processGuess(Guess userGuess, Model model) {
		model.addAttribute("symbolList", generateBackgroundSymbols(30));
		model.addAttribute("SYMBOL_PATHS", SYMBOL_PATHS);
		
		
		gameController.inputCurrGuess(userGuess);
		
		
		//TO IMPLEMENT
		// - PLAY AGAIN
		// - TEXT FILE FULL OF PROBLEMS
		// - WIN AND LOSE SCREENS
		
		
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
