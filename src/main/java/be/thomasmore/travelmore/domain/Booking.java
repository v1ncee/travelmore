package be.thomasmore.travelmore.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "booking")
@NamedQueries(
        {
                @NamedQuery(
                        name = Booking.FIND_BY_ID,
                        query = "SELECT b FROM Booking b WHERE b.id = :id"
                ),
                @NamedQuery(
                        name = Booking.FIND_ALL,
                        query = "SELECT b FROM Booking b"
                ),
                @NamedQuery(
                        name = Booking.FIND_BY_PERSON_ID,
                        query = "SELECT b FROM Booking b INNER JOIN Person p ON b.person.id = p.id WHERE p.id = :personId"
//                        "SELECT t FROM Trip t INNER JOIN Location dl ON t.arrivallocation.id = dl.id Where dl.name LIKE :name"
                ),
                @NamedQuery(
                        name = Booking.SETPAYED_BY_ID,
                        query = "UPDATE Booking b SET b.payed = :payed WHERE b.id = :id"
                )
        }
)

//Bij een boeking moeten plaatsen verminderen


public class Booking {

    public static final String FIND_ALL = "Booking.findAll";
    public static final String FIND_BY_ID = "Booking.findByID";
    public static final String FIND_BY_PERSON_ID = "Booking.findByPersonID";
    public static final String SETPAYED_BY_ID = "Booking.setPayedByID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int id;
    @Column(name = "Persons")
    private int persons;
    @Column(name = "Payed")
    private boolean payed;
    @Column(name = "Note")
    private String note;


    @ManyToOne
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "TripID", referencedColumnName = "TripID")
    private Trip trip;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
