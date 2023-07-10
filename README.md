# Demo microservice project

A demo Spring Boot microservices application created in my free time for learning pruposes.


## Application architecture

![alt text](https://i.imgur.com/m2ZvRya.png)

## Tech Stack
- Java 11
- Spring Boot v3.0.9
  - Service Registery and Discovery using Spring Cloud Netflix Eureka Server
  - Spring Cloud API Gateway
  - Spring Cloud Config Server
  - Feign (HTTP Client)
- PL/SQL
- Micrometer and Zipkin for distributed tracing
- Docker

#### Service Registery and Discovery using Spring Cloud Netflix Eureka Server
The Spring Cloud Netflix Eureka Server has been used as the service Discovery server and the designated microservices have been registred as the clients (shown below). 
Each microservice will be identified using the Spring Application Name in the properties file.

![alt text](https://i.imgur.com/B9kJmm6.png)

#### Centralized Configurations using Spring Cloud Config Server
To be updated.

## Microservices
To be updated.

