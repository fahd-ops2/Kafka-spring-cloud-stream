# Kafka and Spring Cloud Stream Demo

## Overview
This application is a demonstration of using Kafka with Spring Cloud Stream. It includes a REST controller acting as an event producer and multiple functional bindings for event consumption, polling, and transformation.

## Features
- **Event Producer**: Exposes a REST API to send events to a Kafka topic.
- **Event Consumer**: Listens for messages on a Kafka topic and processes them.
- **Supplier (Poller)**: Generates messages at scheduled intervals.
- **Function (Processor)**: Transforms incoming messages and sends them to another topic.

## Technology Stack
- **Spring Boot**
- **Spring Cloud Stream**
- **Apache Kafka**
- **Spring Boot Actuator**

## Prerequisites
- Java 17+
- Docker (for running Kafka via Docker Compose) or a Kafka broker installed locally
- Maven 3+

## Setting Up Kafka
If you do not have a running Kafka instance, you can use Docker:
```sh
version: '2'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
  kafka:
    image: confluentinc/cp-kafka:latest
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```
Start Kafka using:
```sh
docker-compose up -d
```

## Application Configuration
Modify `application.yml` for Kafka:
```yaml
spring:
  cloud:
    stream:
      bindings:
        producer-out-0:
          destination: my-topic
        consumer-in-0:
          destination: my-topic
          group: my-group
        supplier-out-0:
          destination: generated-messages
        function-in-0:
          destination: input-topic
        function-out-0:
          destination: output-topic
      kafka:
        binder:
          brokers: localhost:9092
```

## Running the Application
```sh
mvn spring-boot:run
```

## Testing
Send a message using:
```sh
curl -X POST http://localhost:8080/api/events/send -H "Content-Type: application/json" -d '"Hello Kafka"'
```
Check the logs for received messages.

## Conclusion
This demo showcases how to integrate Spring Cloud Stream with Kafka using producers, consumers, suppliers, and functions. You can expand this further by adding more advanced features like error handling and monitoring.

