package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

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
        List<Trip> trips = this.tripService.findAllTrips();
        return trips;
    }

    public List<Trip> getTripsWithLocationName(String name){
        return this.tripService.findAllTripsWithLocationName(name);
    }

    public List<Trip> getTripsWithFreePlaces(int number){
        return this.tripService.findAllTripsWithFreePlaces(number);
    }

    public List<Trip> getTripsWithPrice(double price){
        return this.tripService.findAllTripsWithPrice(price);
    }

    public List<Trip> getTripsWithTransport(String transport){
        return this.tripService.findAllTripsWithTransport(transport);
    }

    public List<Trip> getTripsBetweenPeriod(String startDateStr, String endDateStr){

        Date startdate = new Date();
        Date enddate = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(SimpleTimeZone.getTimeZone("GMT"));
            startdate = sdf.parse(startDateStr);
            enddate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            System.out.println("test");
        }


        return this.tripService.findAllTripsBetweenPeriod(startdate, enddate);
    }

    public List<Trip> getTripsWithDepartLocationName(String name){
        return this.tripService.findAllTripsWithDepartLocationName(name);
    }


    public void submit(){
        this.tripService.insert(newTrip);
    }

}
