package com.mint.training.domain.instructor.model;

import java.time.LocalDate;
import java.util.Date;

public class Instructor {
    private long instructorId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Instructor(long instructorId, String firstName, String lastName, LocalDate birthday) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(final long instructorId) {
        this.instructorId = instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
