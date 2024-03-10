package com.mint.training.application;

import com.mint.training.controller.event.EventController;
import com.mint.training.domain.event.EventService;
import com.mint.training.domain.event.model.Event;
import com.mint.training.domain.event.model.EventType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(EventController.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    void getEventInfoShouldReturnOkStatus() throws Exception {
        Mockito.when(eventService.getEvent(Mockito.anyLong())).thenReturn(event());

        mockMvc.perform(MockMvcRequestBuilders.get(EventController.V1_EVENT_URL + "/{eventId}",1)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.eventType").value("TIME_OFF"))
                .andExpect(jsonPath("$.description").value("time-off one week"));
    }

    private Event event() {
        return new Event(1, EventType.TIME_OFF, "time-off one week");
    }

}
