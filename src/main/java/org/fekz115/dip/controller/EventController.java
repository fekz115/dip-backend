package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Event;
import org.fekz115.dip.service.EventService;
import org.fekz115.dip.service.request.EventServiceRequests.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody CreateEventRequest request) {
        return eventService.createEvent(request);
    }

    @DeleteMapping("/{id}")
    public void removeEvent(@PathVariable int id) {
        eventService.removeEvent(new RemoveEventRequest(id));
    }

    @PatchMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody UpdateEventRequest request) {
        request.setEventId(id);
        return eventService.updateEvent(request);
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

}
