package com.imgt.core.event;

import com.imgt.core.domain.user.UserDto;
import lombok.Data;

@Data
public class UserCommand {
    private UserDto userDto;

    private EventType eventType;
}
