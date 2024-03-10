package com.mint.training.infrastructure.schedule.model;

import com.mint.training.infrastructure.event.model.EventEntity;
import com.mint.training.infrastructure.instructor.model.InstructorEntity;
import com.mint.training.infrastructure.schedule.model.ScheduleEntity;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class SchedulePK implements Serializable {
    @OneToOne
    @JoinColumn(name = ScheduleEntity.Persistence.COLUMN_INSTRUCTOR_ID, referencedColumnName = InstructorEntity.Persistence.COLUMN_ID,
            foreignKey = @ForeignKey(name = "fk_schedule_instructor_1"), nullable = false)
    private InstructorEntity instructor;

    @OneToOne
    @JoinColumn(name = ScheduleEntity.Persistence.COLUMN_EVENT_ID, referencedColumnName = EventEntity.Persistence.COLUMN_ID,
            foreignKey = @ForeignKey(name = "fk_schedule_event_1"), nullable = false)
    private EventEntity event;

    public SchedulePK(InstructorEntity instructor, EventEntity event) {
        this.instructor = instructor;
        this.event = event;
    }

    public SchedulePK() {

    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
