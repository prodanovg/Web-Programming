package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventHasNoAvailbleCards extends RuntimeException {
    public EventHasNoAvailbleCards(Long eventId) {
        super(String.format("Event with id %s has no more availble cards", eventId));
    }
}




