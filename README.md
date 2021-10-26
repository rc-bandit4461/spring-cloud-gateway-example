# Spring Cloud Gateway Template with Eureka with a REST service
# Steps:
1. start discovery-service (eureka)
2. start gateway-service
3. start first-service
# loadbalancing
to test loadbalacing, start another instance of first-service by changing the port using  
`.\mvnw spring-boot:run -Dspring-boot.run.arguments='--server.port=<port>'` 
