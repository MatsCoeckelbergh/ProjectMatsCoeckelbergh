package domain.db;

import domain.model.Contact;
import domain.model.Person;

import java.util.List;

public interface ContactDB {

    void add(Contact contact);

    List<Contact> getAll();

    List<Contact> getAllFromUserWithID(String userID);

    int getSize();
}