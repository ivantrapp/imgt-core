package com.imgt.core.command.domain.user.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.command.event.EventStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddAddress {

    private final ObjectMapper objectMapper;
    private final EventStoreService eventStoreService;

    public UserAddAddress(ObjectMapper objectMapper, EventStoreService eventStoreService) {
        this.objectMapper = objectMapper;
        this.eventStoreService = eventStoreService;
    }

    public UserDto createAddress(UserDto currentUser, UserDto newAddressData) {
        System.out.println("Adding address with data: " + newAddressData);

        if (canAddNewAddress(currentUser, newAddressData)) {
            List<AddressDto> updatedAddresses = currentUser.getAddressDto();
            updatedAddresses.addAll(newAddressData.getAddressDto());
            currentUser.setAddressDto(updatedAddresses);
        } else {
            currentUser.setAddressDto(newAddressData.getAddressDto());
        }

        System.out.println("Updated user after adding address: " + currentUser);
        return currentUser;
    }

    private static boolean canAddNewAddress(UserDto currentUser, UserDto newAddressData) {
        return currentUser.getAddressDto() != null && !currentUser.getAddressDto().isEmpty() && newAddressData.getAddressDto() != null && !newAddressData.getAddressDto().isEmpty();
    }
}
