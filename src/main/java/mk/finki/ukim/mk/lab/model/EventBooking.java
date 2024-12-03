package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class EventBooking {

    private String eventNameBooked;

    private String attendeeName;

    private String attendeeAddress;

    private Long numberOfTicketsBooked;


    private SavedBooking savedBooking;

    public EventBooking(String eventNameBooked, String attendeeName, String attendeeAddress, long numberOfTicketsBooked) {
        this.eventNameBooked = eventNameBooked;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTicketsBooked = numberOfTicketsBooked;
    }


}
