package com.example.tehnickiprototip.service.impl;

import com.example.tehnickiprototip.model.Event;
import com.example.tehnickiprototip.repository.EventRepository;
import com.example.tehnickiprototip.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }
}
