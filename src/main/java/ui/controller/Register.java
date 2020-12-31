package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception ignored){
        }
        if (person == null){
            return "register.jsp";
        }
        return "/Controller?command=Home";
    }
}
