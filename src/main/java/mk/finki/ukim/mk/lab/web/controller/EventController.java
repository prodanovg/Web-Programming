package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.ui.Model;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sun.beans.introspect.PropertyInfo.Name.description;

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
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        List<Event> listEvents = eventService.listAll();
        model.addAttribute("events", listEvents);
        return "listEvents";
    }

    @PostMapping("/add")
    public String saveEvent(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam Long location,
            @RequestParam int availbleCards
    ) {
        this.eventService.save(name, description, popularityScore, location,availbleCards);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
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
    public String editEventPage(Model model, @PathVariable Long id) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locationList = locationService.findAll();
            model.addAttribute("locations", locationList);
            model.addAttribute("event", event);
            return "addEvent";
        }
        return "redirect:/events?error=EventNotFound";
    }
}
