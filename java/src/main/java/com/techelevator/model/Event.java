package com.techelevator.model;

import java.time.LocalDateTime;

public class Event {

    private long eventId;
    private LocalDateTime eventTime;
    private String eventVenue;
    private String eventAddress;
    private long eventHost;
    private String eventName;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public long getEventHost() {
        return eventHost;
    }

    public void setEventHost(long eventHost) {
        this.eventHost = eventHost;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
