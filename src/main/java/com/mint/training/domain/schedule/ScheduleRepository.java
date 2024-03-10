package com.mint.training.domain.schedule;

import com.mint.training.domain.schedule.model.MonthCalendar;
import com.mint.training.domain.schedule.model.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> eventsByInstructorPerMonth(MonthCalendar month, long instructorId);

    int summaryEventsByInstructorPerMonth(MonthCalendar month, long instructorId);

    Schedule eventByInstructor(long instructorId, long eventId);

    Schedule createOrUpdateSchedule(Schedule schedule);

    void removeSchedule(long instructorId, long eventId);
}
