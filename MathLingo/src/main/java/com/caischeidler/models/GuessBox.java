package com.caischeidler.models;

/*
GuessBox.java
This class is what stores individual characters worth of user input

Details:
- This class contains both the current color and value of the box it represents
- The high number of getters and setters in this class is because it is used by Thymeleaf on the HTML page itself 
	to access its data which requires getters and setters to function
*/
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
