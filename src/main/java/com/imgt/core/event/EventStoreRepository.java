package com.imgt.core.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventStoreRepository extends CrudRepository<EventStore, UUID>{

    List<EventStore> findByStreamIdOrderByVersionDesc(UUID streamId);

    List<EventStore> findByStreamIdOrderByVersionAsc(UUID streamId);
}
