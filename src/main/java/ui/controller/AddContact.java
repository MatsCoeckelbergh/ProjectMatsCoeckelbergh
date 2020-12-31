package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        Contact contact = new Contact();

        setContactEmail(contact, request, errors);
        setContactFirstName(contact, request, errors);
        setContactLastName(contact, request, errors);
        setContactTime(contact, request, errors);
        setPhoneNumber(contact, request, errors);
        setUserID(contact, request, errors);


        if (errors.size() == 0) {
            try {
                contactService.add(contact);
                clearPreviousValues(request);
                HttpSession ses = request.getSession();
                ses.setAttribute("confirmation","Je hebt een contact toegevoegd!");
                try {
                    response.sendRedirect("Controller?command=Contacts");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (DbException e) {
                e.printStackTrace();
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Contacts";
    }

    private void setUserID(Contact contact, HttpServletRequest request, List<String> errors) {
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception e){
            e.printStackTrace();
        }
        try{
            contact.setUserID(person.getUserid());
        } catch (Exception e)
        {
            errors.add("You're not logged in. Please log in/register first.");
        }
    }

    private void setContactEmail(Contact person, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            request.setAttribute("emailPrevious", email);
            person.setEmail(email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }


    private void setContactFirstName(Contact contact, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("fName").trim();
        try {
            contact.setfName(firstName);
            request.setAttribute("fNamePrevious", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactLastName(Contact contact, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lName").trim();
        try {
            request.setAttribute("lNamePrevious", lastName);
            contact.setlName(lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactTime(Contact contact, HttpServletRequest request, List<String> errors){
        String date = request.getParameter("date");
        String hour = request.getParameter("hour");
        try
        {
            contact.setTime(LocalDateTime.parse(date+"T"+hour));
        } catch (Exception e) {
            errors.add("No time given.");
        }
    }

    private void setPhoneNumber(Contact contact, HttpServletRequest request, List<String> errors) {
        String phoneNumber = request.getParameter("phoneNumber").trim();
        try {
            request.setAttribute("phoneNumberPrevious", phoneNumber);
            contact.setPhoneNumber(phoneNumber);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void clearPreviousValues(HttpServletRequest request){
        request.setAttribute("phoneNumberPrevious", "");
        request.setAttribute("hourPrevious", "");
        request.setAttribute("emailPrevious", "");
        request.setAttribute("fNamePrevious", "");
        request.setAttribute("lNamePrevious", "");
        request.setAttribute("datePrevious", "");
    }

}
