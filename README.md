# Spring Cloud Gateway with Oauth2 security using Keycloak IAM provider (Stateful & Stateless authentication)
# Requirements
* Have Keycloak running (default port 8080)
## Keycloak config
1. Create example realm in Keycloak. 
2. Add a confidential client named `gateway`, with valid redirection urls as `*`. Copy its secret to gateway-service's application.yml
## Running
1. start discovery-service (default port 8761)
2. start gateway-service (default port 8888)
3. start first-service (port 8000)
To test load balacing, create another instance, create another instance with `.\mvnw spring-boot:run -Dspring-boot.run.arguments='--server.port=<port>'`
