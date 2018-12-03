package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookingRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Booking findById(int id) {
        return entityManager.find(Booking.class, id);
    }

    public List<Booking> findAll() {
        return entityManager.createNamedQuery(Booking.FIND_ALL, Booking.class).getResultList();
    }

    public List<Booking> findBookingsByPersonId(int personId) {
        return entityManager.createNamedQuery(Booking.FIND_BY_PERSON_ID, Booking.class).setParameter("personId", personId).getResultList();
    }

    public void insert(Booking booking) {
        entityManager.persist(booking);
    }

    public void setPayed(Booking booking){
        entityManager.createNamedQuery(Booking.SETPAYED_BY_ID).setParameter("payed", booking.isPayed()).setParameter("id", booking.getId()).executeUpdate();
    }

}
