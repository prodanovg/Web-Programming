package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import  lombok.Data;

@Data
@AllArgsConstructor
public class SavedBooking {
    private String eventName;
    private int numberOfTickets;

}
