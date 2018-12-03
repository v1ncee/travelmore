package be.thomasmore.travelmore.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@NamedQueries(
        {
                @NamedQuery(
                        name = Person.FIND_BY_ID,
                        query = "SELECT p FROM Person p WHERE p.id = :id"
                ),
                @NamedQuery(
                        name = Person.FIND_BY_EMAIL,
                        query = "select p from Person p where p.email = :email"
                ),
                @NamedQuery(
                        name = Person.FIND_ALL,
                        query = "SELECT p FROM Person p"
                ),
                @NamedQuery(
                        name = Person.VALIDATE,
                        query = "select p from Person p where p.email = :email and p.passKey = :passKey"
                ),
                @NamedQuery(
                        name = Person.COMPARE_LOGIN,
                        query = "SELECT u FROM Person u WHERE u.email = :email AND u.passKey = :passKey"
                )
        }
)
public class Person {
    public static final String FIND_ALL = "Person.findAll";
    public static final String FIND_BY_ID = "Person.findByID";
    public static final String FIND_BY_EMAIL = "Person.findByEmail";
    public static final String VALIDATE = "Person.validate";
    public static final String COMPARE_LOGIN = "User.compareLogin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonID")
    private int id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "PassKey")
    private String passKey;
    @Column(name = "TypeUser")
    private int typeUser;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

}
