package com.imgt.core.query.address;

import com.imgt.core.query.user.UserViewModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressViewModel {

    @Id
    private UUID id;

    private String city;

    private String state;

    private String postcode;

    @ManyToOne()
    private UserViewModel user;
}
