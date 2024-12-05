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
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }


    @Override
    @Transactional
    public Event saveEvent(Event event) {
        if(eventRepository.existsByNameAndLocation_Id(event.getName(),event.getLocation().getId())){
            throw new EventHasSameLocationIdAndEventNumber(event.getLocation().getId(), event.getName());
        }
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> saveWithParams(String name, String description, double popularityScore, Long locationId, int availableCards) {
        Location location = locationRepository.findById(locationId).
                orElseThrow(() -> new InvalidLocationId(locationId));

        Event event = new Event(name, description, popularityScore, location, availableCards);
        if(eventRepository.existsByNameAndLocation_Id(event.getName(),locationId)){
            throw new EventHasSameLocationIdAndEventNumber(locationId, event.getName());
        }
        return Optional.of(eventRepository.save(event));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new EventNotFoundException(id);
        }
    }


    @Override
    public List<Event> findAllByLocation_ID(Long locationID) {
        if (locationID == null) {
            throw new InvalidLocationId(locationID);
        }
        Optional<Location> location = locationRepository.findById(locationID);
        if (location.isEmpty()) {
            throw new LocationNotFoundException(locationID);
        }
        List<Event> events = eventRepository.findAllByLocation_Id(locationID);

        return events;
    }

}
