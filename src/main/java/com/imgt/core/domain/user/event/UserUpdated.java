package com.imgt.core.domain.user.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.UserUtil;
import com.imgt.core.domain.user.UserDto;
import com.imgt.core.event.EventStore;
import com.imgt.core.event.EventStoreService;
import com.imgt.core.event.EventType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUpdated{

    public UserDto updateUser(UserDto currentUser, UserDto updatedUserData) {
        System.out.println("Updating user" + currentUser +  "with data: " + updatedUserData);

        currentUser = UserUtil.mergeUserData(currentUser, updatedUserData);

        return currentUser;
    }
}
