package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //invalidate makes sure the session becomes unusable
        request.getSession().invalidate();
        HttpSession ses = request.getSession();
        ses.setAttribute("confirmation","Je bent uitgelogd!");
        try {
            response.sendRedirect("Controller?command=Home");
        }catch (Exception e){
            e.printStackTrace();
        }


        return "Controller?command=Home";
    }
}
