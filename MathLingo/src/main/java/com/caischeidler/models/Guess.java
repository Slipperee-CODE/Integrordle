package com.caischeidler.models;

import java.util.Arrays;

public class Guess {
	private GuessBox[] guessBoxes;

	public Guess(Problem problem) {
		super();
		this.guessBoxes = new GuessBox[problem.getBoxIDs().length];
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
