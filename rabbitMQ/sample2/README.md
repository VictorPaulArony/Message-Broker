# Sample 2: Registration and Notification Microservices

This sample project demonstrates a microservices architecture using Spring Boot, RabbitMQ, and a H2 in-memory database. It consists of two microservices: a `registration-service` and a `notification-service`.

## Overview

### Registration Service
-   **Description:** Handles user registration. It receives user data, validates it, saves it to the database, and sends a `UserRegistrationEvent` to a RabbitMQ topic exchange.
-   **Port:** `8080`

### Notification Service
-   **Description:** Subscribes to the RabbitMQ topic exchange, consumes `UserRegistrationEvent` messages, and sends a welcome email to the newly registered user.
-   **Port:** `8081`

## Prerequisites
-   Java 21
-   Maven
-   Docker

## Running the Application

### 1. Start RabbitMQ
Run RabbitMQ with the Management Plugin using Docker. The management UI will be available on port `15672`.

```bash
sudo docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

-   **Management UI:** [http://localhost:15672](http://localhost:15672)
-   **Default user/pass:** `guest` / `guest`

### 2. Run Registration Service
Navigate to the `registration-service` directory and run the application using Maven.

```bash
cd registration-service
mvn spring-boot:run
```

### 3. Run Notification Service
Navigate to the `notification-service` directory and run the application using Maven.

```bash
cd notification-service
mvn spring-boot:run
```

## Testing

### Registration Service
You can test the registration service by sending a POST request to the `/api/registration/register` endpoint.

```bash
curl -X POST http://localhost:8080/api/registration/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "viarony",
    "email": "victorpaularony@gmail.com",
    "password": "password123"
  }'
```

A successful registration will return a `201 CREATED` status with the user details in the response body.

### Notification Service
The notification service is triggered automatically when a user is successfully registered. To test it, you need to:

1.  **Configure your email credentials:** Open the `notification-service/src/main/resources/application-dev.properties` file and provide your email and password for the `spring.mail.username` and `spring.mail.password` properties.
2.  **Register a new user:** Use the `curl` command above to register a new user.
3.  **Check your email:** A welcome email should be sent to the email address you provided during registration.

## Configuration
-   **`registration-service`:** The main configuration is in `src/main/resources/application.properties`.
-   **`notification-service`:** The main configuration is in `src/main/resources/application.properties`. The email configuration for the `dev` profile is in `src/main/resources/application-dev.properties`.
