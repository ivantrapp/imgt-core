package com.imgt.core.query.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgt.core.command.aggregate.UserAggregate;
import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.command.event.EventStore;
import com.imgt.core.command.event.EventStoreService;
import com.imgt.core.query.address.AddressViewModel;
import com.imgt.core.query.address.AddressViewModelMapper;
import com.imgt.core.query.events.Event;
import com.imgt.core.util.UserUtil;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProjection {

    private final EventStoreService eventStoreService;
    private final UserViewModelRepository userViewModelRepository;
    private final ObjectMapper objectMapper;

    public UserProjection(EventStoreService eventStoreService, UserViewModelRepository userViewModelRepository, ObjectMapper objectMapper) {
        this.eventStoreService = eventStoreService;
        this.userViewModelRepository = userViewModelRepository;
        this.objectMapper = objectMapper;
    }

    public void handle(Event event) {
        switch (event.getEventType()) {
            case "USER_CREATED":
                handleUserCreatedEvent(event);
                break;
            case "USER_UPDATED":
                handleUserUpdatedEvent(event);
                break;
            case "ADDRESS_ADDED":
                handleAddressAddedEvent(event);
                break;
            case "ADDRESS_REMOVED":
                handleAddressRemovedEvent(event);
                break;
            default:
                throw new IllegalArgumentException("Unhandled event type: " + event.getEventType());
        }
    }

    public void handleUserCreatedEvent(Event event) {
        UserViewModelDto userDto = deserializeEventData(event.getData());

        UserViewModel userViewModel = UserViewModelMapper.DtoToEntity(userDto);

        userViewModelRepository.save(userViewModel);
    }

    public void handleUserUpdatedEvent(Event event) {
        UserViewModelDto userDto = deserializeEventData(event.getData());

        Optional<UserViewModel> existingUser = userViewModelRepository.findById(userDto.getUuid());
        if (existingUser.isPresent()) {
            UserViewModel updatedUser = UserViewModelUtil.mergeUserData(existingUser.get(), userDto);
            userViewModelRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found for update: " + userDto.getUuid());
        }
    }

    public void handleAddressAddedEvent(Event event) {
        UserViewModelDto userDto = deserializeEventData(event.getData());

        Optional<UserViewModel> existingUser = userViewModelRepository.findById(userDto.getUuid());

        if (existingUser.isPresent()) {
            UserViewModel userViewModel = existingUser.get();
            userViewModel.getAddress().addAll(userDto.getAddressDto().stream()
                    .map(AddressViewModelMapper::toEntity)
                    .toList());
            userViewModelRepository.save(userViewModel);
        } else {
            throw new RuntimeException("User not found for adding address: " + userDto.getUuid());
        }
    }

    public void handleAddressRemovedEvent(Event event) {
        UserViewModelDto userDto = deserializeEventData(event.getData());

        Optional<UserViewModel> existingUser = userViewModelRepository.findById(userDto.getUuid());

        if (existingUser.isPresent()) {
            List<AddressViewModel> addressViewModels = existingUser.get().getAddress().stream()
                    .filter(addressDto -> userDto.getAddressDto().stream().anyMatch(address -> Objects.equals(address.getId(), addressDto.getId()))).toList();
            existingUser.get().setAddress(addressViewModels);
            userViewModelRepository.save(existingUser.get());
        } else {
            throw new RuntimeException("User not found for removing address: " + userDto.getUuid());
        }
    }

    private UserViewModelDto deserializeEventData(String eventData) {
        try {
            return objectMapper.readValue(eventData, UserViewModelDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize event data", e);
        }
    }
}
