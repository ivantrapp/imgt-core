package com.imgt.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto){
        User user = userRepository.save(UserMapper.DtoToEntity(userDto));

        return UserMapper.entityToDto(user);
    }
}
