package com.caischeidler.models;

public class Guess {
	public GuessPiece constant;
	public GuessPiece lowerBound;
	public GuessPiece upperBound;
	public GuessPiece integralContent;
	private GuessPiece[] guessPieces = {constant, lowerBound, upperBound, integralContent};
	public static final String[] COLORS = {"wordle-gray","wordle-yellow","wordle-green"};
	
	public Guess(String[] solutionStrings) {
		for (int i = 0; i < guessPieces.length; i++) {
			guessPieces[i] = new GuessPiece(solutionStrings[i]);
		}
	}
	
	public Guess() {
		for (int i = 0; i < guessPieces.length; i++) {
			guessPieces[i] = new GuessPiece();
		}
	}

	public void updateColors(Guess solution){
		//FIX THIS LOGIC TO ACCOUNT FOR MULTIPLES OF THE SAME CHARACTER APPEARING ACROSS GUESSES
		
		
		for (int i = 0; i < solution.guessPieces.length; i++) {
			for (int k = 0; k < guessPieces.length; k++) {
				if (solution.guessPieces[i].input.indexOf(guessPieces[k].input) >= 0) {
					guessPieces[k].color = COLORS[1];
				}
			}
		}
		
		for (int i = 0; i < solution.guessPieces.length; i++) {
			if (guessPieces[i].input.equals(solution.guessPieces[i].input)) {
				guessPieces[i].color = COLORS[2];
			}
		}
	}
	
	public boolean checkForWin(){
		for (GuessPiece guessPiece : guessPieces) {
			if (!guessPiece.getColor().equals(COLORS[2])) {
				return false;
			}
		}
		return true;
	}
	
	public void setupGuessPieces() {
		guessPieces = new GuessPiece[] {constant, lowerBound, upperBound, integralContent};
	}
	
	public GuessPiece getConstant() {
		return constant;
	}

	public void setConstant(GuessPiece constant) {
		this.constant = constant;
	}

	public GuessPiece getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(GuessPiece lowerBound) {
		this.lowerBound = lowerBound;
	}

	public GuessPiece getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(GuessPiece upperBound) {
		this.upperBound = upperBound;
	}

	public GuessPiece getIntegralContent() {
		return integralContent;
	}

	public void setIntegralContent(GuessPiece integralContent) {
		this.integralContent = integralContent;
	}

	@Override
	public String toString() {
		return "Guess [constant=" + constant + ", lowerBound=" + lowerBound + ", upperBound=" + upperBound
				+ ", integralContent=" + integralContent + "]";
	}
}
