package com.imgt.core.event;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventStoreService {

    private final EventStoreRepository eventStoreRepository;

    public EventStoreService(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    public EventStore saveEvent(EventStore eventStore) {
        if(eventStore.getVersion() == 1) {
            return eventStoreRepository.save(eventStore);
        }

        EventStore lastEvent = findByStreamIdOrderByVersionDesc(eventStore.getStreamId())
                .getFirst();

        eventStore.setVersion(lastEvent.getVersion() + 1);

        return eventStoreRepository.save(eventStore);
    }

    public List<EventStore> findByStreamIdOrderByVersionDesc(UUID streamId) {
        return eventStoreRepository.findByStreamIdOrderByVersionDesc(streamId);
    }

    public List<EventStore> findByStreamIdOrderByVersionAsc(UUID streamId) {
        return eventStoreRepository.findByStreamIdOrderByVersionAsc(streamId);
    }
}
