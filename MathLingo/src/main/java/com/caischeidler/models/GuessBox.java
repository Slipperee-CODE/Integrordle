package com.caischeidler.models;

public class GuessBox {
	public static final String GRAY = "integrordle-gray";
	public static final String YELLOW = "integrordle-yellow";
	public static final String GREEN = "integrordle-green";
	
	private String value;
	private String color = GRAY;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
