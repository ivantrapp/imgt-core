package com.imgt.core.command.domain.user.event;

import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.command.domain.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserRemoveAddress {

    public UserDto removeAddress(UserDto currentUser, UserDto removeAddressData) {
        System.out.println("Removing address with data: " + removeAddressData);

        if (canRemoveAddress(currentUser)) {
            List<AddressDto> updatedAddresses = currentUser.getAddressDto().stream()
                    .filter(addrress -> Objects.equals(addrress.getId(), removeAddressData.getAddressDto().getFirst().getUser()))
                    .toList();
            currentUser.setAddressDto(updatedAddresses);
        }

        System.out.println("Updated user after removing address: " + currentUser);
        return currentUser;
    }

    private static boolean canRemoveAddress(UserDto currentUser) {
        return currentUser.getAddressDto() != null && !currentUser.getAddressDto().isEmpty();
    }
}
