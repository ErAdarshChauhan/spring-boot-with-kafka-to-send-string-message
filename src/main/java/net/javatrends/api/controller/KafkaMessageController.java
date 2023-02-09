package net.javatrends.api.controller;

import net.javatrends.api.kafka.KafkaConsumer;
import net.javatrends.api.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {

    private KafkaProducer kafkaProducer;
    private KafkaConsumer kafkaConsumer;

    public KafkaMessageController(KafkaProducer kafkaProducer, KafkaConsumer kafkaConsumer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sendMessageToTopic(message);
        return ResponseEntity.ok("Message sent by producer to Topic");
    }

    @GetMapping("/subscribe")
    public ResponseEntity<String> subscribed(){
        String subscribed = kafkaConsumer.subscribe();
        return ResponseEntity.ok("Message received by consumer from Topic -> "+subscribed);
    }

}
