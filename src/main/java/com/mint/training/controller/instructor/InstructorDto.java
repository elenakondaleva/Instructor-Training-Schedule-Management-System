package com.mint.training.controller.instructor;

import java.time.LocalDate;

public record InstructorDto(long instructorId, String firstName, String lastName, LocalDate birthday) {
}
