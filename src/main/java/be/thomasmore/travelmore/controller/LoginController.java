package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.inject.Inject;

public class LoginController {

    private Person person = new Person();

    @Inject
    private PersonService personService;

    public String login(String email, String password) {
        String returnMessage = "";

        if (this.personService.findPersonByEmail(email) != null) {
            if (this.personService.validateLogin(email, password) != null) {
                returnMessage = "Login is OK!";
            }
            else {
                returnMessage = "Password doesn't match the Email.";
            }
        }
        else {
            returnMessage = "Account not found! Please register.";
        }

        return returnMessage;
    }
}
