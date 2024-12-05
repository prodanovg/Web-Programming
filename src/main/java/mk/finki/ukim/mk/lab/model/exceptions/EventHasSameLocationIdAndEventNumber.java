package mk.finki.ukim.mk.lab.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventHasSameLocationIdAndEventNumber extends RuntimeException {
    public EventHasSameLocationIdAndEventNumber(Long locationId, String eventName) {
        super(String.format("Event with location id %s and event id",locationId,eventName));
    }
}
