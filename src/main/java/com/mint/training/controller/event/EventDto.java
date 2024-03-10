package com.mint.training.controller.event;

import com.mint.training.domain.event.model.EventType;

public record EventDto(long eventId, EventType eventType, String description){

}
