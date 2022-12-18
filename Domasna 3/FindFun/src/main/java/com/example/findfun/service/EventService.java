package com.example.findfun.service;

import com.example.findfun.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> findAll();

    Optional<Event> findById(Long id);
}
