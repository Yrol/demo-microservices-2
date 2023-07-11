# Demo microservice project

A demo Spring Boot microservices application created in my free time for learning purposes.


## Application architecture

![alt text](https://i.imgur.com/m2ZvRya.png)

## Tech Stack
- Java 11
- Spring Boot v3.0.9
  - Service Registry and Discovery using Spring Cloud Netflix Eureka Server
  - Spring Cloud API Gateway
  - Spring Cloud Config Server
  - Feign (HTTP Client)
- PL/SQL
- RabbitMQ
- Micrometer and Zipkin for distributed tracing
- Docker


### Service Registry and Discovery using Spring Cloud Netflix Eureka Server
The Spring Cloud Netflix Eureka Server has been used as the service Discovery server and the designated microservices have been registered as the clients (shown below). 
Each microservice will be identified using the Spring Application Name in the properties file.

[Service Registry module repo](https://github.com/Yrol/demo-microservices-2/tree/master/service-registry)

![alt text](https://i.imgur.com/B9kJmm6.png)



### Spring Cloud API Gateway
Spring Cloud Gateway has been within the project for request routing (to microservices) and also for load balancing.

[API Gateway module repo](https://github.com/Yrol/demo-microservices-2/tree/master/api-gateway)

![alt text](https://i.imgur.com/xIItoXb.png)


### Centralized Configurations using Spring Cloud Config Server
A centralized cloud config server has been used for externalising the configs each microservice via Github.
The Spring Cloud Bus library has also been used along with RabbitMQ for fetching new configs from the remote Git server 
without having to restart microservice each time when there's a config change. This will allow to pull the latest configs from Git and broadcast to 
the designated (subscribed) microservices via RabbitMQ.

[Config Server module repo](https://github.com/Yrol/demo-microservices-2/tree/master/config-server)

![alt text](https://i.imgur.com/nJFhcpf.png)

An example of bus refresh actuator endpoint for the Department microservice [POST].
```aidl
http://localhost:8080/actuator/busrefresh
```



### Distributed API request tracing
To be updated.

### Microservices
To be updated.

