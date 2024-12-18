package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.EventHasSameLocationIdAndEventNumber;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//import static com.sun.beans.introspect.PropertyInfo.Name.description;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(Model model) {
        List<Event> listEvents = eventService.listAll();
        model.addAttribute("events", listEvents);
        return "listEvents";
    }

    @PostMapping("/searchLocations")
    public String searchLocations(@RequestParam(required = false) Long locationSearch, Model model) {
        try {
            List<Event> events = eventService.findAllByLocation_ID(locationSearch);
            model.addAttribute("events", events);
            return "listEvents";
        } catch (LocationNotFoundException ex) {
            model.addAttribute("events", ex.getMessage());
            return "redirect:/events?error=LocationNotFound";
        }

    }

    @GetMapping("/resetSearch")
    public String resetSearch(Model model) {
        return "redirect:/events";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam Long location,
            @RequestParam int availableCards
    ) {
        if (id != null) {
            Optional<Event> ExistingEvent = eventService.findById(id);
            if (ExistingEvent.isPresent()) {
                Event event = ExistingEvent.get();
                event.setName(name);
                event.setDescription(description);
                event.setPopularityScore(popularityScore);
                event.setLocation(locationService.findById(location).
                        orElseThrow(() -> new LocationNotFoundException(location)));
                event.setAvailableCards(availableCards);
                try {
                    eventService.saveEvent(event);
                } catch (EventHasSameLocationIdAndEventNumber ex) {
                    return "redirect:/events?error=EventHasSameLocationIdAndEventNumber";
                }

            } else {
                return "redirect:/events?error=EventNotFound";
            }
        } else {
            try {
                this.eventService.saveWithParams(name, description, popularityScore, location, availableCards);
            } catch (EventHasSameLocationIdAndEventNumber ex) {
                return "redirect:/events?error=EventHasSameLocationIdAndEventNumber";
            }
        }
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }


    @GetMapping("/add-form")
    public String showAddEventForm(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "addEvent";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEventPage(Model model, @PathVariable Long id) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locationList = locationService.findAll();
            model.addAttribute("locations", locationList);
            model.addAttribute("event", event);
            return "forward:/events/add-form?id=" + id;
        }
        return "redirect:/events?error=EventNotFound";
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("events", "access-denied");
        return "listEvents";
    }


}
