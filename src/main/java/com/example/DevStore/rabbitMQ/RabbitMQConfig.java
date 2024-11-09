package com.example.DevStore.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "pedidoQueue";

    @Bean
    public Queue pedidoQueue() {
        return new Queue(QUEUE_NAME, true); // true significa que a fila é durável
    }
}