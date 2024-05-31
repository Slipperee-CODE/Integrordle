package com.caischeidler.models;

/*
ProblemID.java
This class stores the problem ID inputed by the user on the main page and is used to send that data to the game page for 
when the user wants a specific problem

Details:
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
public class ProblemID {
	private int problemID;
	private int TOTAL_PROBLEM_COUNT;

	public int getTOTAL_PROBLEM_COUNT() {
		return TOTAL_PROBLEM_COUNT;
	}

	public void setTOTAL_PROBLEM_COUNT(int tOTAL_PROBLEM_COUNT) {
		TOTAL_PROBLEM_COUNT = tOTAL_PROBLEM_COUNT;
	}
	
	public int getProblemID() {
		return problemID;
	}

	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}
}
