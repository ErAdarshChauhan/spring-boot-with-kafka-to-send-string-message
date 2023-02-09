package net.javatrends.api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    public static final String GROUP_ID = "group_id";

    String msg = null;

    @KafkaListener(topics="javatrends",groupId = GROUP_ID)
    public void consume(String message){
        LOGGER.info(String.format("Message received %s",message));
        msg = message;
    }

    public String subscribe(){
        return msg;
    }
}
