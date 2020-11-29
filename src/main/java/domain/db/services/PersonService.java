package domain.db.services;

import domain.db.PersonDBSQL;
import domain.model.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
    private Map<String, Person> persons = new HashMap<>();
    private PersonDBSQL db = new PersonDBSQL();

    public PersonService(){
    }

    public Person get(String personId) {
        /*
        if (personId == null) {
            throw new DbException("No id given");
        }
        //userID mustn't be case-sensitive
        Person person = persons.get(personId.toLowerCase());
        if (person == null) throw new DbException("Person not in database");
        return person;
        */

        try {
            return db.get(personId);
        }catch  (Exception e){
        }
        return null;
    }
    /*
    public List<Person> getAll() {
        return new ArrayList<Person>(persons.values());
    }
    */

    public List<Person> getAll() {
        try {
            return db.getAll();
        }catch  (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void add(Person person) {
        try {
            db.add(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(Person person) {
        /*
        if (person == null) {
            throw new DbException("No person given");
        }
        if (!persons.containsKey(person.getUserid())) {
            throw new DbException("No person found");
        }
        persons.put(person.getUserid(), person);
        */
        db.update(person);
    }


    public void delete(String personId) {
        /*
        if (personId == null) {
            throw new DbException("No id given");
        }
        persons.remove(personId);
         */

        try {
            db.delete(db.get(personId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerPositiveTest(String userID, LocalDateTime localDateTime){
        if (getLastPositiveTestDay(userID) == null)
        {
            db.registerTestResult(userID, localDateTime);
            return;
        }
        if (localDateTime.isAfter(getLastPositiveTestDay(userID))) {
            db.registerTestResult(userID, localDateTime);
        }
    }

    public LocalDateTime getLastPositiveTestDay(String userID){
        return db.getLastTestDate(userID);
    }

    public int getNumberOfPersons() {
        return db.getAll().size();
    }

}
