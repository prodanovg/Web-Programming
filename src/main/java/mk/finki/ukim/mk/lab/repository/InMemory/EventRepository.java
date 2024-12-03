//package mk.finki.ukim.mk.lab.repository.InMemory;
//
//import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
//import mk.finki.ukim.mk.lab.model.Event;
//import mk.finki.ukim.mk.lab.model.Location;
//import mk.finki.ukim.mk.lab.model.SavedBooking;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@Repository
//public class EventRepository {
//
//    private List<SavedBooking> savedBookings = new ArrayList<>();
//
//    public List<Event> findAll() {
//        return DataHolder.events;
//    }
//
//    public List<Event> searchEvents(String text) {
//        if (text == null || text.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return DataHolder.events.stream().filter(r -> r.getName().toLowerCase().contains(text.toLowerCase()) ||
//                r.getDescription().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
//    }
//
//    public List<SavedBooking> getSavedBookings() {
//        return savedBookings;
//    }
//
//    public void addBooking(String eventName, int numberOfTickets) {
//
//        Optional<SavedBooking> existingBooking = savedBookings.stream().filter(r -> r.getEventName().equals(eventName)).findFirst();
//
//        if (existingBooking.isPresent()) {
//            existingBooking.get().setNumberOfTickets(existingBooking.get().getNumberOfTickets() + numberOfTickets);
//        } else {
//            savedBookings.add(new SavedBooking(eventName, numberOfTickets));
//        }
//    }
//    public Optional<Event> saveEvent(String eventName, String description, Double rating, Long locationId, int availbleCards) {
//        Location location=DataHolder.locations.stream().filter(r->r.getId().equals(locationId)).findFirst().orElse(null);
//        Event event = new Event(eventName,description,rating,location,availbleCards);
//        DataHolder.events.add(event);
//        return null;
//    }
//    public void deleteById(Long id) {
//        DataHolder.events.removeIf(r -> Objects.equals(r.getId(), id));
//    }
//    public Optional<Event> findById(Long id) {
//        return DataHolder.events.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst();
//    }
//
//    public Optional<Event> removeCards(Long id,int cardsTaken){
//        Optional<Event> event = DataHolder.events.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst();
//        int cardsnow = event.get().getAvailableCards() - cardsTaken;
//        event.get().setAvailableCards(cardsnow);
//        return event;
//    }
//}
