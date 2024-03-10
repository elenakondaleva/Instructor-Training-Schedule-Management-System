package com.mint.training.domain.schedule.model;

import java.time.LocalDate;

public class CreateSchedule {

    private final long instructorId;
    private final long eventId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public CreateSchedule(Builder builder) {
        this.instructorId = builder.instructorId;
        this.eventId = builder.eventId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }
    public static final class Builder {
        private long instructorId;
        private long eventId;
        private LocalDate startDate;
        private LocalDate endDate;

        private Builder() {

        }

        public Builder withInstructorId(long instructorId) {
            this.instructorId = instructorId;
            return this;
        }

        public Builder withEventId(long eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }
        public CreateSchedule build() {
            return new CreateSchedule(this);
        }

    }
    public static Builder builder() {
        return new Builder();
    }

    public long instructorId() {
        return instructorId;
    }

    public long eventId() {
        return eventId;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }
}
