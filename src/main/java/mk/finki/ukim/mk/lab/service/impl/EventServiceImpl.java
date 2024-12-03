package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.*;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;


    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        return eventRepository.findAllByNameContainingOrDescriptionContaining(text.toLowerCase(), text.toLowerCase());
    }


    @Override
    public List<Event> filterEvents(String searchName, String minRating) {
        return listAll().stream().
                filter(event ->
                        (searchName == null || searchName.isEmpty() || event.getName().contains(searchName)) &&
                                (minRating == null || minRating.isEmpty() || event.getPopularityScore() >= Double.parseDouble(minRating))).toList();

    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Transactional
    @Override
    public void addBooking(String eventName, int numberOfTickets) {
        Optional<Event> eventOptional = eventRepository.findByName(eventName);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();

            int updateCards = event.getAvailableCards() - numberOfTickets;

            if (updateCards < 0) {
                throw new EventHasNoAvailbleCards(event.getId());
            }
            event.setAvailableCards(updateCards);
            eventRepository.save(event);
        } else {
            throw new EventWithEventNameIsNotFound(eventName);
        }

    }

    @Override
    @Transactional
    public Event saveEvent(Event event) {
        if (event.getId() != null) {
            return eventRepository.save(event);
        } else {
            return eventRepository.save(event);
        }
    }

    @Override
    public Optional<Event> saveWithParams(String name, String description, double popularityScore, Long locationId, int availableCards) {
        Location location = locationRepository.findById(locationId).
                orElseThrow(() -> new InvalidLocationId(locationId));

        Event event = new Event(name, description, popularityScore, location, availableCards);
        return Optional.of(eventRepository.save(event));    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Event> remainingCards(Long id, int cardsTaken) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException(id);
        }
        Event event = eventOptional.get();

        if (event.getAvailableCards() < cardsTaken) {
            throw new EventHasNoAvailbleCards(event.getId());
        }
        event.setAvailableCards(event.getAvailableCards() - cardsTaken);
        eventRepository.save(event);

        return Optional.of(event);
    }

    @Override
    public List<Event> findAllByLocation_ID(Long locationID) {
        if (locationID == null) {
            throw new InvalidLocationId(locationID);
        }
        Optional<Location> location = locationRepository.findById(locationID);
        if (!location.isPresent()) {
            throw new LocationNotFoundException(locationID);
        }
        List<Event> events = eventRepository.findAllByLocation_Id(locationID);

        if (events.isEmpty()) {
            throw new LocationNotFoundException(locationID);
        }
        return events;
    }


}
