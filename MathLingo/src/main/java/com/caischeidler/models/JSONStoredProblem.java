package com.caischeidler.models;

import java.util.Arrays;

/*
JSONStoredProblem.java
This class is what is used to retrieve problem data from a .json file

Details:
- The high number of getters and setters in this class is because it is used to store all the data of a standard problem in 1 class, 
	meaning I need to access all its data hence all the getters and setters
*/
public class JSONStoredProblem {
	private String description;
	private String functionLetter; 
	private String constant;
	private String upperBound;
	private String lowerBound;
	private String area;
	private String[] boxIDs; 
	private String[] guessBoxSolutions; 
	private int maxGuesses;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFunctionLetter() {
		return functionLetter;
	}
	public void setFunctionLetter(String functionLetter) {
		this.functionLetter = functionLetter;
	}
	public String getConstant() {
		return constant;
	}
	public void setConstant(String constant) {
		this.constant = constant;
	}
	public String getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}
	public String getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public int getMaxGuesses() {
		return maxGuesses;
	}
	public void setMaxGuesses(int maxGuesses) {
		this.maxGuesses = maxGuesses;
	}
	@Override
	public String toString() {
		return "JSONStoredProblem [description=" + description + ", functionLetter=" + functionLetter + ", constant="
				+ constant + ", upperBound=" + upperBound + ", lowerBound=" + lowerBound + ", area=" + area
				+ ", boxIDs=" + Arrays.toString(boxIDs) + ", guessBoxSolutions=" + Arrays.toString(guessBoxSolutions)
				+ ", maxGuesses=" + maxGuesses + "]";
	}
}
