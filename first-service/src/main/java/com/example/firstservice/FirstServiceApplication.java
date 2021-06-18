package com.example.firstservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FirstServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}
	
	
}

@RestController
class HelloWorldController {
	@Value("${server.port}")
	private String port;

	@GetMapping("/public")
	public String publicResource() {
		return "Private resource: hello from port: " + port;
	}

	@GetMapping("/private")
	public String privateResource() {
		return "Private resource: hello from port: " + port;
	}
}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> authorize.antMatchers("/public**").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer().jwt();
	}
}