package com.imgt.core.query.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserViewModelRepository extends JpaRepository<UserViewModel, UUID> {

    Optional<UserViewModel> findByNome(String nome);
}
