package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.SavedBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findAllByNameContainingOrDescriptionContaining(String name, String description);
//    void addBooking(String eventName, int numberOfTickets);
//    Optional<Event> saveEvent(String name, String description, Double rating, Long locationId, int availbleCards);
//    void removeCards(Long id, int cardsTaken);
//    List<Event> findByNameContainingIgnoreCase(String name);
    Optional<Event> findByName(String eventName);

}
