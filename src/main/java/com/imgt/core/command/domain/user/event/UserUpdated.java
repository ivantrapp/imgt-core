package com.imgt.core.command.domain.user.event;

import com.imgt.core.util.UserUtil;
import com.imgt.core.command.domain.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserUpdated {

    public UserDto updateUser(UserDto currentUser, UserDto updatedUserData) {
        System.out.println("Updating user" + currentUser + "with data: " + updatedUserData);

        currentUser = UserUtil.mergeUserData(currentUser, updatedUserData);

        return currentUser;
    }
}
