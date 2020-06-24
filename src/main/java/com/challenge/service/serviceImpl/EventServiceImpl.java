package com.challenge.service.serviceImpl;

import com.challenge.dto.EventLogDTO;
import com.challenge.mapper.EventLogMapper;
import com.challenge.model.EventLog;
import com.challenge.repository.EventLogRepository;
import com.challenge.service.EventService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private EventLogRepository eventLogRepository;
    @Autowired
    private EventLogMapper eventLogMapper;

    @Override
    public EventLog saveEvent(EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }

    @Override
    public List<EventLog> findAll(Pageable pageable){
        return eventLogRepository.findAll(pageable).getContent();
    }

    @Override
    public Page<EventLogDTO> findAll(Predicate predicate, Pageable pageable) {
        Page<EventLog> logPage = eventLogRepository.findAll(predicate,pageable);
        List<EventLogDTO> eventLogDTO = logPage.get().map(eventLogMapper::map).collect(Collectors.toList());
        return new PageImpl<EventLogDTO>(eventLogDTO, logPage.getPageable(), logPage.getSize());
    }

    @Override
    public Optional<EventLog> findById(Long id) {
        return eventLogRepository.findById(id);
    }
}
