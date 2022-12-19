package com.example.findfun.web.rest;


import com.example.findfun.model.Event;
import com.example.findfun.model.RestEvent;
import com.example.findfun.service.EventService;
import jdk.javadoc.doclet.Doclet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    private final EventService service;

    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<RestEvent> findAll(){
        List<RestEvent> restEvents = new ArrayList<>();
        List<Event> events = service.findAll();
        events.forEach(event -> restEvents.add(new RestEvent(event)));
        return restEvents;
    }

    @GetMapping("/search/{text}")
    public List<RestEvent> search(@PathVariable String text){
        if (text==null || text.isEmpty()){
            findAll();
        }
        List<RestEvent> restEvents = new ArrayList<>();
        List<Event> events = service.searchEvents(text);
        events.forEach(event -> restEvents.add(new RestEvent(event)));
        return restEvents;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestEvent> findById(@PathVariable Long id) {
        Event event = service.findById(id).get();
        RestEvent restEvent = new RestEvent(event);
        return Optional.of(restEvent)
                .map(e-> ResponseEntity.ok().body(e))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


}
