package com.mint.training.infrastructure.schedule.model;

import com.mint.training.infrastructure.event.model.EventEntity;
import com.mint.training.infrastructure.instructor.model.InstructorEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = ScheduleEntity.Persistence.TABLE_SHEDULE)
public class ScheduleEntity implements Serializable {
    public static final class Persistence {
        public static final String TABLE_SHEDULE = "schedule";
        public static final String COLUMN_INSTRUCTOR_ID = "instructor_id";
        public static final String COLUMN_EVENT_ID = "event_id";
        private static final String COLUMN_START_DATE = "start_date";
        private static final String COLUMN_END_DATE = "end_date";
    }

    public ScheduleEntity() {
    }

    @EmbeddedId
    private SchedulePK scheduleId;

    @Temporal(TemporalType.DATE)
    @Column(name = Persistence.COLUMN_START_DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = Persistence.COLUMN_END_DATE)
    private LocalDate endDate;

    public ScheduleEntity(InstructorEntity instructor, EventEntity event, LocalDate startDate, LocalDate endDate) {
        this.scheduleId = new SchedulePK(instructor, event);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SchedulePK getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(SchedulePK scheduleId) {
        this.scheduleId = scheduleId;
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
