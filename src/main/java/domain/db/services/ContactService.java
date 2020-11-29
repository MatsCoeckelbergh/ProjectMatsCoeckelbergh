package domain.db.services;

import domain.db.ContactDB;
import domain.db.ContactDBSQL;
import domain.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {/*
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public ContactService() {
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("No contact given");
        }
        contacts.add(contact);
    }

    public int getNumberOfContacts() {
        return contacts.size();
    }
    */
    ContactDB db = new ContactDBSQL();

    public ContactService(){}

    public List<Contact> getAll(){
        return db.getAll();
    }

    public List<Contact> getAllFromGivenUserId(String userID){
        return db.getAllFromUserWithID(userID);
    }

    public void add(Contact contact) {
        db.add(contact);
    }

    public int getNumberOfContacts() {
        return db.getSize();
    }
}
