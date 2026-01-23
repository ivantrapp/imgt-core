package com.imgt.core.query.address;

import com.imgt.core.command.domain.user.UserDto;
import com.imgt.core.query.user.UserViewModel;
import com.imgt.core.query.user.UserViewModelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressViewModelDto {

    private UUID id;
    private String city;
    private String state;
    private String postcode;
    private UserViewModelDto user;
}
