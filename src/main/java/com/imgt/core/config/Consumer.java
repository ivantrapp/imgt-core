package com.imgt.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private ObjectMapper objectMapper;

    public Consumer(ObjectMapper objectMapper) {
        this.objectMapper = new ObjectMapper();
    }

    @SqsListener("imgt-core.fifo") // Replace with your actual SQS queue name
    public void receiveMessage(String message) {

        try {
            System.out.println("Received message: " + message);
//            UserCommand userCommand = objectMapper.convertValue(message, UserCommand.class);
//            System.out.println("Processed UserCommand: " + userCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
