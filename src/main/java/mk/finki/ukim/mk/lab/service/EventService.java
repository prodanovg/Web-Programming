package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.SavedBooking;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

public interface EventService {
    List<Event> listAll();

    List<Event> searchEvents(String text);

    List<SavedBooking> getSavedBookings();

    List<Event> filterEvents(String searchName, String minRating);

    List<SavedBooking> searchSavedBookings(String queryString);

    public Optional<Event> save(String name, String description, Double rating, Long locationId,int availbleCards);

    void addBooking(String eventName, int numberOfTickets);

    void deleteById(Long id);

    public Optional<Event> remainingCards(Long id,int cardsTaken);

    public Optional<Event> findById(Long id);
}
