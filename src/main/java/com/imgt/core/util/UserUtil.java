package com.imgt.core.util;

import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.command.domain.user.UserDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserUtil {

    public UserDto mergeUserData(UserDto currentData, UserDto newData) {
        if (newData.getNome() != null) {
            currentData.setNome(newData.getNome());
        }
        if (newData.getPhoneNumber() != null) {
            currentData.setPhoneNumber(newData.getPhoneNumber());
        }
        if (newData.getAddressDto() != null) {
            currentData.setAddressDto(newData.getAddressDto());
        }

        return currentData;
    }

    public UserDto addAddressToUser(UserDto user, AddressDto newAddress) {
        if(newAddress.getId() == null) {
            newAddress.setId(java.util.UUID.randomUUID());
        }
        user.setAddressDto(List.of(newAddress));
        return user;
    }

    public UserDto mergeUserAddresses(UserDto currentData, UserDto newData) {
        if (newData.getAddressDto() != null && !newData.getAddressDto().isEmpty()) {
            List<AddressDto> updatedAddresses = currentData.getAddressDto();
            updatedAddresses.addAll(newData.getAddressDto());
            currentData.setAddressDto(updatedAddresses);
        }
        return currentData;
    }
}
