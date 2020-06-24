package com.challenge.mapper;

import com.challenge.dto.EventLogDTO;
import com.challenge.model.EventLog;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-24T01:52:38-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class EventLogMapperImpl implements EventLogMapper {

    @Override
    public EventLogDTO map(EventLog eventlog) {
        if ( eventlog == null ) {
            return null;
        }

        EventLogDTO eventLogDTO = new EventLogDTO();

        eventLogDTO.setId( eventlog.getId() );
        eventLogDTO.setLevel( eventlog.getLevel() );
        eventLogDTO.setDescription( eventlog.getDescription() );
        eventLogDTO.setSource( eventlog.getSource() );
        if ( eventlog.getQuantity() != null ) {
            eventLogDTO.setQuantity( eventlog.getQuantity().longValue() );
        }

        return eventLogDTO;
    }

    @Override
    public List<EventLogDTO> map(List<EventLog> eventlogs) {
        if ( eventlogs == null ) {
            return null;
        }

        List<EventLogDTO> list = new ArrayList<EventLogDTO>( eventlogs.size() );
        for ( EventLog eventLog : eventlogs ) {
            list.add( map( eventLog ) );
        }

        return list;
    }
}
