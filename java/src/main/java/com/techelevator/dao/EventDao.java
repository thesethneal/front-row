package com.techelevator.dao;

import com.techelevator.model.EventBandDto;

import java.util.List;

public interface EventDao {

    List<EventBandDto> getAllEvents();
    List<EventBandDto> getEventsByEventHost(long eventHost);
    void createEvent(EventBandDto newEvent);

}
