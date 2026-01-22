package com.imgt.core.domain.user.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.domain.user.User;
import com.imgt.core.domain.user.UserDto;
import com.imgt.core.domain.user.UserMapper;
import com.imgt.core.event.EventStore;
import com.imgt.core.event.EventStoreRepository;
import com.imgt.core.event.EventStoreService;
import com.imgt.core.event.EventType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserCreated {

    private final ObjectMapper objectMapper;
    private final EventStoreService  eventStoreService;

    public UserCreated(ObjectMapper objectMapper, EventStoreService eventStoreService) {
        this.objectMapper = objectMapper;
        this.eventStoreService = eventStoreService;
    }

    public UserDto createUser(UserDto userDto) {
        System.out.println("Creating user with data: " + userDto);

        User user = UserMapper.DtoToEntity(userDto);

        return UserMapper.entityToDto(user);
    }
}
