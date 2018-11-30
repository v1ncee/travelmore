package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.TripService;
import be.thomasmore.travelmore.service.LocationService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@ManagedBean
@SessionScoped
public class TripController {

    private Trip newTrip = new Trip();
    private List<Trip> trips;
    private List<Location> locations;

    @Inject
    private TripService tripService;
    private LocationService locationService;

    public Trip getNewTrips() {
        return newTrip;
    }

    public void setNewTrip(Trip newTrip) {
        this.newTrip = newTrip;
    }

    public List<Trip> getAllTrips() {
        trips = this.tripService.findAllTrips();
        return trips;
    }

    public List<Trip> getTrips(){
        return trips;
    }

    public void filterTable(String departCity, String city, String price, String people, String transport, String startDate, String endDate) {
        if (city.equals("") && departCity.equals("") && price.equals("") && people.equals("") && transport.equals("") && startDate.equals("") && endDate.equals("")) {
            this.getAllTrips();
        }
        if (!city.equals("")) {
            this.getTripsWithLocationName(city);
        }
        if (!departCity.equals("")) {
            this.getTripsWithDepartLocationName(departCity);
        }
        if (!price.equals("")) {
            double priceDouble;
            try {
                priceDouble = Double.parseDouble(price);
                this.getTripsWithPrice(priceDouble);
            } catch (NumberFormatException e) {
//                return "Vul een (decimaal) getal in!";
            }
        }
        if (!people.equals("")) {
            int peopleInt;
            try {
                peopleInt = Integer.parseInt(people);
                this.getTripsWithFreePlaces(peopleInt);
            } catch (NumberFormatException e) {
//                return "Vul een (decimaal) getal in!";
            }
        }
        if (!transport.equals("")) {
            this.getTripsWithTransport(transport);
        }
        if (!startDate.equals("") &&  !endDate.equals("")) {
            this.getTripsBetweenPeriod(startDate, endDate);
        }
    }

    public List<Trip> getTripsWithLocationName(String name){
        trips = this.tripService.findAllTripsWithLocationName(name);
        return trips;
    }

    public List<Trip> getTripsWithFreePlaces(int number){
        trips = this.tripService.findAllTripsWithFreePlaces(number);
        return trips;
    }

    public List<Trip> getTripsWithPrice(double price){
        trips = this.tripService.findAllTripsWithPrice(price);
        return trips;
    }

    public List<Trip> getTripsWithTransport(String transport){
        trips = this.tripService.findAllTripsWithTransport(transport);
        return trips;
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

        trips = this.tripService.findAllTripsBetweenPeriod(startdate, enddate);
        return trips;
    }

    public List<Trip> getTripsWithDepartLocationName(String name){
        trips = this.tripService.findAllTripsWithDepartLocationName(name);
        return trips;
    }

    public Trip getTripId(int id){
        Trip trip = this.tripService.findTripById(id);
        return trip;
    }


    public void submit(){
        this.tripService.insert(newTrip);
    }

}
