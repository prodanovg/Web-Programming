package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.exceptions.EventHasNoAvailbleCards;
import mk.finki.ukim.mk.lab.model.exceptions.EventWithEventNameIsNotFound;
import mk.finki.ukim.mk.lab.model.exceptions.NoSavedBookingsFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.EventBookingRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository, EventRepository eventRepository) {
        this.eventBookingRepository = eventBookingRepository;
        this.eventRepository = eventRepository;
    }

//    @Override
//    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
//        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
//
//    }

    @Override
    public List<EventBooking> getAllBookings() {
        return eventBookingRepository.findAll();
    }

    @Override
    @Transactional
    public void removeAllAndUpdateBookings() {
        List<EventBooking> eventBookings = eventBookingRepository.findAll();

        for (EventBooking booking : eventBookings) {
            Optional<Event> eventOptional = eventRepository.findByName(booking.getEventBookedName());

            if (eventOptional.isPresent()) {
                Event event = eventOptional.get();

                event.setAvailableCards((int) (event.getAvailableCards() + booking.getNumberOfTicketsBooked()));

                eventRepository.save(event);

                eventBookingRepository.delete(booking);
            }
        }

    }


    @Override
    public List<EventBooking> searchSavedBookings(String queryString) {
        return eventBookingRepository.findAll().stream()
                .filter(e -> e.getEventBookedName().toLowerCase().contains(queryString.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void addBooking(String eventName, int numberOfTickets) {
        Optional<Event> eventOptional = eventRepository.findByName(eventName);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();

            if (event.getAvailableCards() < numberOfTickets) {
                throw new EventHasNoAvailbleCards(event.getId());
            }

            event.setAvailableCards(event.getAvailableCards() - numberOfTickets);
            eventRepository.save(event);

            EventBooking eventBooking = new EventBooking();

            eventBooking.setEventBookedName(event.getName());
            eventBooking.setNumberOfTicketsBooked(Long.valueOf(numberOfTickets));
            eventBooking.setAttendeeName("Attendee Name");
            eventBooking.setAttendeeAddress("Attendee Address");

            eventBooking.setEvents(List.of(event));

            eventBookingRepository.save(eventBooking);

        } else {
            throw new EventWithEventNameIsNotFound(eventName);
        }

    }


}