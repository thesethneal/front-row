package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventBandDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private JdbcTemplate template;

    public JdbcEventDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<EventBandDto> getAllEvents() {
        List<EventBandDto> events = new ArrayList<>();

        String sql = "SELECT event_id, event_time, event_venue, event_address, " +
                "event_host, event_name, b.band_name FROM events e " +
                "JOIN bands b ON e.event_host = b.band_id;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                events.add(mapRowToEventBandDto(results));
            }
            for (EventBandDto event : events) {
                long eventHost = event.getEvent().getEventHost();
                event.setBandName(getBandNameByEventHost(eventHost));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return events;
    }

    @Override
    public List<EventBandDto> getEventsByEventHost(long eventHost) {
        List<EventBandDto> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE event_host = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, eventHost);
            while (results.next()) {
                events.add(mapRowToEventBandDto(results));
            }
            for (EventBandDto event : events) {
                event.setBandName(getBandNameByEventHost(eventHost));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return events;
    }

    @Override
    public void createEvent(EventBandDto newEvent) {
        long newEventId = addEvent(newEvent);

    }



// Private methods
    private String getBandNameByEventHost(long eventHost) {
        String bandName = null;

        String sql = "SELECT band_name FROM bands WHERE band_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, eventHost);
            if (results.next()) {
                bandName = results.getString("band_name");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bandName;

    }

    private Event mapRowToEvent(SqlRowSet rowSet) {
        Event event = new Event();

        event.setEventId(rowSet.getLong("event_id"));
        event.setEventTime((rowSet.getTimestamp("event_time")).toLocalDateTime());
        event.setEventVenue(rowSet.getString("event_venue"));
        event.setEventAddress(rowSet.getString("event_address"));
        event.setEventHost(rowSet.getLong("event_host"));
        event.setEventName(rowSet.getString("event_name"));

        return event;
    }


    private EventBandDto mapRowToEventBandDto(SqlRowSet rowSet) {
        EventBandDto eventBandDto = new EventBandDto();
        eventBandDto.setEvent(mapRowToEvent(rowSet));
        return eventBandDto;
    }

    private long getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?;";
        long userId = -1;

        try {
            userId = template.queryForObject(sql, new Object[]{username}, Long.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems 2");
        }
        return userId;
    }

    private long addEvent(EventBandDto newEvent) {
        long newEventId = -1;
        String sql = "INSERT INTO events (event_time, event_venue, event_address, " +
                "event_host, event_name) " +
                "VALUES (?,?,?,?,?) " +
                "RETURNING event_id;";

        try {
            newEventId = template.queryForObject(sql, new Object[] {
                    newEvent.getEvent().getEventTime(),
                    newEvent.getEvent().getEventVenue(),
                    newEvent.getEvent().getEventAddress(),
                    newEvent.getEvent().getEventHost(),
                    newEvent.getEvent().getEventName()}, Integer.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return newEventId;
    }

}
