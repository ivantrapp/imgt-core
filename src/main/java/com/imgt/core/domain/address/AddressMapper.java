package com.imgt.core.domain.address;

public class AddressMapper {

    public static AddressDto toDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        return dto;
    }

    public static Address toEntity(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        return address;
    }
}
