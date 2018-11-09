package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

<<<<<<< HEAD
=======
    public List<Trip> findByLocationName(String name) {
        if(name != "" && name != null){
            return entityManager.createNamedQuery(Trip.FIND_ALL_LOCATION_NAME, Trip.class).setParameter("name", name + "%").getResultList();
        }
        return null;
    }
>>>>>>> 6f2c1b8ff291c3618ad4a2c123105be7cec3fde1

    public void insert(Trip trip) {
        entityManager.persist(trip);
    }

}