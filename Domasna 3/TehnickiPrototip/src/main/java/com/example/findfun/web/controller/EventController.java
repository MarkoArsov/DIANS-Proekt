package com.example.tehnickiprototip.web.controller;


import com.example.tehnickiprototip.model.Event;
import com.example.tehnickiprototip.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public String allEvents(Model model) {
        return "home";
    }

    @GetMapping("/{id}")
    public String eventById(@PathVariable Long id, Model model){

        Event event = null;
        if (service.findById(id).isPresent()){
            event = service.findById(id).get();
        }
        model.addAttribute("event", event);
        model.addAttribute("eventId", event.getId());
        return "event";
    }

}
