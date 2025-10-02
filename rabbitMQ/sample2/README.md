## Running RabbitMQ with Docker
Run RabbitMQ with the Management Plugin on port `5672` (AMQP) and `15672` (HTTP UI):
```bash
sudo docker run -d --name rabbitmq -p 5672:15672 rabbitmq:3-management
```

* Management UI: [http://localhost:15672](http://localhost:15672)
* Default user/pass: `guest` / `guest`

# Configuration (`application.properties`)

Make sure your `application.properties` looks like this:

```properites
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

curl -X POST http://localhost:8080/api/registration/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "viarony",
    "email": "victorpaularony@gmail.com",
    "password": "password123"
  }'