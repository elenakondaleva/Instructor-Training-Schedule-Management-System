package com.mint.training.domain.instructor;

import com.mint.training.domain.instructor.model.Instructor;

import java.util.List;

public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor getInstructor(final long instructorId) {
        return instructorRepository.getInstructor(instructorId);
    }

    public List<Instructor> getAll() {
        return instructorRepository.getAll();
    }
}
