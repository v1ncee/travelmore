package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.SessionUtilities;
import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@ManagedBean
@SessionScoped
public class LoginController {

    private Person newUser = new Person();
    private Person gebruikteUser = new Person();



    @Inject
    private PersonService personService;

    public Person getNewUser() {
        return newUser;
    }

    public void setNewUser(Person newUser) {
        this.newUser = newUser;
    }

    public List<Person> getUsers(){
        return this.personService.findAllPersons();
    }

    public void submit(){
        this.personService.insert(newUser);
    }

    public String submitRegister(){
        this.personService.insert(newUser);

        return "registerBedankt";
    }

    public Person getGebruikteUser(){
        return gebruikteUser;
    }

    public String login(){
        Person login = this.personService.compareLogin(gebruikteUser);

        if(login != null){

            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", login);

            return "loginBedankt";
        }else{

        }
        return "index";
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public boolean isLoggedIn(){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().containsKey("user");
    }

    public String navigateToLogin(){
        return "login";
    }
}
