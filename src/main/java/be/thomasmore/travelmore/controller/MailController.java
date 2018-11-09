package be.thomasmore.travelmore.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.MessagingException;
import be.thomasmore.travelmore.service.MailService;

/**
 * A bean class that illustrates sending an email message
 * @author tcolburn
 */
@Named
@RequestScoped
public class MailController {

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
    public String getStatusMessage() {
        return statusMessage;
    }

    public String send() {
        statusMessage = "Message Sent";
        try {
            MailService.sendMessage(recipient, "TravelMore | Registratie", "Beste, bedankt voor je registratie bij TravelMore!");
        }
        catch(MessagingException ex) {
            statusMessage = ex.getMessage();
        }
        return "index";
    }

    private String recipient;
//    private String subject;
//    private String message;
    private String statusMessage = "";

}
