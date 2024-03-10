package com.mint.training.domain.event.model;

public class Event {
    private long eventId;
    private EventType eventType;
    private String description;

    public Event() {
    }

    public Event(long eventId, EventType eventType, String description) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.description = description;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(final long eventId) {
        this.eventId = eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(final EventType eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
