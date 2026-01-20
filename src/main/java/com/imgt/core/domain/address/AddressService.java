package com.imgt.core.domain.address;

import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDto getAddressById(java.util.UUID id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toDTO)
                .orElse(null);
    }

    public AddressDto getAddressByPostcode(String postcode) {
        Address address = addressRepository.findByPostcode(postcode);
        if (address != null) {
            return AddressMapper.toDTO(address);
        }
        return null;
    }
}
