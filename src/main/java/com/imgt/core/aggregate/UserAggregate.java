package com.imgt.core.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.domain.user.UserDto;
import com.imgt.core.domain.user.event.UserAddAddress;
import com.imgt.core.domain.user.event.UserCreated;
import com.imgt.core.domain.user.event.UserRemoveAddress;
import com.imgt.core.domain.user.event.UserUpdated;
import com.imgt.core.event.EventStore;
import com.imgt.core.event.EventType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAggregate {

    private final ObjectMapper objectMapper;
    private final UserCreated userCreated;
    private final UserUpdated userUpdated;
    private final UserAddAddress userAddAddress;
    private final UserRemoveAddress userRemoveAddress;

    public UserAggregate(ObjectMapper objectMapper, UserCreated userCreated,
                         UserUpdated userUpdated,
                         UserAddAddress userAddAddress,
                         UserRemoveAddress userRemoveAddress) {
        this.objectMapper = objectMapper;
        this.userCreated = userCreated;
        this.userUpdated = userUpdated;
        this.userAddAddress = userAddAddress;
        this.userRemoveAddress = userRemoveAddress;
    }

    public UserDto apply(EventType event, UserDto currentUser, UserDto userDto) {
        System.out.println("Applying event to UserAggregate: " + event);

        switch (event) {
            case USER_CREATED -> {
                userDto = userCreated.createUser(userDto);
            }
            case USER_UPDATED -> {
                userDto = userUpdated.updateUser(currentUser, userDto);
            }
            case ADDRESS_ADDED -> {
                userDto = userAddAddress.createAddress(currentUser, userDto);
            }
            case ADDRESS_REMOVED -> {
                userDto = userRemoveAddress.removeAddress(currentUser, userDto);
            }
            default -> System.out.println("Unknown event type: " + event);
        }

        return userDto;
    }

    public UserDto rehydrateUserEvents(List<EventStore> events, UUID streamId) {
        System.out.println("Rehydrating user events for streamId: " + streamId);

        UserDto userDto = new UserDto();

        for (EventStore event : events) {
            System.out.println("Applying event: " + event);

            userDto = apply(event.getEventType(), userDto, parseEventData(event.getData()));
        }

        return userDto;
    }

    private UserDto parseEventData(String data) {
        try {
            return objectMapper.readValue(data, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse event data", e);
        }
    }
}
