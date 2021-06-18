# spring-cloud-gateway-example with Oauth2 security using Keycloak IAM provider
# Requirements
* Have Keycloak running (default port 8080)
## Keycloak config
1. Create example realm in Keycloak. Add a confidential client named `gateway`, with valid redirection urls as `*`. Copy its secret to gateway-service's application.yml
2. start discovery-service (default port 8761)
3. start gateway-service (default port 8888)
4. start first-service (port 8000)
