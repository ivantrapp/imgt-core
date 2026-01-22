package com.imgt.core.domain.user;

import com.imgt.core.domain.address.AddressMapper;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UserMapper {

    public User DtoToEntity(UserDto userDto){
        if(userDto.getUuid() == null){
            userDto.setUuid(UUID.randomUUID());
        }

        return User.builder()
                .uuid(userDto.getUuid())
                .nome(userDto.getNome())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddressDto() != null ? userDto.getAddressDto().stream()
                        .map(AddressMapper::toEntity)
                        .toList() : null)
                .build();
    }

    public UserDto entityToDto(User user){
        return UserDto.builder()
                .uuid(user.getUuid())
                .nome(user.getNome())
                .phoneNumber(user.getPhoneNumber())
                .addressDto(user.getAddress() != null ? user.getAddress().stream()
                        .map(AddressMapper::toDTO)
                        .toList() : null)
                .build();
    }
}
