package com.example.firstservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FirstServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}
	
	
}

@RestController
class HelloWorldController{
	@Value("${server.port}")
	private String port;

	@GetMapping("/hello")
	public String hello(){
			return "hello from port: " + port;
	}
}