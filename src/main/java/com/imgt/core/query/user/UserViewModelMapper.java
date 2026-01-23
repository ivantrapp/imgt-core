package com.imgt.core.query.user;

import com.imgt.core.command.domain.address.AddressMapper;
import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.query.address.AddressViewModelMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserViewModelMapper {

    public UserViewModel DtoToEntity(UserViewModelDto userDto){
        if(userDto.getUuid() == null){
            userDto.setUuid(UUID.randomUUID());
        }

        return UserViewModel.builder()
                .uuid(userDto.getUuid())
                .nome(userDto.getNome())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddressDto() != null ? userDto.getAddressDto().stream()
                        .map(AddressViewModelMapper::toEntity)
                        .toList() : null)
                .build();
    }

    public UserViewModelDto entityToDto(UserViewModel user){
        return UserViewModelDto.builder()
                .uuid(user.getUuid())
                .nome(user.getNome())
                .phoneNumber(user.getPhoneNumber())
                .addressDto(user.getAddress() != null ? user.getAddress().stream()
                        .map(AddressViewModelMapper::toDTO)
                        .toList() : List.of())
                .build();
    }
}
