package com.imgt.core.query.events;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Event {
    private UUID id;
    private UUID streamId;
    private String eventType;
    private String createdAt;
    private String data;
    private Long version;
}

