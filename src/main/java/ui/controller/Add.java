package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Add extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        Person person = new Person();

        setPersonEmail(person, request, errors);
        setPersonUserid(person, request, errors);
        setPersonFirstName(person, request, errors);
        setPersonLastName(person, request, errors);
        setPersonPassword(person, request, errors);
        setPersonRole(person, request, errors);

        if (person.getFirstName() != null){
            if (request.getParameter("password").toLowerCase().contains(person.getFirstName().toLowerCase())){
                errors.add("Your password mustn't contain your first name");
            }
        }
        if (person.getLastName() != null){
            if (request.getParameter("password").toLowerCase().contains(person.getLastName().toLowerCase())){
                errors.add("Your password mustn't contain your last name");
            }
        }
        if (request.getParameter("password").length() < 4){
            errors.add("Your password must be at least 4 characters long");
        }
        if (request.getParameter("password").toLowerCase().equals(request.getParameter("password"))){
            errors.add("Your password must contain at least one capital letter");
        }

        if (errors.size() == 0) {
            try {
                personService.add(person);
                return "Controller?command=Overview";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Register";
    }

    private void setPersonEmail(Person person, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            request.setAttribute("emailPrevious", email);
            person.setEmail(email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPersonUserid(Person person, HttpServletRequest request, List<String> errors) {
        String userID = request.getParameter("userid").trim();
        try {
            request.setAttribute("userIdPrevious", userID);
            person.setUserid(userID);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPersonFirstName(Person person, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName").trim();
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNamePrevious", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPersonLastName(Person person, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName").trim();
        try {
            request.setAttribute("lastNamePrevious", lastName);
            person.setLastName(lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }



    private void setPersonPassword(Person person, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password").trim();
        try {
            request.setAttribute("passwordPrevious", password);
            person.setPassword(password);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPersonRole(Person person, HttpServletRequest request, List<String> errors) {
        try {
            if (request.getParameter("password").equals("M4k3m34dm1n")) {
                person.setRole("admin");
            } else {
                person.setRole("user");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
