package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            //.trim() because humans are sloppy and might enter whitespace
            String userID = request.getParameter("userId").trim();
            Person person = getPersonService().get(userID);

            if (person != null && person.isCorrectPassword(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", person);
            } else {
                request.setAttribute("userIdPrevious", userID);
                request.setAttribute("error", "Your password does not match.");

            }
        } catch (DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        if (request.getAttribute("error") == "" || request.getAttribute("error") == null){
            request.setAttribute("confirmation", "Je bent ingelogd!");
        }
        return "/Controller?command=Home";
    }
}
