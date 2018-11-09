package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PersonRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person findByEmail(String email) {
        return entityManager.find(Person.class, email);
    }

    public List<Person> findAll() {
        return entityManager.createNamedQuery(Person.FIND_ALL, Person.class).getResultList();
    }

    public Person validate(String email, String passKey) {
        return entityManager.createNamedQuery(Person.VALIDATE, Person.class).setParameter("email", email).setParameter("passKey", passKey).getSingleResult();
    }

    public void insert(Person person) {
        entityManager.persist(person);
    }

}
