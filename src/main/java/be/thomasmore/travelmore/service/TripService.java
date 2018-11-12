package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.repository.TripRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
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

    public List<Trip> findAllTripsWithFreePlaces(int number) {
        return tripRepository.findByFreePlaces(number);
    }

    public List<Trip> findAllTripsWithPrice(double price) {
        return tripRepository.findByPrice(price);
    }

    public List<Trip> findAllTripsWithTransport(String transport) {
        return tripRepository.findByTransport(transport);
    }

    public List<Trip> findAllTripsBetweenPeriod(Date startDate, Date endDate) {
        return tripRepository.findByPeriod(startDate, endDate);
    }

    public List<Trip> findAllTripsWithDepartLocationName(String name) {
        return tripRepository.findByDepartLocationName(name);
    }

    public void updateName(int id, String newName) {
        Trip trip = tripRepository.findById(id);
        trip.setTitle(newName);
    }

    public void insert(Trip trip) {
        tripRepository.insert(trip);
    }

}
