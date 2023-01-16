# e-media
E-MEDIA is a microservices implementation using Spring Boot, which is a popular framework for building microservices in Java. Spring Boot provides a set of tools for quickly building and deploying microservices, making it a popular choice for developers. The project contains multiple services, each with its own set of responsibilities and functionality. These services communicate with each other through a well-defined API, allowing them to work together to provide the overall functionality of the system. The project is designed to be a learning resource for understanding how to configure and build microservices, providing a clear and well-organized example of a microservices architecture. It can serve as a starting point for developers who want to learn about microservices and how to build them using Spring Boot.

It has the folloing services.
* **Discovery-service:** It is discovery server which holds information about all the clients which are registered as a microservice
* **Gateway-service:** This service routes all the requests to the correct service. I also implemented the requests authorization in this service that help us avoid the reimplementaion of same function in each service.
* **User-Service:** Each features for users of the system like registration, authentication, profile update, account deletion, device management will be implemented in this service.
* **Other services:** All other services are responsible for product management, like, storing product details, orders, shipment and ...

## How to run this project
