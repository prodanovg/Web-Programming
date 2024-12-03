package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();

    @PostConstruct
    public void init() {
        locations.add(new Location("Central Park", "59th St to 110th St, New York, NY", "50,000 people", "A large public park in the heart of Manhattan, offering recreational activities, gardens, and cultural events.",new ArrayList<>()));
        locations.add(new Location("Millennium Stadium", "Westgate Street, Cardiff, Wales", "74,500 people", "A multi-purpose stadium hosting rugby, football, concerts, and other major events.",new ArrayList<>()));
        locations.add(new Location("The Louvre Museum", "Rue de Rivoli, 75001 Paris, France", "10,000 visitors per day", "One of the world’s largest and most visited art museums, home to thousands of works of art, including the Mona Lisa.",new ArrayList<>()));
        locations.add(new Location("Sydney Opera House", "Bennelong Point, Sydney, Australia", "5,738 people", "A globally recognized performing arts center known for its unique architecture and world-class performances.",new ArrayList<>()));
        locations.add(new Location("Tokyo Dome", "1-3-61 Koraku, Bunkyo City, Tokyo, Japan", "55,000 people", "A multi-purpose stadium primarily used for baseball games, concerts, and other entertainment events.",new ArrayList<>()));
        events.add(new Event("Concert", "A live music concert.", 3.5,locations.get(0),100));
        events.add(new Event("Art Exhibition", "Showcasing modern art.", 4.0,locations.get(1),10));
        events.add(new Event("Tech Conference", "Discussing the future of tech.", 5.0,locations.get(2),10));
        events.add(new Event("Food Festival", "A gathering of food lovers.", 4.8,locations.get(3),10));
        events.add(new Event("Book Fair", "Meet authors and buy books.", 2.3,locations.get(4),10));
        events.add(new Event("Marathon", "A city-wide marathon event.", 4.9,locations.get(0),5));
        events.add(new Event("Movie Premiere", "The latest blockbuster film.", 1.6,locations.get(1),5));
        events.add(new Event("Theater Play", "An engaging theater performance.", 3.7,locations.get(2),5));
        events.add(new Event("Music Festival", "A festival with various artists.", 4.8,locations.get(3),100));
        events.add(new Event("Networking Event", "Meet professionals and expand your network.", 4.2,locations.get(4),100));
    }
}
