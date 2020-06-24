package com.challenge.service;

import com.challenge.dto.EventLogDTO;
import com.challenge.model.EventLog;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    EventLog saveEvent(EventLog eventLog);
    List<EventLog> findAll(Pageable pageable);
    Page<EventLogDTO> findAll(Predicate predicate, Pageable pageable);
    Optional<EventLog> findById(Long id);
}
