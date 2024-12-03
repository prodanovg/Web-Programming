package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String capacity;

    @Column(length = 4000)
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Event> events;


    public Location(String name, String address, String capacity, String description,List<Event> events) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
        this.events = events;
    }
}
