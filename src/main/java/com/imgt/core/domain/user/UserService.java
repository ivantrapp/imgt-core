package com.imgt.core.domain.user;

import com.imgt.core.domain.address.AddressDto;
import com.imgt.core.domain.address.AddressMapper;
import com.imgt.core.domain.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository,
                       AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public UserDto createUser(UserDto userDto){
        User user = userRepository.save(UserMapper.DtoToEntity(userDto));

        return UserMapper.entityToDto(user);
    }

    public void updateUser(UUID id, UserDto updateInfo){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        user.setPhoneNumber(updateInfo.getPhoneNumber());

        userRepository.save(user);
    }

    public UserDto getUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return UserMapper.entityToDto(user);
    }

    public UserDto addAddressToUser(UUID userId, AddressDto addressDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getAddress().add(AddressMapper.toEntity(addressDto));
        userRepository.save(user);
        return UserMapper.entityToDto(user);
    }

    public void removeAddressFromUser(UUID userId, String postCode) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getAddress().removeIf(address -> address.getPostcode().equals(postCode));
        userRepository.save(user);
    }
}
