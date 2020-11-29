package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Remove extends  RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        personService.delete(person.getUserid());
        //Log out because otherwise you'd be logged in on an account that does not exist.
        request.getSession().invalidate();
        return "Controller?command=Home";
    }
}
