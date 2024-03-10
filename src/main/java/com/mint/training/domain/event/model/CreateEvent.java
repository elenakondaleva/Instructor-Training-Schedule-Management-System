package com.mint.training.domain.event.model;

public class CreateEvent {
    private EventType eventType;
    private String description;

    public CreateEvent(EventType eventType, String description) {
        this.eventType = eventType;
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
