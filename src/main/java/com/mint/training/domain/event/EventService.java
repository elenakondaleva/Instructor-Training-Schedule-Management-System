package com.mint.training.domain.event;

import com.mint.training.domain.event.model.CreateEvent;
import com.mint.training.domain.event.model.Event;
import org.springframework.transaction.annotation.Transactional;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getEvent(final long id) {
        return eventRepository.getEvent(id);
    }

    @Transactional
    public Event addEvent(final CreateEvent event) {
        Event newEvent = new Event();
        newEvent.setEventType(event.getEventType());
        newEvent.setDescription(event.getDescription());
        return eventRepository.addEvent(newEvent);
    }

    public void removeEvent(final long eventId) {
        eventRepository.removeEvent(eventId);
    }
}
