package com.caischeidler.models;

public class Guess {
	public GuessPiece constant;
	public GuessPiece lowerBound;
	public GuessPiece upperBound;
	public GuessPiece integralContent;
	private GuessPiece[] guessPieces = {constant, lowerBound, upperBound, integralContent};
	public static final String[] COLORS = {"gray","yellow","green"};
	
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
		for (int i = 0; i < solution.guessPieces.length; i++) {
			for (int k = 0; k < guessPieces.length; k++) {
				solution.guessPieces[i].containsGuessPiece(guessPieces[k]);
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
