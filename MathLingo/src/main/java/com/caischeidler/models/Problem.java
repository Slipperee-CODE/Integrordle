package com.caischeidler.models;

public class Problem {
	private String description;
	private String mathJaxFormattedIntegral;
	private String mathJaxFormattedFunction;
	
	private String[] boxIDs;
	public static final String CHAR_BOX = "CHAR_BOX";
	public static final String EXPO_BOX = "EXPO_BOX";
	
	private String[] guessBoxSolutions;
	
	public Problem(String description, String functionLetter, String constant, String upperBound, String lowerBound, String area,
			String[] boxIDs, String[] guessBoxSolutions) {
		super();
		this.description = description;
		this.mathJaxFormattedIntegral = "$$" + constant + "\\int_{" + lowerBound + "}^{" +  upperBound + "}" + functionLetter + "(x) \\,dx =" + area + "$$";
		this.mathJaxFormattedFunction = "$$" + functionLetter + "(x) =" + "$$";
		this.boxIDs = boxIDs;
		this.guessBoxSolutions = guessBoxSolutions;
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
}
