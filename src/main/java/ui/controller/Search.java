package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Search extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String filterString = request.getParameter("searchfor");
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception ignored){
        }
        if (person == null){
            return "/Controller?command=Home";
        }

        List<Contact> contacts = contactService.getAllFromGivenUserId(person.getUserid());
        if (person.getRole().equals("admin")){
            contacts = contactService.getAll();
        }
        ArrayList<Contact> tmp = new ArrayList<Contact>();
        if (personService.getLastPositiveTestDay(person.getUserid()) != null) {
            for (Contact c : contacts) {
                if (personService.getLastPositiveTestDay(person.getUserid()).isBefore(c.getTime())) {
                    tmp.add(c);
                }
            }
        }

        contacts = tmp;
        if (!(filterString == null) && !filterString.isEmpty()){
            tmp = new ArrayList<Contact>();
            for (Contact c : contacts) {
                if (c.matchesString(filterString)){
                    tmp.add(c);
                }
            }
            contacts = tmp;
        }
        request.setAttribute("contacts", contacts);
        return "search.jsp";
    }
}
