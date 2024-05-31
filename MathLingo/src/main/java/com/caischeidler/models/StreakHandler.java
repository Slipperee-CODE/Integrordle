package com.caischeidler.models;

/*
StreakHandler.java
This class handles all logic related to the streak aspect of the game viewable on the home page 

Details:
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
public class StreakHandler {
	private int currStreak = 0;
	private String currStreakColor = GuessBox.GRAY;
	private int highestStreak = 0;
	private String highestStreakColor = GuessBox.GRAY;
	private final int SPACE_BETWEEN_COLOR_LEVEL_UPS = 2;
	
	/*
	 This method updates the user's problem streak when they return to the home page from the problem they were solving
	*/
	public void handleCurrStreak(ProblemHandler problemHandler) {
		if (problemHandler.isWin()) {
			currStreak++;
		} else if (problemHandler.getGuessIndex()==0){
			currStreak=currStreak; //I know this line is redundant it's just nice to see the logic
		} else {
			currStreak = 0;
		}
		highestStreak = Math.max(currStreak, highestStreak);
		currStreakColor = colorStreak(currStreak);
		highestStreakColor = colorStreak(highestStreak);
	}
	
	/*
	 This method colors the box the streak number resides in depending on if the user has reached a streak certain milestone
	*/
	private String colorStreak(int streak) {
		if (streak >= 2*SPACE_BETWEEN_COLOR_LEVEL_UPS) {
			return GuessBox.GREEN;
		} else if (streak >= 1*SPACE_BETWEEN_COLOR_LEVEL_UPS) {
			return GuessBox.YELLOW;
		} else {
			return GuessBox.GRAY;
		}
	}
	
	public int getCurrStreak() {
		return currStreak;
	}
	public void setCurrStreak(int currStreak) {
		this.currStreak = currStreak;
	}
	public String getCurrStreakColor() {
		return currStreakColor;
	}
	public void setCurrStreakColor(String currStreakColor) {
		this.currStreakColor = currStreakColor;
	}
	public int getHighestStreak() {
		return highestStreak;
	}
	public void setHighestStreak(int highestStreak) {
		this.highestStreak = highestStreak;
	}
	public String getHighestStreakColor() {
		return highestStreakColor;
	}
	public void setHighestStreakColor(String highestStreakColor) {
		this.highestStreakColor = highestStreakColor;
	}
}
