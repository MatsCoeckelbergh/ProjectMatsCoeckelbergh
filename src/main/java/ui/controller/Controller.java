package ui.controller;

import domain.db.services.ContactService;
import domain.db.services.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PersonService service = new PersonService();
    private final ContactService contactService = new ContactService();
    private final HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination = "index.jsp";
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, service, contactService);
                destination = handler.handleRequest(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                destination = "error.jsp";
            }
        }
        if (!response.isCommitted()){
            HttpSession ses = request.getSession();
            request.setAttribute("confirmation", ses.getAttribute("confirmation"));
            ses.removeAttribute("confirmation");
            request.getRequestDispatcher(destination).forward(request, response);
        }

    }
}
