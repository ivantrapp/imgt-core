package com.imgt.core.command.domain.address;

import com.imgt.core.query.address.AddressViewModel;

public class AddressMapper {

    public static AddressDto toDTO(AddressViewModel address) {
        if (address == null) {
            return null;
        }
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        return dto;
    }
}
