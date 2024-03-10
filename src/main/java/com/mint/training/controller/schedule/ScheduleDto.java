package com.mint.training.controller.schedule;

import com.mint.training.domain.event.model.EventType;

import java.time.LocalDate;

public record ScheduleDto (long instructorId, long eventId, LocalDate startDate, LocalDate endDate) {
}
