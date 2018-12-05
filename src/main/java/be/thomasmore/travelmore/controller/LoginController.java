package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;


@ManagedBean
@SessionScoped
public class LoginController {

//    private Person newUser = new Person();
    private Person gebruikteUser = new Person();
    private int userId;
    private String message = "";

    @Inject
    private PersonService personService;
    @Inject
    private TripController tripController;


    public Person getGebruikteUser(){
        return gebruikteUser;
    }

    public void setGebruikteUser(Person gebruikteUser) {
        this.gebruikteUser = gebruikteUser;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login(){
        String message1;
        String returnPage;

        if (!gebruikteUser.getEmail().equals("") && !gebruikteUser.getPassKey().equals("")) {
            Person login = this.personService.validateLogin(gebruikteUser.getEmail(), gebruikteUser.getPassKey());

            if (login != null) {

                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("user", login);

                setId(login.getId());
                setGebruikteUser(login);

                message1 = "";
                returnPage = "index";

                this.tripController.getAllTrips();
            } else {
                message1 = "The combination of the email and passord is incorrect. Please try again!";
                returnPage = "login";
            }
        } else {
            message1 = "Make sure all fields are filled in correctly!";
            returnPage = "login";
        }


        setMessage(message1);
        return returnPage;
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        setGebruikteUser(null);
        return "login.xhtml";
    }

    public void submit(){
//        this.personService.insert(newUser);
    }
}
