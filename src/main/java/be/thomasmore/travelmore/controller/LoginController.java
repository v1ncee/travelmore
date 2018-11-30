package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.SessionUtilities;
import be.thomasmore.travelmore.service.PersonService;
import be.thomasmore.travelmore.domain.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@ManagedBean
@SessionScoped
public class LoginController {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Inject
    private PersonService personService;

    public PersonService getPersonService(){
        return personService;
    }

    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    public String login(String email, String password) {
        Person person = new Person();

        person = personService.validateLogin(email, password);

        if (person != null) {
            HttpSession session = SessionUtilities.getSession();
            session.setAttribute("id", person.getId());
            session.setAttribute("name", person.getFirstName());
            session.setAttribute("email", person.getEmail());
            log();

            return "index";
        }

        return "login";
    }

    public String logout()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return "logout";
    }

    public void log() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        setName(session.getAttribute("name").toString());
    }




}
