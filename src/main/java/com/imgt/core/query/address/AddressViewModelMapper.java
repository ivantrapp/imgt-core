package com.imgt.core.query.address;

import com.imgt.core.command.domain.address.AddressDto;

public class AddressViewModelMapper {

    public static AddressViewModelDto toDTO(AddressViewModel address) {
        if (address == null) {
            return null;
        }
        AddressViewModelDto dto = new AddressViewModelDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        return dto;
    }

    public static AddressViewModel toEntity(AddressViewModelDto dto) {
        if (dto == null) {
            return null;
        }
        AddressViewModel address = new AddressViewModel();
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        return address;
    }
}
