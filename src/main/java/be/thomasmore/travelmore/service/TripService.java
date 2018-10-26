package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.repository.TripRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TripService {
    @Inject
    private TripRepository tripRepository;

    public Trip findTripById(int id) {
        return tripRepository.findById(id);
    }

    public List<Trip> findAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> findAllTripsWithLocationName(String name) {
        return tripRepository.findByLocationName(name);
    }

    public void updateName(int id, String newName) {
        Trip trip = tripRepository.findById(id);
        trip.setTitle(newName);
    }

    public void insert(Trip trip) {
        tripRepository.insert(trip);
    }

}
