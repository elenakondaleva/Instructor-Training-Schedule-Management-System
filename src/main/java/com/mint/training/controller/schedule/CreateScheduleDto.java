package com.mint.training.controller.schedule;

import java.time.LocalDate;

public record CreateScheduleDto (long eventId, LocalDate startDate, LocalDate endDate) {
}
