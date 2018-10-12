package be.thomasmore.travelmore.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "location")
@NamedQueries(
        {
                @NamedQuery(
                        name = Location.FIND_BY_CODE,
                        query = "SELECT l FROM Location l WHERE l.code = :code"
                ),
                @NamedQuery(
                        name = Location.FIND_ALL,
                        query = "SELECT l FROM Location l"
                )
        }
)
public class Location {
    public static final String FIND_ALL = "Location.findAll";
    public static final String FIND_BY_CODE = "Location.findByCode";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocationID")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Code")
    @Size(min=3, max = 5)
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
