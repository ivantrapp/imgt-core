package com.imgt.core.command.event;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EventStore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private OffsetDateTime createdAt;

    private UUID streamId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String data;

    private EventType eventType;

    private Long version;
}
