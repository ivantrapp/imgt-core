package com.imgt.core.domain.user;

import com.imgt.core.domain.address.AddressMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User DtoToEntity(UserDto userDto){
        return User.builder()
                .uuid(userDto.getUuid())
                .nome(userDto.getNome())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddressDtoList() != null ? userDto.getAddressDtoList().stream()
                        .map(AddressMapper::toEntity)
                        .toList() : null)
                .build();
    }

    public UserDto entityToDto(User user){
        return UserDto.builder()
                .uuid(user.getUuid())
                .nome(user.getNome())
                .phoneNumber(user.getPhoneNumber())
                .addressDtoList(user.getAddress() != null ? user.getAddress().stream()
                        .map(AddressMapper::toDTO)
                        .toList() : null)
                .build();
    }
}
