package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EventBooking {
    @Id
    private String eventName;

    private String attendeeName;

    private String attendeeAddress;

    private Long numberOfTickets;

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }

//    //constructors
//    public EventBooking() {}
//
//    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
//        this.eventName = eventName;
//        this.attendeeName = attendeeName;
//        this.attendeeAddress = attendeeAddress;
//        this.numberOfTickets = numberOfTickets;
//    }
//
//
}
