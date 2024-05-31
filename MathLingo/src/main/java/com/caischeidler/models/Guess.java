package com.caischeidler.models;

import java.util.Arrays;
/*
Guess.java
This class is how the player inputs their guesses and one of these is generated per guess the user makes

Details:
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
public class Guess {
	private GuessBox[] guessBoxes;
	private Problem problem;
	
	public void setProblem(Problem problem) {
		this.problem = problem;
		this.guessBoxes = new GuessBox[problem.getBoxIDs().length];
	}
	
	public Problem getProblem() {
		return problem;
	}

	public GuessBox[] getGuessBoxes() {
		return guessBoxes;
	}

	public void setGuessBoxes(GuessBox[] guessBoxes) {
		this.guessBoxes = guessBoxes;
	}

	@Override
	public String toString() {
		return "Guess [guessBoxes=" + Arrays.toString(guessBoxes) + "]";
	}
}
