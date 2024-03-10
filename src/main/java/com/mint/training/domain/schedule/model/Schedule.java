package com.mint.training.domain.schedule.model;

import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.instructor.model.Instructor;

import java.time.LocalDate;

public class Schedule {
    private Instructor instructor;
    private Event event;
    private LocalDate startDate;
    private LocalDate endDate;

    public Schedule(Instructor instructor, Event event, LocalDate startDate, LocalDate endDate) {
        this.instructor = instructor;
        this.event = event;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(final Instructor instructor) {
        this.instructor = instructor;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }
}
