package com.mint.training.domain.event;

import com.mint.training.domain.event.model.CreateEvent;
import com.mint.training.domain.event.model.Event;

public interface EventRepository {
    Event getEvent(long eventId);

    Event addEvent(Event event);

    void removeEvent(long eventId);
}
