package com.imgt.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imgt.core.aggregate.UserCommandHandler;
import com.imgt.core.domain.address.AddressDto;
import com.imgt.core.domain.user.UserDto;
import com.imgt.core.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserCommandHandler userCommandHandler;

    public UserController(UserCommandHandler userCommandHandler) {
        this.userCommandHandler = userCommandHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleCreateUserCommand(user), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleUpdateUserCommand(user), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/removeAddress")
    public ResponseEntity<UserDto> removeAddress(@RequestBody AddressDto addressDto, UUID userId) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleRemoveAddressCommand(userId, addressDto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addAddress")
    public ResponseEntity<UserDto> addAddress(@RequestBody AddressDto addressDto, UUID userId) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleAddAddressCommand(userId, addressDto), HttpStatusCode.valueOf(200));
    }
}
