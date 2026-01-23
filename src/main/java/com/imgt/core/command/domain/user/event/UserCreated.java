package com.imgt.core.command.domain.user.event;

import com.imgt.core.command.domain.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserCreated {

    public UserDto createUser(UserDto userDto) {
        System.out.println("Creating user with data: " + userDto);

        userDto.setUuid(Objects.isNull(userDto.getUuid()) ? UUID.randomUUID() : userDto.getUuid());

        return userDto;
    }
}
