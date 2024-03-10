package com.mint.training.infrastructure.event.model;

import com.mint.training.domain.event.model.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = EventEntity.Persistence.TABLE_EVENT)
public class EventEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_EVENT = "event";
        public static final String COLUMN_ID = "event_id";
        private static final String COLUMN_TYPE = "event_type";
        private static final String COLUMN_DESCRIPTION = "description";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_ID)
    private long eventId;

    @Column(name = Persistence.COLUMN_TYPE, nullable = false)
    private EventType evenType;

    @NotBlank
    @Column(name = Persistence.COLUMN_DESCRIPTION, nullable = false)
    private String description;

    public EventEntity() {
    }

    public EventEntity(long eventId, EventType eventType, String description) {
        this.eventId = eventId;
        this.evenType = eventType;
        this.description = description;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(final long eventId) {
        this.eventId = eventId;
    }


    public EventType getEvenType() {
        return evenType;
    }

    public void setEvenType(EventType evenType) {
        this.evenType = evenType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
