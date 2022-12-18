package com.example.tehnickiprototip.service;

import com.example.tehnickiprototip.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> findAll();

    Optional<Event> findById(Long id);
}
