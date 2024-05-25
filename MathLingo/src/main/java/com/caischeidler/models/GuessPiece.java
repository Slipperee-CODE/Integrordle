package com.caischeidler.models;

public class GuessPiece {
	public String input;
	public String color;
	
	public GuessPiece(String input) {
		this.input = input;
		color = Guess.COLORS[0];
	}
	
	public GuessPiece() {
		input = "";
		color = Guess.COLORS[0];
	}
	
	public void containsGuessPiece(GuessPiece other) {
		int foundIndex = input.indexOf(other.input);
		if (foundIndex < 0) {
			other.color = Guess.COLORS[0];
		} else if (foundIndex > 0) {
			other.color = Guess.COLORS[1];
		} else {
			other.color = Guess.COLORS[2];
		}
	}
	
	public String getColor(){
		return color;
	}
	
	@Override
	public String toString() {
		return "GuessPiece [input=" + input + ", color=" + color + "]";
	}

}
