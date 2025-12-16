package com.imgt.core.domain.user;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User DtoToEntity(UserDto userDto){
        return User.builder()
                .uuid(userDto.getUuid())
                .nome(userDto.getNome())
                .build();
    }

    public UserDto entityToDto(User user){
        return UserDto.builder()
                .uuid(user.getUuid())
                .nome(user.getNome())
                .build();
    }
}
