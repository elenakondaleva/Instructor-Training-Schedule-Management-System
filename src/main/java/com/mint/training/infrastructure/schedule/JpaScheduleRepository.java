package com.mint.training.infrastructure.schedule;

import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.instructor.model.Instructor;
import com.mint.training.domain.schedule.ScheduleRepository;
import com.mint.training.domain.schedule.model.MonthCalendar;
import com.mint.training.domain.schedule.model.Schedule;
import com.mint.training.infrastructure.event.model.EventEntity;
import com.mint.training.infrastructure.instructor.model.InstructorEntity;
import com.mint.training.infrastructure.schedule.model.ScheduleEntity;
import com.mint.training.infrastructure.schedule.model.SchedulePK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaScheduleRepository implements ScheduleRepository {

    private static final String SHEDULE_MONTH_SUMMARY
            = """
            select sum(datediff(least(s.endDate, concat(year(s.endDate), '-', :next, '-01')),
              greatest(s.startDate, concat(year(s.startDate), '-', :month, '-01'))))
            from ScheduleEntity s
            where (month(s.startDate) = :month and month(s.endDate) = :month or
                   month(s.startDate) < :month and month(s.endDate) = :month or
                   month(s.startDate) = :month and month(s.endDate) > :month or
                   month(s.startDate) < :month and month(s.endDate) > :month)
                   and s.scheduleId.instructor.instructorId = :instructor
            """;
    private static final String SHEDULE_MONTH
            = """
            select s from ScheduleEntity s
            join fetch s.scheduleId.instructor instructor
            join fetch s.scheduleId.event event
            where (month(s.startDate) = :month and month(s.endDate) = :month or
                month(s.startDate) < :month and month(s.endDate) = :month or
                month(s.startDate) = :month and month(s.endDate) > :month or
                month(s.startDate) < :month and month(s.endDate) > :month)
                and instructor.instructorId = :instructor
            order by s.startDate desc
        """;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Schedule eventByInstructor(long instructorId, long eventId) {
        ScheduleEntity entity = eventByInstructorEntity(instructorId, eventId);
        Optional.ofNullable(entity).orElseThrow(EntityNotFoundException::new);
        return asSchedule(entity);
    }

    private ScheduleEntity eventByInstructorEntity(long instructorId, long eventId) {
        InstructorEntity instructor = em.find(InstructorEntity.class, instructorId);
        EventEntity event = em.find(EventEntity.class, eventId);
        return em.find(ScheduleEntity.class, new SchedulePK(instructor, event));
    }
    @Override
    public List<Schedule> eventsByInstructorPerMonth(MonthCalendar month, long instructorId) {
        Query query = em.createQuery(SHEDULE_MONTH);
        query.setParameter("month", month.ordinal() + 1);
        query.setParameter("instructor", instructorId);
        List<ScheduleEntity> resultSet = query.getResultList();
        return transformScheduleList(resultSet);
    }

    @Override
    public int summaryEventsByInstructorPerMonth(MonthCalendar month, long instructorId) {
        Query query = em.createQuery(SHEDULE_MONTH_SUMMARY);
        query.setParameter("month", month.ordinal() + 1);
        query.setParameter("next", month.ordinal() + 2);
        query.setParameter("instructor", instructorId);
        Number result = (Number) query.getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    @Override
    @Transactional
    public Schedule createOrUpdateSchedule(Schedule schedule) {
        ScheduleEntity entity =  eventByInstructorEntity(schedule.getInstructor().getInstructorId(),
                schedule.getEvent().getEventId());
        if (entity == null) {
            entity = asScheduleEntity(schedule);
            em.persist(entity);
        }
        else {
            entity.setStartDate(schedule.getStartDate());
            entity.setEndDate(schedule.getEndDate());
        }
        return asSchedule(entity);
    }

    @Override
    @Transactional
    public void removeSchedule(long instructorId, long eventId) {
        ScheduleEntity entity =  eventByInstructorEntity(instructorId, eventId);
        Optional.ofNullable(entity).orElseThrow(EntityNotFoundException::new);
        em.remove(entity);
    }

    private List<Schedule> transformScheduleList(List<ScheduleEntity> scheduleList) {
        return scheduleList.stream().map(entity -> asSchedule(entity)).collect(Collectors.toList());
    }

    private Schedule asSchedule(ScheduleEntity input) {
        InstructorEntity instructor = input.getScheduleId().getInstructor();
        EventEntity event = input.getScheduleId().getEvent();
        return new Schedule(
                new Instructor(instructor.getInstructorId(), instructor.getFirstName(),
                        instructor.getLastName(), instructor.getBirthday()),
                new Event(event.getEventId(), event.getEvenType(), event.getDescription()),
                input.getStartDate(),
                input.getEndDate());
    }

    private ScheduleEntity asScheduleEntity(Schedule schedule) {
        Instructor instructor = schedule.getInstructor();
        InstructorEntity instructorEntity
                = new InstructorEntity(instructor.getInstructorId(),
                        instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getBirthday());
        Event event = schedule.getEvent();
        EventEntity eventEntity
                = new EventEntity(event.getEventId(), event.getEventType(), event.getDescription());
        return new ScheduleEntity(instructorEntity,
                eventEntity,
                schedule.getStartDate(),
                schedule.getEndDate());
    }
}
