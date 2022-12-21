package com.example.findfun.service;

import com.example.findfun.model.Event;
import com.example.findfun.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> findAll();

    Optional<Event> findById(Long id);

    Optional<Event> save(String name, String about, String imgPath, Double lat, Double lng, LocalDateTime date, User createdUser, String category);

    List<Event> searchEvents(String text);

    List<Event> findAllByName(String name);

    void addInterestedUserToEvent(Event event, User user);
}
