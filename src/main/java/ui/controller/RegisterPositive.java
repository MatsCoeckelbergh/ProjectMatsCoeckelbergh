package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RegisterPositive extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        Person person = new Person();
        try {
            person = (Person) request.getSession().getAttribute("user");
        } catch (Exception e){
            e.printStackTrace();
        }


        try {
            LocalDateTime time = LocalDateTime.parse(request.getParameter("date")+"T"+"00:00");
            personService.registerPositiveTest(person.getUserid(), time);
        }catch (Exception e)
        {
            if (e instanceof  DateTimeParseException){
                errors.add("Please select a date");
            }
            else
            {
                errors.add("You're not logged in. Please log in/register first.");
            }
            e.printStackTrace();

        }

        if (errors.size() == 0) {
            try {
                personService.add(person);
                HttpSession ses = request.getSession();
                ses.setAttribute("confirmation","Je hebt je test geregistreerd!");
                try {
                    response.sendRedirect("Controller?command=Contacts");
                }catch (Exception e){
                    e.printStackTrace();
                }
                return "Controller?command=Contacts";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);

        return "Controller?command=RegisterPositivePage";
    }
}
