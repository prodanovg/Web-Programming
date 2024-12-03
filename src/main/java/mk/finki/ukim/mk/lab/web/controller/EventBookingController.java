package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.SavedBooking;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

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
    public String eventBooking(HttpServletRequest req, Model model) {
        String queryString = req.getParameter("bookingSearch");
        List<SavedBooking> bookingsToSend = eventBookingService.searchSavedBookings(queryString);


        model.addAttribute("savedBookingList", bookingsToSend);
        return "bookingConfirmation";
    }
    @PostMapping("/add")
    public String eventBookingConfirmation(HttpServletRequest req, Model model) {
//        List<SavedBooking> savedBookingList = eventBookingService.getSavedBookings();

        Long eventId = Long.valueOf(req.getParameter("rad"));
        int numOfTickets = Integer.parseInt(req.getParameter("numTickets"));
        String eventName = eventService.findById(eventId).get().getName();

        if(numOfTickets <= eventService.findById(eventId).get().getAvailableCards()){
            eventService.addBooking(eventName, numOfTickets);
            eventService.remainingCards(eventId, numOfTickets);
            model.addAttribute("hostName", req.getRemoteHost());
            model.addAttribute("hostAddress", req.getRemoteAddr());
            model.addAttribute("eventName", eventName);
            model.addAttribute("numOfTickets", numOfTickets);
//            model.addAttribute("savedBookingList", savedBookingList);
            return "bookingConfirmation";
        }
        else {
            return "redirect:/events?error=EventNotFound";
        }



    }
}
