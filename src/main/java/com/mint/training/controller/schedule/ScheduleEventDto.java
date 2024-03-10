package com.mint.training.controller.schedule;

import com.mint.training.domain.event.model.EventType;

import java.time.LocalDate;

public class ScheduleEventDto
{
    private long instructorId;
    private long eventId;
    private EventType eventType;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public ScheduleEventDto(long instructorId, long eventId, EventType eventType, String description,
                            LocalDate startDate, LocalDate endDate) {
        this.eventId = eventId;
        this.instructorId = instructorId;
        this.eventType = eventType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
