package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.service.BookingService;
import be.thomasmore.travelmore.service.PersonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.awt.print.Book;
import java.util.List;

@ManagedBean
@ViewScoped
public class BookingController {

    private Booking newBooking = new Booking();

    @Inject
    private BookingService bookingService;

    public Booking getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(Booking newBooking) {
        this.newBooking = newBooking;
    }

    public List<Booking> getBookings(){
        return this.bookingService.findAllBookings();
    }

    public void submit(){
        this.bookingService.insert(newBooking);
    }

    public void setPayed(){
        //hier moet de huidige booking inkomen
        Booking b = new Booking();
        b.setId(3);
        b.setPayed(true);

        this.bookingService.setPayed(b);
    }

}
