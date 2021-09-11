package com.ms.email.kafka;

import com.ms.email.model.Email;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.Map;

public class ConsumerKafka {

    public static void main(String[] args) throws IOException {
        ConsumerKafka consumerKafka = new ConsumerKafka();

        try(var kafkaService = new KafkaService<>(consumerKafka::parse,
                Map.of(),
                Email.class,
                "EMAIL_NEW_USER",
                ConsumerKafka.class.getSimpleName())){
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord consumerRecord) {
        System.out.println("----------------- Enviando email -------------------------");
        System.out.println("Topico: " + consumerRecord.topic());
        System.out.println("Chave: " +consumerRecord.key());
        System.out.println("Valor: " +consumerRecord.value());
    }


}
