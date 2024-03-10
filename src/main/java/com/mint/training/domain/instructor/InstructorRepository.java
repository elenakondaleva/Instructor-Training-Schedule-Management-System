package com.mint.training.domain.instructor;

import com.mint.training.domain.instructor.model.Instructor;

import java.util.List;

public interface InstructorRepository {
    Instructor getInstructor(long instructorId);

    List<Instructor> getAll();
}
