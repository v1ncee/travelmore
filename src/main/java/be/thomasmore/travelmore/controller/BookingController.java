package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.BookingService;
import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.service.TripService;
import be.thomasmore.travelmore.controller.TripController;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@ManagedBean
@SessionScoped
public class BookingController {

    private Booking newBooking = new Booking();
    @Inject
    private BookingService bookingService;
    @Inject
    private BookingService tripService;
    @Inject
    private PersonService personService;
    @Inject
    private TripController tripController;
    @Inject
    private MailController mailController;
    @Inject
    private LoginController loginController;

    private Person person = new Person();
    private Trip trip = new Trip();
    private Location location = new Location();
    private Boolean payed;
    private int personId = 1;
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Booking getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(Booking newBooking) {
        this.newBooking = newBooking;
    }

    public List<Booking> getBookings(){
        return this.bookingService.findAllBookings();
    }

    public List<Booking> getBookingsByPerson(int userID) {
        return this.bookingService.findBookingsByPersonId(userID);
    }

    public String bookTrip(int tripID, int userID) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Trip trip = this.tripController.getTripWithId(tripID);
        Person person = this.personService.findPersonById(userID);

        newBooking.setPerson(person);
        newBooking.setTrip(trip);

        return "booking";
    }

    public String submit(String note, int persons){
        String message1;
        String returnPage;
        payed = false;

        if(newBooking.getTrip().getPlaces() < persons){

            return "booking";

        } else {
            newBooking.setNote(note);
            newBooking.setPersons(persons);
            newBooking.setPayed(payed);
            this.bookingService.insert(newBooking);

            return "dashboard";
        }
    }

    public void setPayed(int bookingId){
        //set payed to true
        Booking booking = this.bookingService.findBookingById(bookingId);
        booking.setPayed(true);

        tripService.setPayed(booking);

        //send mail after payment
        Person person = booking.getPerson();
        String email = person.getEmail();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        this.mailController.send(email, firstName, lastName, "TravelMore | Booking", "Beste " + firstName + " " + lastName + ", <br/>bedankt voor je boeking bij TravelMore! <br/><br/>Hier is je boeking: <br/><strong>Reis: </strong>" + booking.getTrip().getTitle() + " in " + booking.getTrip().getCity() + ", " + booking.getTrip().getCountry() + "<br/><strong>Datum: </strong>" + booking.getTrip().getStartDate() + " - " + booking.getTrip().getEndDate() + "<br/><strong>Aantal personen: </strong>" + booking.getPersons() + "<br/>");
    }

}
