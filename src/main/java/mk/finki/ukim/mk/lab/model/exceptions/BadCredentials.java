package mk.finki.ukim.mk.lab.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadCredentials extends RuntimeException {
    public BadCredentials(String message) {
        super(String.format("Bad credentials !!!"));
    }
}
