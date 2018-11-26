package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class RegisterController {

    private Person newPerson = new Person();
    private String message = "";

    @Inject
    private PersonService personService;
    private MailController mailController = new MailController();

    //register new user
    public String register(String firstName, String lastName, String email, String passKey) {
        MailController mail = this.mailController;
        String message1;
        String returnPage;

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

                mail.send(email, firstName, lastName);
                message1 = "Hi " + firstName + ", you are now registered. Please log in to book a trip.";
                returnPage = "login";
            } else {
                message1 = "This email is already in use. Please use another one.";
                returnPage = "register";
            }
        } else {
            message1 = "Make sure all fields are filled in correctly!";
            returnPage = "register";
        }


        setMessage(message1);

        return returnPage;
    }

    //get message
    public String getMessage() {
        return message;
    }

    //set message
    public void setMessage(String message) {
        this.message = message;
    }
}
