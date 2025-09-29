# Sample1 Spring Boot RabbitMQ Messaging Example

This is a simple Spring Boot application that demonstrates a **producer-consumer** pattern using **RabbitMQ**. Messages are sent via a REST API and consumed from a message queue.

---

## Features

- Send messages via HTTP POST
- Publish messages to a RabbitMQ topic exchange
- Consume messages from a queue with a listener
- Configuration using `application.properties`
- Dockerized RabbitMQ setup

---

## Running RabbitMQ with Docker

Run RabbitMQ with the Management Plugin on port `5672` (AMQP) and `15672` (HTTP UI):

```bash
docker run -d --hostname my-rabbit --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management
```

* Management UI: [http://localhost:15672](http://localhost:15672)
* Default user/pass: `guest` / `guest`

---

## Configuration (`application.properties`)

Make sure your `application.properties` looks like this:

```properties
rabbitmq.queue.name=sms_queue
rabbitmq.exchange.name=sms_exchange
rabbitmq.routing.Key=sms_routingKey

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

---

## Build & Run

### Using Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Or:

```bash
java -jar target/sample1-0.0.1-SNAPSHOT.jar
```

---

## Sending a Message (Test via cURL)

Once the application is running:

```bash
curl -X POST "http://localhost:8080/api/sms/send" \
  -d "message=Hello from cURL"
```

**Expected Output:**

```
Message sent: Hello from cURL
```

You’ll also see logs:

* In the producer: `Message sent by producer: Hello from cURL`
* In the consumer: `Message Received by Consumer: Hello from cURL`

---

## RabbitMQ UI

Access the RabbitMQ management UI to verify:

* **Exchange**: `sms_exchange`
* **Queue**: `sms_queue`
* **Binding**: With routing key `sms_routingKey`

URL: [http://localhost:15672](http://localhost:15672)

Login with:

* **Username**: `guest`
* **Password**: `guest`

---

## Requirements

* Java 21+
* Maven 3.6+
* Docker (for RabbitMQ)

---
## License

This project is licensed under the MIT License.
