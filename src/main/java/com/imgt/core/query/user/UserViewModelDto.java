package com.imgt.core.query.user;

import com.imgt.core.command.domain.address.AddressDto;
import com.imgt.core.query.address.AddressViewModelDto;
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
public class UserViewModelDto {

    private UUID uuid;
    private String nome;
    private String phoneNumber;
    private List<AddressViewModelDto> addressDto;
}
