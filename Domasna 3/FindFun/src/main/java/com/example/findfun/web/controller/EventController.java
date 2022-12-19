package com.example.findfun.web.controller;


import com.example.findfun.model.Event;
import com.example.findfun.model.User;
import com.example.findfun.service.EventService;
import com.example.findfun.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService service;
    private final UserService userService;

    public EventController(EventService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public String allEvents(Model model, Authentication authentication, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (authentication != null && user == null){
            String username = authentication.getName();
            user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
        }
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

    @GetMapping("/add")
    public String addEventPage(){
        return "createEvent";
    }

    @PostMapping("/add")
    public String addEvent(@RequestParam Double lat,
                           @RequestParam Double lng,
                           @RequestParam String name,
                           @RequestParam String about,
                           @RequestParam String category,
                           @RequestParam String imgPath,
                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                           HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        service.save(name, about, imgPath, lat, lng, date, user, category);

        return "redirect:/events";
    }
}
