package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 4000)
    private String description;

    private double popularityScore;

    @ManyToOne
    private Location location;

    private int availableCards;

    @ManyToOne
    private EventBooking eventBooking;

    public Event(String name, String description, double popularityScore,Location location,int availableCards) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.availableCards = availableCards;
    }

}
