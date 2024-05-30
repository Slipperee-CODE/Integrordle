package com.caischeidler.models;

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
