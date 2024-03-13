package com.mint.training.controller.schedule;

import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.schedule.ScheduleService;
import com.mint.training.domain.schedule.model.CreateSchedule;
import com.mint.training.domain.schedule.model.MonthCalendar;
import com.mint.training.domain.schedule.model.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Schedule")
@Validated
@RestController
@RequestMapping(ScheduleController.V1_SCHEDULE_URL)
public class ScheduleController {
    public static final String V1_SCHEDULE_URL = "/v1/schedule";
    public static final String SCHEDULE_BY_INSTRUCTOR = "/{month}/{instructorId}";
    public static final String SUMMARY_SCHEDULE_BY_INSTRUCTOR = "/{month}/{instructorId}/summary";
    public static final String SCHEDULE_BY_INSTRUCTOR_EVENT = "/{instructorId}/{eventId}";
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(SCHEDULE_BY_INSTRUCTOR)
    public List<ScheduleEventDto> getScheduleByInstructor(@PathVariable @Schema(example = "JANUARY") String month,
                                                           @PathVariable @Positive @Schema(example = "1") long instructorId) {
        List<Schedule> schedules = scheduleService
                .eventsByInstructorPerMonth(MonthCalendar.valueOf(month), instructorId);
        return schedules.stream().map(item -> asScheduleEventDto(item)).collect(Collectors.toList());
    }

    @GetMapping(SUMMARY_SCHEDULE_BY_INSTRUCTOR)
    public int getSummuryScheduleByInstructor(@PathVariable @Schema(example = "JANUARY") String month,
                                       @PathVariable @Positive @Schema(example = "1") long instructorId) {
        return scheduleService.summaryEventsByInstructorPerMonth(MonthCalendar.valueOf(month), instructorId);
    }

    @GetMapping(SCHEDULE_BY_INSTRUCTOR_EVENT)
    public ScheduleDto getScheduleByInstructorByEvent(@PathVariable @Positive @Schema(example = "1") long instructorId,
                                @PathVariable @Positive @Schema(example = "1") long eventId) {
        Schedule schedule = scheduleService.eventByInstructor(instructorId, eventId);
        return asSchedule(schedule);
    }

    @PostMapping(SCHEDULE_BY_INSTRUCTOR_EVENT)
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleDto createOrUpdateSchedule(@PathVariable @Positive @Schema(example = "1") long instructorId,
                                @NotNull @Valid @RequestBody CreateScheduleDto scheduleDto) {
        CreateSchedule newSchedule = CreateSchedule.builder()
                .withInstructorId(instructorId)
                .withEventId(scheduleDto.eventId())
                .withStartDate(scheduleDto.startDate())
                .withEndDate(scheduleDto.endDate())
                .build();
        Schedule schedule = scheduleService.createOrUpdateShedule(newSchedule);
        return asSchedule(schedule);
    }

    @DeleteMapping(SCHEDULE_BY_INSTRUCTOR_EVENT)
    public void removeSchedule(@PathVariable @Positive @Schema(example = "1") long instructorId,
                                @PathVariable @Positive @Schema(example = "1") long eventId) {
        scheduleService.removeSchedule(instructorId, eventId);
    }
    private ScheduleDto asSchedule(Schedule input){
        return new ScheduleDto(input.getInstructor().getInstructorId(), input.getEvent().getEventId(),
                input.getStartDate(), input.getEndDate());
    }

    private ScheduleEventDto asScheduleEventDto(Schedule input){
        Event event = input.getEvent();
        return new ScheduleEventDto(input.getInstructor().getInstructorId(), event.getEventId(), event.getEventType(),
                event.getDescription(), input.getStartDate(), input.getEndDate());
    }

}
