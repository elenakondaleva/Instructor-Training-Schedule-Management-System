package com.mint.training.controller.event;

import com.mint.training.domain.event.EventService;
import com.mint.training.domain.event.model.CreateEvent;
import com.mint.training.domain.event.model.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Event")
@Validated
@RestController
@RequestMapping(EventController.V1_EVENT_URL)
public class EventController {

    public static final String V1_EVENT_URL = "/v1/event";
    public static final String EVENT_INFO = "/{eventId}";
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Gets information about an event.",
            description = "Returns information details about an event with a given eventId.",
            operationId = "Events.getInformation", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404", description = "No event exists for the given eventId.",
                    content = @Content) })
    @GetMapping(EVENT_INFO)
    public EventDto getEvent(@PathVariable @Positive @Schema(example = "1") long eventId) {
        Event event = eventService.getEvent(eventId);
        return asEvent(event);
    }

    @PostMapping()
    public EventDto addEvent(CreateEventDto event) {
        Event newEvent = eventService.addEvent(
                new CreateEvent(event.typeEvent(), event.description()));
        return asEvent(newEvent);
    }

    @DeleteMapping()
    public void addEvent(long eventId) {
        eventService.removeEvent(eventId);
    }

    private EventDto asEvent(Event event) {
        return new EventDto(event.getEventId(), event.getEventType(), event.getDescription());
    }
}
