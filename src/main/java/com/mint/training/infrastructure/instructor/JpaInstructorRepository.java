package com.mint.training.infrastructure.instructor;

import com.mint.training.domain.instructor.InstructorRepository;
import com.mint.training.domain.instructor.model.Instructor;
import com.mint.training.infrastructure.instructor.model.InstructorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaInstructorRepository implements InstructorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Instructor getInstructor(long instructorId) {
        return Optional.ofNullable(em.find(InstructorEntity.class, instructorId))
                .map(entity -> asInstructor(entity))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Instructor> getAll() {
        return em.createQuery("select i from InstructorEntity i", InstructorEntity.class)
                .getResultList().stream().map(entity -> asInstructor(entity)).collect(Collectors.toList());
    }

    public Instructor asInstructor(final InstructorEntity input) {
        return new Instructor(input.getInstructorId(), input.getFirstName(), input.getLastName(), input.getBirthday());
    }
}
