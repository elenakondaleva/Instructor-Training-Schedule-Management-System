package com.mint.training.controller.instructor;

import com.mint.training.domain.instructor.InstructorService;
import com.mint.training.domain.instructor.model.Instructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Instructor")
@Validated
@RestController
@RequestMapping(InstructorController.V1_INSTRUCTOR_URL)
public class InstructorController {
    public static final String V1_INSTRUCTOR_URL = "/v1/instructor";
    public static final String INSTRUCTOR_INFO = "/{instructorId}";
    public static final String INSTRUCTOR_ALL = "/getAll";
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Operation(summary = "Gets information about an instructor.",
            description = "Returns information details about an instructor with a given id.",
            operationId = "instructors.getInformation", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstructorDto.class))),
            @ApiResponse(responseCode = "404", description = "No instructor exists for the given id.",
                    content = @Content) })
    @GetMapping(INSTRUCTOR_INFO)
    public InstructorDto getInstructor(@PathVariable @Positive @Schema(example = "1") long instructorId) {
        Instructor instructor = instructorService.getInstructor(instructorId);
        return asInstructor(instructor);
    }

    @GetMapping(INSTRUCTOR_ALL)
    public List<InstructorDto> getAll() {
        List<Instructor> instructors = instructorService.getAll();
        return instructors.stream().map(item -> asInstructor(item)).collect(Collectors.toList());
    }

    private InstructorDto asInstructor(Instructor instructor) {
        return new InstructorDto(instructor.getInstructorId(),
                instructor.getFirstName(), instructor.getLastName(), instructor.getBirthday());
    }


}
