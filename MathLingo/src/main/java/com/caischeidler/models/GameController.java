package com.caischeidler.models;

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
		guesses[guessIndex] = guess;
		guesses[guessIndex].updateColors(solution);
		if(guesses[guessIndex].checkForWin()) {
			return true;
		}
		System.out.println(guesses[guessIndex]);
		guessIndex++;
		return false;
	}
}
