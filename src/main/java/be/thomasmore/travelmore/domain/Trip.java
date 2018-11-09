package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "trip")
@NamedQueries (
    {
        @NamedQuery(
            name = Trip.FIND_ALL,
                query = "SELECT t from Trip t"
        ),
            @NamedQuery(
                    name = Trip.FIND_ALL_LOCATION_NAME,
                    query = "SELECT t FROM Trip t INNER JOIN Location l ON t.location.id=l.id Where l.name LIKE :name"
            )
    }
)

public class Trip {
    public static final String FIND_ALL = "Trip.findAll";
    /*public static final String FIND_BY_CODE = "Trip.findByCode";*/
    public static final String FIND_ALL_LOCATION_NAME = "Trip.findAllLocationName";


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TripID")
    private int id;
    @Column(name = "Title")
    private String title;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "Price")
    private double price;
    @Column(name = "Places")
    private int places;
    @Column(name = "Transport")
    private String transport;
    @Column(name = "Country")
    private String country;
    @Column(name = "City")
    private String city;


    @OneToOne
    @JoinColumn(name = "LocationID")
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
