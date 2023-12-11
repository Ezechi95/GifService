package com.netflix.GifService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GifServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GifServiceApplication.class, args);
	}

}
