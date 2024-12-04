package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService {
//    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);

    void addBooking(String eventName, int numberOfTickets);

    List<EventBooking> getAllBookings();

    void removeAllAndUpdateBookings();

    List<EventBooking> searchSavedBookings(String queryString);
}
