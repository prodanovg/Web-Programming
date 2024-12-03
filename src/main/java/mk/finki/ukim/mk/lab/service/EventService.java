package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    List<Event> searchEvents(String text);


    List<Event> filterEvents(String searchName, String minRating);

    List<Event> findAllByLocation_ID(Long locationID);
//    public Optional<Event> save(String name, String description, Double rating, Long locationId, int availbleCards);

    void addBooking(String eventName, int numberOfTickets);

    void deleteById(Long id);

    public Optional<Event> remainingCards(Long id, int cardsTaken);

    public Optional<Event> findById(Long id);

    Event saveEvent(Event event);

    Optional<Event> saveWithParams(String name, String description, double popularityScore, Long location, int availableCards);
}
