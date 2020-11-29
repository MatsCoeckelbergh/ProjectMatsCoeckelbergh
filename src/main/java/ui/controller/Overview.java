package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> people = personService.getAll();
        List<Person> positives = new ArrayList<Person>();
        for (Person p: people)
        {
            if (personService.getLastPositiveTestDay(p.getUserid()) != null){
                positives.add(p);
                p.setTestTime(personService.getLastPositiveTestDay(p.getUserid()));
            }
        }

        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception ignored){
        }
        if (person == null){
            return "/Controller?command=Home";
        }
        if (!(person.getRole().equals("admin"))){
            return "/Controller?command=Home";
        }
        request.setAttribute("people", people);
        request.setAttribute("positives", positives);
        return "personoverview.jsp";
    }
}
