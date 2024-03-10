package com.mint.training.domain.schedule;

import com.mint.training.domain.event.EventService;
import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.instructor.InstructorService;
import com.mint.training.domain.instructor.model.Instructor;
import com.mint.training.domain.schedule.model.CreateSchedule;
import com.mint.training.domain.schedule.model.MonthCalendar;
import com.mint.training.domain.schedule.model.Schedule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final InstructorService instructorService;
    private final EventService eventService;


    public ScheduleService(ScheduleRepository scheduleRepository, InstructorService instructorService, EventService eventService) {
        this.scheduleRepository = scheduleRepository;
        this.instructorService = instructorService;
        this.eventService = eventService;
    }

    public List<Schedule> eventsByInstructorPerMonth(MonthCalendar month, long instructorId) {
        return scheduleRepository.eventsByInstructorPerMonth(month, instructorId);
    }

    public int summaryEventsByInstructorPerMonth(MonthCalendar month, long instructorId) {
        return scheduleRepository.summaryEventsByInstructorPerMonth(month, instructorId);
    }
    public Schedule eventByInstructor(long instructorId, long eventId) {
        return scheduleRepository.eventByInstructor(instructorId, eventId);
    }
    @Transactional
    public Schedule createOrUpdateShedule(final CreateSchedule newSchedule) {
        Instructor instructor = instructorService.getInstructor(newSchedule.instructorId());
        Event event = eventService.getEvent(newSchedule.eventId());
        Schedule schedule = new Schedule(instructor, event, newSchedule.startDate(), newSchedule.endDate());
        return scheduleRepository.createOrUpdateSchedule(schedule);
    }

    public void removeSchedule(long instructorId, long eventId) {
        scheduleRepository.removeSchedule(instructorId, eventId);
    }
}
