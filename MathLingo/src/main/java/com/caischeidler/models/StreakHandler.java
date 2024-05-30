package com.caischeidler.models;

public class StreakHandler {
	private int currStreak = 0;
	private String currStreakColor = GuessBox.GRAY;
	private int highestStreak = 0;
	private String highestStreakColor = GuessBox.GRAY;
	private final int SPACE_BETWEEN_COLOR_LEVEL_UPS = 2;
	
	public void handleCurrStreak(ProblemHandler problemHandler) {
		if (problemHandler.isWin()) {
			currStreak++;
		} else {
			currStreak = 0;
		}
		highestStreak = Math.max(currStreak, highestStreak);
		currStreakColor = colorStreak(currStreak);
		highestStreakColor = colorStreak(highestStreak);
	}
	
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
