package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.repository.LocationRepository;
import be.thomasmore.travelmore.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PersonService {
    @Inject
    private PersonRepository personRepository;

    public Person findPersonById(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }


    public void insert(Person person) {
        personRepository.insert(person);
    }

}
