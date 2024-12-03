package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.SavedBooking;

import java.util.List;
import java.util.Optional;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);

    List<SavedBooking> searchSavedBookings(String queryString);
}
