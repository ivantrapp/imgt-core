package com.imgt.core.domain.user;

import com.imgt.core.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column
    private String nome;

    @Column
    private String phoneNumber;

    @OneToMany
    private List<Address> address;
}
