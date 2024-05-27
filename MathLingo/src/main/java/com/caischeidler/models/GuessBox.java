package com.caischeidler.models;

public class GuessBox {
	private String value;
	private String color;
	public static final String GRAY = "integrordle-gray";
	public static final String YELLOW = "integrodle-yellow";
	public static final String GREEN = "integrordle-green";
	
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
