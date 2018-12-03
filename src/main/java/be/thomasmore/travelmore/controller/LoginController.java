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

    private Person newUser = new Person();
    private Person gebruikteUser = new Person();
    private int userId;

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

    public Person getGebruikteUser(){
        return gebruikteUser;
    }

    public String login(){
        Person login = this.personService.compareLogin(gebruikteUser);

        if(login != null){

            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", login);

            setId(login.getId());

            return "index";
        }else{

        }
        return "index";
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return userId;
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public boolean isLoggedIn(){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().containsKey("user");
    }

//    public boolean getLoggedUser(){
//        FacesContext context = FacesContext.getCurrentInstance();
//        return context.getExternalContext().getSessionMap().get("user");
//    }

    public String navigateToLogin(){
        return "login";
    }
}
