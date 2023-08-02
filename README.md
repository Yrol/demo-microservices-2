# Demo microservice project

A demo Spring Boot microservices application created in my free time for learning purposes.


## Application architecture

![](https://i.imgur.com/m2ZvRya.png)

## Tech Stack
- Java 11
- Spring Boot v3.0.9
  - Service Registry and Discovery using Spring Cloud Netflix Eureka Server
  - Spring Cloud API Gateway
  - Spring Cloud Config Server
  - Feign (HTTP Client)
  - CircuitBreaker
  - Swagger for API documentation
- PL/SQL
- RabbitMQ
- Micrometer and Zipkin for distributed tracing
- Docker


### Service Registry and Discovery using Spring Cloud Netflix Eureka Server
The Spring Cloud Netflix Eureka Server has been used as the Service Discovery Server and the designated microservices have been registered as the clients (shown below). 
Each microservice will be identified using the Spring Application Name in their properties file.

[Service Registry module repo](https://github.com/Yrol/demo-microservices-2/tree/master/service-registry)

![](https://i.imgur.com/B9kJmm6.png)



### Spring Cloud API Gateway
Spring Cloud Gateway has been used within the project for request routing (to microservices) as well as for load balancing.

[API Gateway module repo](https://github.com/Yrol/demo-microservices-2/tree/master/api-gateway)

![](https://i.imgur.com/xIItoXb.png)


### Centralized Configurations using Spring Cloud Config Server

<strong>Prerequisites:</strong> Make sure the RabbitMQ is running on docker port 5672
that is required for the bus refresh.

```aidl
docker run --rm -it -d -p 5672:5672 rabbitmq:3.11.0
```

A centralized cloud config server has been used for externalising the configs each microservice via Github.
The Spring Cloud Bus library has also been used along with RabbitMQ for fetching new configs from the remote Git server 
without having to restart microservice each time when there's a config change. This will allow to pull the latest configs from Git and broadcast to 
the designated (subscribed) microservices via RabbitMQ.

[Config Server module repo](https://github.com/Yrol/demo-microservices-2/tree/master/config-server)<br/>
[External configs repo](https://github.com/Yrol/demo-microservices-2-configs)


![](https://i.imgur.com/nJFhcpf.png)

An example of bus refresh actuator endpoint for the Department microservice [POST].
```aidl
http://localhost:8080/actuator/busrefresh
```



### Distributed API request tracing

<Strong>Prerequisites: </strong>Make sure Zipkin is running in DOcker port 9441

```aidl
docker run -d -p 9441:9441 openzipkin/zipkin
```

Zipkin and Micrometer libs have been used for Distributed tracing, and the dashboard can be accessed via `http://localhost:9411/`. 
The tracing probability and Zipkin endpoint have been configured in each microservice. 
This setup can also capture internal calls from one microservice to another (ex: API-Gateway -> Employee service -> Department service).
![](https://i.imgur.com/Y2K1E42.png)

### Swagger API documentation
Uses OpenAPI Specification - Version 3
- Employee service - `http://localhost:8081/swagger-ui/index.html`
- Department service - `http://localhost:8080/swagger-ui/index.html`
- Organization service - `http://localhost:8083/swagger-ui/index.html`

### Microservices
To be updated.

