package com.example.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	

}
@Configuration
class GatewayConfig{
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route("first-service",
						(route) -> route.path("/first/**").filters(f -> f.rewritePath("/first/(?<segment>.*)", "/${segment}"))

								.uri("lb://FIRST-SERVICE/"))
								.build();
	}
}

@Configuration
@EnableWebFluxSecurity
class GatewaySecurityConfig {
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.csrf().disable().authorizeExchange().pathMatchers("/public**","/first/public**").permitAll().anyExchange().authenticated().and().oauth2Login().and()
				.oauth2ResourceServer().jwt();
		return http.build();
	}
}

@RestController
class HelloController {
	@GetMapping("/public")
	public String publicResource() {
		return "this is a public resource";
	}

	@GetMapping("/private")
	public String privateResource() {
		return "This is a private resource";
	}
}
