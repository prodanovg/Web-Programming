package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventBookedName;

    private String attendeeName;

    private String attendeeAddress;

    private Long numberOfTicketsBooked;

    @OneToMany(mappedBy = "eventBooking")
    private List<Event> events;


    public EventBooking(String eventBookedName, String attendeeName, String attendeeAddress, long numberOfTicketsBooked) {
        this.eventBookedName = eventBookedName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTicketsBooked = numberOfTicketsBooked;
    }


}
