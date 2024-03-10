package com.mint.training.infrastructure.instructor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = InstructorEntity.Persistence.TABLE_INSTRUCTOR)
public class InstructorEntity implements Serializable {
    public static final class Persistence {
        public static final String TABLE_INSTRUCTOR = "instructor";
        public static final String COLUMN_ID = "instructor_id";
        private static final String COLUMN_FIRST_NAME = "first_name";
        private static final String COLUMN_LAST_NAME = "last_name";

        private static final String COLUMN_BIRTHDAY = "birthday";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_ID)
    private long instructorId;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_FIRST_NAME, nullable = false)
    private String firstName;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_LAST_NAME, nullable = false)
    private String lastName;

    @NotBlank
    @Column(name = Persistence.COLUMN_BIRTHDAY, nullable = false)
    private LocalDate birthday;

    public InstructorEntity() {
    }

    public InstructorEntity(long instructorId, String firstName, String lastName, LocalDate birthday) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }
}
