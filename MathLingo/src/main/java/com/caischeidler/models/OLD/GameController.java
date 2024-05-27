package com.caischeidler.models.OLD;

import java.util.Arrays;

public class GameController {
	public int guessIndex = 0;
	public String area;
	public Guess[] guesses;
	private Guess solution;
	
	public GameController(String area, Guess solution, int maxGuesses) {
		this.solution = solution;
		this.area = area;
		guesses = new Guess[maxGuesses];
		for (int i = 0; i < maxGuesses; i++) {
			guesses[i] = new Guess();
		}
	}
	
	public boolean inputCurrGuess(Guess guess) {
		guess.setupGuessPieces();
		guesses[guessIndex] = guess;
		//System.out.println("Guesses List W/ User Guess" + Arrays.toString(guesses));
		guesses[guessIndex].updateColors(solution);
		if(guesses[guessIndex].checkForWin()) {
			return true;
		}
		guessIndex++;
		return false;
	}

	@Override
	public String toString() {
		return "GameController [guessIndex=" + guessIndex + ", area=" + area + ", guesses=" + Arrays.toString(guesses)
				+ ", solution=" + solution + "]";
	}
	
	
}
