package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.repository.BookingRepository;
import be.thomasmore.travelmore.repository.LocationRepository;
import be.thomasmore.travelmore.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.awt.print.Book;
import java.util.List;

@Stateless
public class BookingService {
    @Inject
    private BookingRepository bookingRepository;

    public Booking findBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }


    public void insert(Booking booking) {
        bookingRepository.insert(booking);
    }

}
