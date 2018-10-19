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
                )
        }
)
public class Booking {
<<<<<<< HEAD
	
=======
    public static final String FIND_ALL = "Booking.findAll";
    public static final String FIND_BY_ID = "Booking.findByID";

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
    private Person person;



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









>>>>>>> 618ff719d7daae7c605a420acb5b128a9c7c44e2
}
