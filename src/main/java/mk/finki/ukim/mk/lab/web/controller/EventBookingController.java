package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.exceptions.EventNotFoundException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.ui.Model;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events/eventBooking")
public class EventBookingController {

    private final EventService eventService;
    private final EventBookingService eventBookingService;

    public EventBookingController(EventService eventService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String eventBooking(@RequestParam(required = false) String bookingSearch, Model model) {
        List<EventBooking> bookingList = eventBookingService.getAllBookings();

        if (bookingSearch != null && !bookingSearch.isEmpty()) {
            bookingList = eventBookingService.searchSavedBookings(bookingSearch);
        }

        model.addAttribute("savedBookingList", bookingList);
        return "bookingConfirmation";
    }

    @PostMapping("/add")
    public String addBooking(@RequestParam int numTickets,
                             @RequestParam Long eventId, Model model) {
        try {
            Optional<Event> Optional_event = eventService.findById(eventId);

            if(Optional_event.isEmpty()) {
                throw new EventNotFoundException(eventId);
            }
            Event event = Optional_event.get();

            eventBookingService.addBooking(event.getName(), numTickets);

            model.addAttribute("eventName", event.getName());
            model.addAttribute("numOfTickets", numTickets);

            model.addAttribute("savedBookingList", eventBookingService.getAllBookings());

            return "bookingConfirmation";
        } catch (Exception e) {
            return "redirect:/events?error=BookingFailed";
        }
    }

    @GetMapping("/removeAllBookedEvents")
    public String removeAllBookedEvents(Model model) {
        eventBookingService.removeAllAndUpdateBookings();

        return "redirect:/events/eventBooking";

    }
    @GetMapping("/goBackToEvents")
    public String goBackToEvents(Model model) {
        List<Event> listEvents = eventService.listAll();
        model.addAttribute("events", listEvents);
        return "redirect:/events";

    }
    @GetMapping("/GoToEventBookings")
    public String goToEventBookings() {
        return "redirect:/events/eventBooking";
    }
}
