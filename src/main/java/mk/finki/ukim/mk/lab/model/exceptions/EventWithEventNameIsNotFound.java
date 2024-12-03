package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventWithEventNameIsNotFound extends RuntimeException {
    public EventWithEventNameIsNotFound(String eventName) {
        super(String.format("Event with '" + eventName + "' is not found! "));

    }
}
