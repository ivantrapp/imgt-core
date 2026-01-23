package com.imgt.core.query.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserViewModelService {

    private final UserViewModelRepository userViewModelRepository;

    @Autowired
    public UserViewModelService(UserViewModelRepository userViewModelRepository) {
        this.userViewModelRepository = userViewModelRepository;
    }

    public UserViewModelDto getUser(UUID id){
        UserViewModel userViewModel = userViewModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return UserViewModelMapper.entityToDto(userViewModel);
    }

    public UserViewModelDto getUserByNome(String nome){
        UserViewModel userViewModel = userViewModelRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Not found"));
        return UserViewModelMapper.entityToDto(userViewModel);
    }

}
