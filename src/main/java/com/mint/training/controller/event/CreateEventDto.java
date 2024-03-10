package com.mint.training.controller.event;

import com.mint.training.domain.event.model.EventType;

public record CreateEventDto (EventType typeEvent, String description) {
}
