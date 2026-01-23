package com.imgt.core.command.domain.user;

import com.imgt.core.command.domain.address.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID uuid;
    private String nome;
    private String phoneNumber;
    private List<AddressDto> addressDto;
}
