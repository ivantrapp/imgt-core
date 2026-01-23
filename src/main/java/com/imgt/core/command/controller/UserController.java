package com.imgt.core.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imgt.core.command.aggregate.UserCommandHandler;
import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.command.domain.user.UserDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{userId}/removeAddress")
    public ResponseEntity<UserDto> removeAddress(@RequestBody AddressDto addressDto, @PathVariable UUID userId) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleRemoveAddressCommand(userId, addressDto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{userId}/addAddress")
    public ResponseEntity<UserDto> addAddress(@RequestBody AddressDto addressDto,@PathVariable UUID userId) throws JsonProcessingException {
        return new ResponseEntity<>(userCommandHandler.handleAddAddressCommand(userId, addressDto), HttpStatusCode.valueOf(200));
    }
}
