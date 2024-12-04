package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    List<Event> findAllByLocation_ID(Long locationID);

    void deleteById(Long id);

    public Optional<Event> findById(Long id);

    Event saveEvent(Event event);

    Optional<Event> saveWithParams(String name, String description, double popularityScore, Long location, int availableCards);
}
