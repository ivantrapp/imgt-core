package com.imgt.core.query.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressViewModelRepository extends JpaRepository<AddressViewModel, UUID> {

    AddressViewModel findByPostcode(String postcode);
}
