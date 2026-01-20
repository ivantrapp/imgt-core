package com.imgt.core.domain.address;

import com.imgt.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private UUID id;
    private String city;
    private String state;
    private String postcode;
    private User user;
}
