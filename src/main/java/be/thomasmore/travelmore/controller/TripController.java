package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class TripController {

    private Trip newTrip = new Trip();

    @Inject
    private TripService tripService;

    public Trip getNewTrips() {
        return newTrip;
    }

    public void setNewTrip(Trip newTrip) {
        this.newTrip = newTrip;
    }

    public List<Trip> getTrips(){
        return this.tripService.findAllTrips();
    }

    public void submit(){
        this.tripService.insert(newTrip);
    }

}
