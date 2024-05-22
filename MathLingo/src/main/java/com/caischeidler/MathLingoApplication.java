package com.caischeidler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caischeidler")
public class MathLingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathLingoApplication.class, args);
		System.out.println("Website Running!");	
	}
}
