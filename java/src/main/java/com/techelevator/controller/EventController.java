package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.model.EventBandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    @Autowired
    private EventDao eventDao;

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{bandId}/events")
    public List<EventBandDto> getEventsByBandId(@PathVariable long bandId) {
        return eventDao.getEventsByEventHost(bandId);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-event")
    public void createEvent(@RequestBody @Valid EventBandDto eventBandDto) {
        eventDao.createEvent(eventBandDto);
    }

}
