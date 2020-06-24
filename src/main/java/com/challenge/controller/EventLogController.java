package com.challenge.controller;

import com.challenge.dto.EventLogDTO;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.model.EventLog;
import com.challenge.service.EventService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/event_log")
public class EventLogController {

    @Autowired
    private EventService eventService;

    @ApiOperation("Search all logs using predicate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping
    public Page<EventLogDTO> getFiltered(@QuerydslPredicate(root = EventLog.class) Predicate predicate, @PageableDefault(size = 5) Pageable pageable) {
        return eventService.findAll(predicate, pageable);
    }

    @ApiOperation("Insert a EventLog")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successful Operation")})
    @PostMapping
    public ResponseEntity<EventLog> save(@RequestBody @Valid EventLog eventLog){
        return new ResponseEntity<EventLog>(this.eventService.saveEvent(eventLog), HttpStatus.CREATED);
    }

    @ApiOperation("Search a log by its ID")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Element not found"),@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping("/{id}")
    public ResponseEntity<EventLog> findById(@PathVariable("id") Long id){
        return new ResponseEntity<EventLog>(this.eventService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Element not found")), HttpStatus.OK);
    }
}
