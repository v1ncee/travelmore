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

    public List<Person> findAll() {
        return entityManager.createNamedQuery(Person.FIND_ALL, Person.class).getResultList();
    }

    public void insert(Person person) {
        entityManager.persist(person);
    }

}
