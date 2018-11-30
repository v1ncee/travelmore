package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.BookingService;
import be.thomasmore.travelmore.service.PersonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@ManagedBean
@ViewScoped
public class BookingController {

    private Booking newBooking = new Booking();
    @Inject
    private BookingService bookingService;

    private Person person = new Person();
    private Trip trip = new Trip();
    private Location location = new Location();
    private Boolean payed;


    public Booking getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(Booking newBooking) {
        this.newBooking = newBooking;
    }

    public List<Booking> getBookings(){
        return this.bookingService.findAllBookings();
    }

    public String submit(String note, int persons, Person person, Trip trip){
        payed = true;

        /*
        location.setName("Schiphol");
        location.setCode("ams");

        person.setFirstName("eloy");
        person.setLastName("boone");
        person.setEmail("eloy@test.com");
        person.setPassKey("azerty");
        person.setTypeUser(0);

        trip.setTitle("testbooking");
        trip.setCity("Brussel");
        trip.setCountry("Belgium");
        trip.setPrice(150);
        trip.setTransport("Vliegtuig");
        trip.setPlaces(5);
        trip.setDepartlocation(location);
        trip.setLocation(location);
        */

        newBooking.setNote(note);
        newBooking.setPersons(persons);
        newBooking.setPayed(payed);
        newBooking.setPerson(person);
        newBooking.setTrip(trip);
        this.bookingService.insert(newBooking);

        return "index";
    }

    public void setPayed(){
        //hier moet de huidige booking inkomen
        Booking b = new Booking();
        b.setId(3);
        b.setPayed(true);

        this.bookingService.setPayed(b);
    }

}
