package com.imgt.core.command.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.util.UserUtil;
import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.config.Producer;
import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.command.event.EventStore;
import com.imgt.core.command.event.EventStoreService;
import com.imgt.core.command.event.EventType;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class UserCommandHandler {

    private final UserAggregate userAggregate;
    private final EventStoreService eventStoreService;
    private final ObjectMapper objectMapper;
    private final Producer producer;

    public UserCommandHandler(UserAggregate userAggregate, EventStoreService eventStoreService, ObjectMapper objectMapper, Producer producer) {
        this.userAggregate = userAggregate;
        this.eventStoreService = eventStoreService;
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public UserDto handleCreateUserCommand(UserDto data) throws JsonProcessingException {
        UserDto user = userAggregate.apply(EventType.USER_CREATED, new UserDto(), data);

        System.out.println("User created with : " + user);
        EventStore eventStore = EventStore.builder()
                .createdAt(OffsetDateTime.now())
                .eventType(EventType.USER_CREATED)
                .streamId(user.getUuid())
                .version(1L)
                .data(objectMapper.writeValueAsString(user))
                .build();

        eventStoreService.saveEvent(eventStore);

        producer.publish(eventStore, EventType.USER_CREATED.name(), user.getUuid() + EventType.USER_CREATED.name());

        return user;

    }

    public UserDto handleUpdateUserCommand(UserDto data) throws JsonProcessingException {
        List<EventStore> events = eventStoreService.findByStreamIdOrderByVersionAsc(data.getUuid());
        UserDto currentUser = userAggregate.rehydrateUserEvents(events, data.getUuid());

        UserDto updatedUser = userAggregate.apply(EventType.USER_UPDATED, currentUser, data);

        EventStore eventStore = EventStore.builder()
                .createdAt(java.time.OffsetDateTime.now())
                .eventType(EventType.USER_UPDATED)
                .streamId(data.getUuid())
                .version(events.getLast().getVersion() + 1)
                .data(objectMapper.writeValueAsString(updatedUser))
                .build();

        eventStoreService.saveEvent(eventStore);

        producer.publish(eventStore, EventType.USER_UPDATED.name(), updatedUser.getUuid() + EventType.USER_UPDATED.name());

        return updatedUser;
    }

    public UserDto handleAddAddressCommand(UUID userId, AddressDto data) throws JsonProcessingException {
        List<EventStore> events = eventStoreService.findByStreamIdOrderByVersionAsc(userId);
        UserDto currentUser = userAggregate.rehydrateUserEvents(events, userId);

        UserDto userWithNewAddress = UserUtil.addAddressToUser(new UserDto(), data);

        UserDto updatedUser = userAggregate.apply(EventType.ADDRESS_ADDED, currentUser, userWithNewAddress);

        EventStore eventStore = EventStore.builder()
                .createdAt(java.time.OffsetDateTime.now())
                .eventType(EventType.ADDRESS_ADDED)
                .streamId(userId)
                .version(events.getLast().getVersion() + 1)
                .data(objectMapper.writeValueAsString(updatedUser))
                .build();

        eventStoreService.saveEvent(eventStore);

        producer.publish(eventStore, EventType.ADDRESS_ADDED.name(), userId + EventType.ADDRESS_ADDED.name());

        return updatedUser;
    }

    public UserDto handleRemoveAddressCommand(UUID userId, AddressDto data) throws JsonProcessingException {
        List<EventStore> events = eventStoreService.findByStreamIdOrderByVersionAsc(userId);
        UserDto currentUser = userAggregate.rehydrateUserEvents(events, userId);

        UserDto userWithRemovedAddress = UserUtil.addAddressToUser(new UserDto(), data);

        UserDto updatedUser = userAggregate.apply(EventType.ADDRESS_REMOVED, currentUser, userWithRemovedAddress);

        EventStore eventStore = EventStore.builder()
                .createdAt(java.time.OffsetDateTime.now())
                .eventType(EventType.ADDRESS_REMOVED)
                .streamId(userId)
                .version(events.getLast().getVersion() + 1)
                .data(objectMapper.writeValueAsString(updatedUser))
                .build();

        eventStoreService.saveEvent(eventStore);

        producer.publish(eventStore, EventType.ADDRESS_REMOVED.name(), userId + EventType.ADDRESS_REMOVED.name());

        return updatedUser;
    }
}
