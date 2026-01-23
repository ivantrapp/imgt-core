package com.imgt.core.query.user;

import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.query.address.AddressViewModelMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class UserViewModelUtil {

    public UserViewModel mergeUserData(UserViewModel currentData, UserViewModelDto newData) {
        if (newData.getNome() != null) {
            currentData.setNome(newData.getNome());
        }
        if (newData.getPhoneNumber() != null) {
            currentData.setPhoneNumber(newData.getPhoneNumber());
        }
        if (newData.getAddressDto() != null) {
            currentData.setAddress(!newData.getAddressDto().isEmpty() ?
                    newData.getAddressDto().stream().map(AddressViewModelMapper::toEntity).toList() : List.of());
        }

        return currentData;
    }

    public UserDto addAddressToUser(UserDto user, AddressDto newAddress) {
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
