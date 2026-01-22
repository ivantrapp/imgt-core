package com.imgt.core.domain.user.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.domain.address.AddressDto;
import com.imgt.core.domain.user.UserDto;
import com.imgt.core.event.Event;
import com.imgt.core.event.EventStoreService;
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

        return currentUser;
    }

    private static boolean canRemoveAddress(UserDto currentUser) {
        return currentUser.getAddressDto() != null && !currentUser.getAddressDto().isEmpty();
    }
}
