package com.imgt.core.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.command.event.EventType;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.UUID;

@Component
public class Producer {
    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.sns.topic.arn-fifo}")
    private String topicArnFifo;

    public Producer(SnsClient snsClient, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
    }

    public void publish(Object message, String eventType, String messageGroupId) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);

            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArnFifo)
                    .message(jsonMessage)
                    .subject(eventType)
                    .messageGroupId(messageGroupId)
                    .messageDeduplicationId(UUID.randomUUID().toString())
                    .build();

            snsClient.publish(request);

        } catch (Exception e) {
            throw new RuntimeException("Failed to publish event", e);
        }
    }
}
