package com.imgt.core.domain.user;

import com.imgt.core.aggregate.UserAggregate;
import com.imgt.core.event.EventStore;
import com.imgt.core.event.EventStoreRepository;
import com.imgt.core.event.EventStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserProjection {

    private final EventStoreService eventStoreService;
    private final UserAggregate userAggregate;

    public UserProjection(EventStoreService eventStoreService,
                          UserAggregate userAggregate) {
        this.eventStoreService = eventStoreService;
        this.userAggregate = userAggregate;
    }

    public UserDto projectUser(String streamId) {
        System.out.println("Projecting user with streamId: " + streamId);

        List<EventStore> events = eventStoreService.findByStreamIdOrderByVersionAsc(UUID.fromString(streamId));

//        for (EventStore event : events) {
//            System.out.println("Processing event: " + event);
//            userAggregate.apply(event.getEventType(), event.getData());
//        }

        return new UserDto();
    }
}
