package ui.controller;

import domain.db.services.ContactService;
import domain.db.services.PersonService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, PersonService model, ContactService contactModel) {

        RequestHandler handler = null;

        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
            handler.setModel(contactModel);
        } catch (Exception e) {
            throw new RuntimeException("This page doesn't exist!.");
        }

        return handler;
    }
}
