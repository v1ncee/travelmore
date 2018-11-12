package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class RegisterController {

    private Person newPerson = new Person();

    @Inject
    private PersonService personService;

    private String message;

    //register new user
    public String register(String firstName, String lastName, String email, String passKey) {
        //check if all fields are filled in
        if (!firstName.equals("") && !lastName.equals("") && !email.equals("") && !passKey.equals("")) {

            //check if email is already registered
            if (this.personService.findPersonByEmail(email) == null) {
                newPerson.setFirstName(firstName);
                newPerson.setLastName(lastName);
                newPerson.setEmail(email);
                newPerson.setPassKey(passKey);
                newPerson.setTypeUser(0);

                this.personService.insert(newPerson);

                //redirect to login page
                this.message = "Hi " + firstName + ", you are now registered. Please log in to book a trip.";
            }
            else {
                this.message = "This email is already in use. Please use another one.";
            }
        }
        else {
            this.message = "Make sure all fields are filled in correctly!";
        }

        return "message";
    }

    //get message
    public String getMessage() {
        return this.message;
    }
}
