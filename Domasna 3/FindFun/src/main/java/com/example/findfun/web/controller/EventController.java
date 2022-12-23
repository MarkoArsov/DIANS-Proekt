package com.example.findfun.web.controller;


import com.example.findfun.model.Event;
import com.example.findfun.model.User;
import com.example.findfun.service.CommentService;
import com.example.findfun.service.EventService;
import com.example.findfun.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService service;
    private final UserService userService;
    private final CommentService commentService;

    public EventController(EventService service, UserService userService, CommentService commentService) {
        this.service = service;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String allEvents(Model model, Authentication authentication, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (authentication != null && user == null) {
            String username = authentication.getName();
            user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
            System.out.println("==============================================================="+user.getCreatedEvents().size()+"===============================================================");
        }
        model.addAttribute("searchText", "");
        model.addAttribute("sortText", "all");
        return "home";
    }

    @GetMapping("/{id}")
    public String eventById(@PathVariable Long id, Model model) {

        Event event = null;
        if (service.findById(id).isPresent()) {
            event = service.findById(id).get();
        }
        model.addAttribute("event", event);
        model.addAttribute("eventId", event.getId());
        model.addAttribute("eventDate", event.getDate());
        model.addAttribute("comments", event.getComments());
        return "event";
    }

    @GetMapping("/add")
    public String addEventPage() {
        return "createEvent";
    }

    @GetMapping("/invite/{id}")
    public String invitePage(@PathVariable Long id, HttpServletRequest request, Model model){

        Event event = service.findById(id).get();

        User user = (User) request.getSession().getAttribute("user");
        List<User> friends = user.getFriends();
        List<User> invitedFriends = event.getInvitedUsers();


        for(int i = 0; i < friends.size(); i++){
            for (User invitedFriend : invitedFriends) {
                if(friends.get(i).getUsername().equals(invitedFriend.getUsername())){
                    friends.remove(i);
                    i--;
                }
            }
        }
        model.addAttribute("notInvitedFriends", friends);
        model.addAttribute("invitedFriends", invitedFriends);
        model.addAttribute("eventId",id);

        return "invite";
    }
    @PostMapping("/add_friend")
    public String addFriend(@RequestParam Long id,
                            @RequestParam String friendUsername,HttpServletRequest request){
        Event event = service.findById(id).get();
        User friend = userService.findByUsername(friendUsername);
        service.addInvitedUserToEvent(event, friend);

        return "redirect:/events/invite/" + id;
    }

    @GetMapping("/sort/{sortText}")
    public String sort(@PathVariable String sortText, Model model){
        model.addAttribute("sortText", sortText);
        return "home";
    }

    @PostMapping("/add")
    public String addEvent(@RequestParam Double lat,
                           @RequestParam Double lng,
                           @RequestParam String name,
                           @RequestParam String about,
                           @RequestParam String category,
                           @RequestParam String imgPath,
                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                           HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        service.save(name, about, imgPath, lat, lng, date, user, category);

        request.getSession().setAttribute("user", userService.findByUsername(user.getUsername()));

        Long id = service.findAllByName(name).get(0).getId();

        return "redirect:/events/invite/" + id;
    }

    @PostMapping("/search")
    public String search(@RequestParam String text, Model model) {
        model.addAttribute("searchText", text);
        return "home";
    }

    @PostMapping("/comment")
    public String addComment(@RequestParam String comment,
                             @RequestParam Long eventID,
                             HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        Event event = service.findById(eventID).get();
        commentService.save(comment, user, event);
        return "redirect:/events/" + eventID.toString();
    }

    @GetMapping("/interested/{id}")
    public String interested(@PathVariable Long id,
                             HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Event event = null;
        if (service.findById(id).isPresent()) {
            event = service.findById(id).get();
        }

        service.addInterestedUserToEvent(event, user);

        request.getSession().setAttribute("user", userService.findByUsername(user.getUsername()));

        return "redirect:/events/" + id.toString();
    }

}
