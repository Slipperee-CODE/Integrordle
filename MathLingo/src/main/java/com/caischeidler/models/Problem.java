package com.caischeidler.models;

import java.util.Arrays;

/*
Problem.java
This class is what formats and stores the mathematical expressions which make up the problem statement along with the user's guesses and the
array containing the solutions for the problem

Details:
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
public class Problem {
	private String description;
	private String mathJaxFormattedIntegral;
	private String mathJaxFormattedFunction;
	
	private String[] boxIDs;
	public static final String CHAR_BOX = "CHAR_BOX";
	public static final String EXPO_BOX = "EXPO_BOX";
	
	private String[] guessBoxSolutions;
	private int maxGuesses;
	
	public Problem(String description, String functionLetter, String constant, String upperBound, String lowerBound, String area,
			String[] boxIDs, String[] guessBoxSolutions, int maxGuesses) {
		super();
		this.description = description;
		this.mathJaxFormattedIntegral = "$$" + constant + "\\int_{" + lowerBound + "}^{" +  upperBound + "}" + functionLetter + "(x) \\,dx =" + area + "$$";
		this.mathJaxFormattedFunction = "$$" + functionLetter + "(x) =" + "$$";
		this.boxIDs = boxIDs;
		this.guessBoxSolutions = guessBoxSolutions;
		this.maxGuesses = maxGuesses;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMathJaxFormattedIntegral() {
		return mathJaxFormattedIntegral;
	}

	public void setMathJaxFormattedIntegral(String mathJaxFormattedIntegral) {
		this.mathJaxFormattedIntegral = mathJaxFormattedIntegral;
	}

	public String getMathJaxFormattedFunction() {
		return mathJaxFormattedFunction;
	}

	public void setMathJaxFormattedFunction(String mathJaxFormattedFunction) {
		this.mathJaxFormattedFunction = mathJaxFormattedFunction;
	}

	public String[] getBoxIDs() {
		return boxIDs;
	}

	public void setBoxIDs(String[] boxIDs) {
		this.boxIDs = boxIDs;
	}

	public String[] getGuessBoxSolutions() {
		return guessBoxSolutions;
	}

	public void setGuessBoxSolutions(String[] guessBoxSolutions) {
		this.guessBoxSolutions = guessBoxSolutions;
	}

	public static String getCharBox() { 
		return CHAR_BOX;
	}

	public static String getExpoBox() {
		return EXPO_BOX;
	}

	public int getMaxGuesses() {
		return maxGuesses;
	}

	public void setMaxGuesses(int maxGuesses) {
		this.maxGuesses = maxGuesses;
	}

	@Override
	public String toString() {
		return "Problem [description=" + description + ", mathJaxFormattedIntegral=" + mathJaxFormattedIntegral
				+ ", mathJaxFormattedFunction=" + mathJaxFormattedFunction + ", boxIDs=" + Arrays.toString(boxIDs)
				+ ", guessBoxSolutions=" + Arrays.toString(guessBoxSolutions) + ", maxGuesses=" + maxGuesses + "]";
	}
}
