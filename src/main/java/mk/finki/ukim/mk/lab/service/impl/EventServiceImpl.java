package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.SavedBooking;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public List<SavedBooking> getSavedBookings() {
        return eventRepository.getSavedBookings();
    }

    @Override
    public List<SavedBooking> searchSavedBookings(String queryString) {
        return eventRepository.getSavedBookings().stream().
                filter(booking -> booking.getEventName().toLowerCase().contains(queryString.toLowerCase())).
                collect(Collectors.toList());
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

    @Override
    public void addBooking(String eventName, int numberOfTickets) {
        eventRepository.addBooking(eventName, numberOfTickets);
    }

    @Override
    public Optional<Event> save(String name, String description, Double rating, Long locationId,int availbleCards) {
        return this.eventRepository.saveEvent(name,description,rating,locationId,availbleCards);

    }
    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> remainingCards(Long id, int cardsTaken) {
        return eventRepository.removeCards(id,cardsTaken);
    }
}
