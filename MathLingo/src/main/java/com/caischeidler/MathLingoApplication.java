	package com.caischeidler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caischeidler")
/*
 MathLingoApplication.java
 This class starts the server for the website itself locally so that it can be seen by going to http://localhost:8080/ in your browser
*/
public class MathLingoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MathLingoApplication.class, args);
		System.out.println("Website Running!");	
	}
}
