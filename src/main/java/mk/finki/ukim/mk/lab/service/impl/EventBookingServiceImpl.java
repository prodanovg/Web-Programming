package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.SavedBooking;
import mk.finki.ukim.mk.lab.model.exceptions.NoSavedBookingsFoundException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventBookingServiceImpl implements EventBookingService {


    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);

    }

    @Override
    public List<SavedBooking> searchSavedBookings(String queryString) {
        return List.of();
    }

}