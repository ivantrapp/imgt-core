package com.imgt.core.query.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.query.user.UserProjection;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final ObjectMapper objectMapper;
    private final UserProjection userProjection;

    public Consumer(ObjectMapper objectMapper, UserProjection userProjection) {
        this.userProjection = userProjection;
        this.objectMapper = new ObjectMapper();
    }

    @SqsListener("imgt-core.fifo") // Replace with your actual SQS queue name
    public void receiveMessage(String message) {

        try {
            System.out.println("Received message: " + message);
            Event userEvents = objectMapper.readValue(message, Event.class);

            userProjection.handle(userEvents);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
