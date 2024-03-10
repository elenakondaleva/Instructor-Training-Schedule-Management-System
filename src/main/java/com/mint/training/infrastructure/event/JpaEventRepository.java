package com.mint.training.infrastructure.event;

import com.mint.training.controller.event.CreateEventDto;
import com.mint.training.domain.event.EventRepository;
import com.mint.training.domain.event.model.CreateEvent;
import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.event.model.EventType;
import com.mint.training.infrastructure.event.model.EventEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaEventRepository implements EventRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public Event getEvent(long eventId) {
        return Optional.ofNullable(em.find(EventEntity.class, eventId))
                .map(this::asEvent)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Event addEvent(Event event) {
        EventEntity entity = asEventEntity(event);
        em.persist(entity);
        return asEvent(entity);
    }

    @Override
    public void removeEvent(long eventId) {
        EventEntity event = Optional.ofNullable(em.find(EventEntity.class, eventId))
                .orElseThrow(EntityNotFoundException::new);
        em.remove(event);
    }

    public Event asEvent(final EventEntity input) {
        return new Event(input.getEventId(), input.getEvenType(), input.getDescription());
    }

    public EventEntity asEventEntity(final Event input) {
        EventEntity newEvent = new EventEntity();
        newEvent.setEvenType(input.getEventType());
        newEvent.setDescription(input.getDescription());
        return newEvent;
    }
}
