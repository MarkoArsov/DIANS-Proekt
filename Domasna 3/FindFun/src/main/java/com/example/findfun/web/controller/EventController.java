package com.example.findfun.web.controller;


import com.example.findfun.model.Event;
import com.example.findfun.model.User;
import com.example.findfun.service.EventService;
import com.example.findfun.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

}
