package com.challenge.mapper;

import com.challenge.dto.EventLogDTO;
import com.challenge.model.EventLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventLogMapper {
    EventLogDTO map(EventLog eventlog);
    List<EventLogDTO> map(List<EventLog> eventlogs);
}
