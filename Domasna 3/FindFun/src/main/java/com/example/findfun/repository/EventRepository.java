package com.example.findfun.repository;

import com.example.findfun.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();

    Optional<Event> findById(Long id);

}
