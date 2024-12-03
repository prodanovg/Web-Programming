package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidLocationId extends IllegalArgumentException{
    public InvalidLocationId(Long id) {
        super(String.format("Invalid location id %s", id));
    }
}
