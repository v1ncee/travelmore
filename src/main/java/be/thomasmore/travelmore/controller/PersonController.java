package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Person;
import be.thomasmore.travelmore.service.PersonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class PersonController {

    private Person newPerson = new Person();

    @Inject
    private PersonService personService;

    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

    public List<Person> getPersons(){
        return this.personService.findAllPersons();
    }

    public void submit(){
        this.personService.insert(newPerson);
    }

}
