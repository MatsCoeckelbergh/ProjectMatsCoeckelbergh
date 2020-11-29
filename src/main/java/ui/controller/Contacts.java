package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Contacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "contacts.jsp";
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception e){
            destination = "/Controller?command=Home";
        }
        if (destination.equals("/Controller?command=Home")){
            return destination;
        }
        if (person == null){
            return "/Controller?command=Home";
        }
        List<Contact> contacts = contactService.getAllFromGivenUserId(person.getUserid());
        if (personService.get(person.getUserid()).getRole().equals("admin")){
            contacts = contactService.getAll();
        }
        request.setAttribute("contacts", contacts);
        return "contacts.jsp";
    }
}
