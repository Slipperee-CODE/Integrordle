package com.caischeidler.models;

import java.util.Arrays;

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
