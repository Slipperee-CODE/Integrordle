package com.caischeidler.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
ProblemHandler.java
This is the main class for gameplay which takes charge of both doing the actual Wordle-esc logic of the game, the coloring of the boxes 
and the storage of several game states

Details:
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
public class ProblemHandler {
	private Guess[] guesses;
	private Problem problem;
	private int maxGuessesAllowed;
	private int guessIndex = 0;
	private boolean win = false;
	private int problemID;
	
	//Constructor
	public ProblemHandler(Problem problem, int maxGuessesAllowed, int problemID) {
		super();
		this.guesses = new Guess[maxGuessesAllowed];
		this.problem = problem;
		this.maxGuessesAllowed = maxGuessesAllowed;
		this.problemID = problemID;
	}

	/*
	 This method handles the guess inputed by the user by placing it in the array of guesses, checking for win/loss and moving forward the guessIndex
	*/
	public void handleCurrGuess(Guess guess) {
		guesses[guessIndex] = colorGuess(guess, problem);
		win = checkForWin(guesses[guessIndex], problem);
		guessIndex++;
	}
	
	/*
	 This method handles the coloring of the boxes based off Wordle's rules, it is pretty much the main game mechanic
	*/
	private Guess colorGuess(Guess guess, Problem problem) {
		//check for green first
		for (int i = 0; i < problem.getGuessBoxSolutions().length; i++) {
			if (guess.getGuessBoxes()[i].getValue().equals(problem.getGuessBoxSolutions()[i])){
				guess.getGuessBoxes()[i].setColor(GuessBox.GREEN);
			}
		}
		
		//nested for loop to check for yellow except any already green ones in solution and problem get skipped to check for yellows
		List<Integer> alreadySetToForYellowIndexes = new ArrayList<Integer>();
		
		for (int i = 0; i < problem.getGuessBoxSolutions().length; i++) {
			if (guess.getGuessBoxes()[i].getColor().equals(GuessBox.GRAY)) {
				int yellowForIndex = matchingCurrentlyGrayBox(guess, i , problem, alreadySetToForYellowIndexes);
				if (yellowForIndex > -1) {
					guess.getGuessBoxes()[i].setColor(GuessBox.YELLOW);
					alreadySetToForYellowIndexes.add(yellowForIndex);
				}
			}
		}
		return guess;
	}
	
	/*
	 This method is used to find if a box's value matches that of a box that is not green and that does not already have a yellow box for it
	*/
	private int matchingCurrentlyGrayBox(Guess guess, int currIndex, Problem problem, List<Integer> alreadySetToForYellowIndexes) {
		for (int i = 0; i < guess.getGuessBoxes().length; i++) {
			if (!guess.getGuessBoxes()[i].getColor().equals(GuessBox.GREEN) && guess.getGuessBoxes()[currIndex].getValue().equals(problem.getGuessBoxSolutions()[i])){
				if (!alreadySetToForYellowIndexes.contains(i)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/*
	 This method checks if all boxes are green, therefore determining if the player has won/lost
	*/
	private boolean checkForWin(Guess guess, Problem problem) {
		for (int i = 0; i < problem.getGuessBoxSolutions().length; i++) {
			if (!guess.getGuessBoxes()[i].getColor().equals(GuessBox.GREEN)){
				return false;
			}
		}
		return true;
	}

	public Guess[] getGuesses() {
		return guesses;
	}

	public void setGuesses(Guess[] guesses) {
		this.guesses = guesses;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public int getMaxGuessesAllowed() {
		return maxGuessesAllowed;
	}

	public void setMaxGuessesAllowed(int maxGuessesAllowed) {
		this.maxGuessesAllowed = maxGuessesAllowed;
	}

	public int getGuessIndex() {
		return guessIndex;
	}

	public void setGuessIndex(int guessIndex) {
		this.guessIndex = guessIndex;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public int getProblemID() {
		return problemID;
	}

	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}

	@Override
	public String toString() {
		return "ProblemHandler [guesses=" + Arrays.toString(guesses) + ", problem=" + problem + ", maxGuessesAllowed="
				+ maxGuessesAllowed + ", guessIndex=" + guessIndex + ", win=" + win + ", problemID=" + problemID + "]";
	}
}
