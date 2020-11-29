package ui.controller;

import domain.db.services.ContactService;
import domain.db.services.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected PersonService personService;
    protected ContactService contactService;

    public PersonService getPersonService() {
        return personService;
    }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(PersonService service) {
        personService = service;
    }

    public void setModel(ContactService service) {
        contactService = service;
    }
}
