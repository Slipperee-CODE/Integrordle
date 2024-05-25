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
	
	public String getColor(){
		return color;
	}
	
	@Override
	public String toString() {
		return "GuessPiece [input=" + input + ", color=" + color + "]";
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
}
