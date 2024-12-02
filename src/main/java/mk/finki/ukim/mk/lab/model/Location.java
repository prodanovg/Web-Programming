package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Location(String name, String address, String capacity, String description) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }
}
