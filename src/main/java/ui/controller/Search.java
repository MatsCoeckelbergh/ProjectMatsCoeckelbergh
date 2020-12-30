package ui.controller;

import domain.model.Contact;
import domain.model.Person;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Search extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //authentication
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception ignored){
        }
        if (person == null){
            return "/Controller?command=Home";
        }

        LocalDateTime starttime = null,endtime = null;
        String filterString = request.getParameter("searchfor");
        try {
          starttime = LocalDateTime.parse(request.getParameter("startdate")+"T"+"00:00");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            endtime = LocalDateTime.parse(request.getParameter("enddate")+"T"+"00:00");
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        List<Contact> contacts = contactService.getAllFromGivenUserId(person.getUserid());

        ArrayList<Contact> filteredByInfectionDate = new ArrayList<Contact>();
        if (personService.getLastPositiveTestDay(person.getUserid()) != null) {
            for (Contact c : contacts) {
                if (personService.getLastPositiveTestDay(person.getUserid()).isBefore(c.getTime())) {
                    filteredByInfectionDate.add(c);
                }
            }
        }



        ArrayList<Contact> filteredByStartDate = new ArrayList<>();
        if (person.getRole().equals("admin")){
            filteredByInfectionDate = new ArrayList<Contact>();
            for (Contact c : contactService.getAll()){
                filteredByInfectionDate.add(c);
            }
        }
        if (starttime != null) {
            for (Contact c : filteredByInfectionDate) {
                if (starttime.isBefore(c.getTime())) {
                    filteredByStartDate.add(c);
                }
            }
        }else{
            filteredByStartDate = filteredByInfectionDate;
        }
        ArrayList<Contact> filteredByEndDate = new ArrayList<>();
        if (endtime != null) {
            for (Contact c : filteredByStartDate) {
                if (endtime.isAfter(c.getTime())) {
                    filteredByEndDate.add(c);
                }
            }
        }
        else{
            filteredByEndDate = filteredByStartDate;
        }
        contacts = filteredByEndDate;
        if (!(filterString == null) && !filterString.isEmpty()){
            filteredByInfectionDate = new ArrayList<Contact>();
            for (Contact c : contacts) {
                if (c.matchesString(filterString)){
                    filteredByInfectionDate.add(c);
                }
            }
            contacts = filteredByInfectionDate;
        }

        request.setAttribute("contacts", contacts);
        return "search.jsp";
    }
}
