package com.example.DevStore.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida do RabbitMQ: " + mensagem);
    }
}
