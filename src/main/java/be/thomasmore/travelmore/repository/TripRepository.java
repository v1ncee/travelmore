package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class TripRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Trip findById(int id) {
        return entityManager.find(Trip.class, id);
    }

    public List<Trip> findAll() {
        return entityManager.createNamedQuery(Trip.FIND_ALL, Trip.class).getResultList();
    }

    public List<Trip> findByLocationName(String name) {
        if(name != "" && name != null){
            return entityManager.createNamedQuery(Trip.FIND_ALL_LOCATION_NAME, Trip.class).setParameter("name", name + "%").getResultList();
        }
        return null;
    }

    public List<Trip> findByFreePlaces(int number) {
        if(number != 0 && number > 0){
            return entityManager.createNamedQuery(Trip.FIND_ALL_FREE_PLACES, Trip.class).setParameter("number", number).getResultList();
        }
        return null;
    }

    public List<Trip> findByPrice(double price) {
        if(price != 0 && price > 0){
            return entityManager.createNamedQuery(Trip.FIND_ALL_PRICE, Trip.class).setParameter("price", price).getResultList();
        }
        return null;
    }

    public List<Trip> findByTransport(String transport) {
        if(transport != "" && transport != null){
            return entityManager.createNamedQuery(Trip.FIND_ALL_TRANSPORT, Trip.class).setParameter("transport", transport + "%").getResultList();
        }
        return null;
    }

    public List<Trip> findByPeriod(Date startDate, Date endDate) {
        if(startDate != null && endDate != null){
            return entityManager.createNamedQuery(Trip.FIND_ALL_PERIOD, Trip.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
        }
        return null;
    }

    public List<Trip> findByDepartLocationName(String name) {
        if(name != "" && name != null){
            return entityManager.createNamedQuery(Trip.FIND_ALL_DEPARTLOCATION, Trip.class).setParameter("name", name + "%").getResultList();
        }
        return null;
    }

    public void insert(Trip trip) {
        entityManager.persist(trip);
    }

}