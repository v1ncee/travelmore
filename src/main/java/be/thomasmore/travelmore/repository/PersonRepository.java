package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PersonRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.createNamedQuery(Person.FIND_BY_ID, Person.class).setParameter("id", id).getSingleResult();
    }

    public Person findByEmail(String email) {
        List<Person> resultsFindByEmail = entityManager.createNamedQuery(Person.FIND_BY_EMAIL, Person.class).setParameter("email", email).getResultList();

        if (resultsFindByEmail.size() > 0) {
            return resultsFindByEmail.get(0);
        }
        else {
            return null;
        }
    }

    public List<Person> findAll() {
        return entityManager.createNamedQuery(Person.FIND_ALL, Person.class).getResultList();
    }

    public Person validate(String email, String passKey) {
        return entityManager.createNamedQuery(Person.VALIDATE, Person.class).setParameter("email", email).setParameter("passKey", passKey).getSingleResult();
    }

    public Person compareLogin(Person compareLogin){
        return entityManager.createNamedQuery(Person.COMPARE_LOGIN, Person.class)
                .setParameter("email", compareLogin.getEmail())
                .setParameter("password", compareLogin.getPassKey())
                .getSingleResult();
    }


    public void insert(Person person) {
        entityManager.persist(person);
    }

}
