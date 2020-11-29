package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //invalidate makes sure the session becomes unusable
        request.getSession().invalidate();
        return "Controller?command=Home";
    }
}
