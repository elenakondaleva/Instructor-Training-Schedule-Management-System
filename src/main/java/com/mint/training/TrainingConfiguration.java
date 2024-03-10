package com.mint.training;

import com.mint.training.domain.event.EventRepository;
import com.mint.training.domain.event.EventService;
import com.mint.training.domain.instructor.InstructorRepository;
import com.mint.training.domain.instructor.InstructorService;
import com.mint.training.domain.schedule.ScheduleRepository;
import com.mint.training.domain.schedule.ScheduleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainingConfiguration {
    @Bean
    public InstructorService instructorService(InstructorRepository repository) {
        return new InstructorService(repository);
    }

    @Bean
    public EventService eventService(EventRepository repository) {
        return new EventService(repository);
    }

    @Bean
    public ScheduleService scheduleService(ScheduleRepository repository,
                                           InstructorRepository instructorRepository,
                                           EventRepository eventRepository) {
        return new ScheduleService(repository, instructorService(instructorRepository), eventService(eventRepository));
    }

}
