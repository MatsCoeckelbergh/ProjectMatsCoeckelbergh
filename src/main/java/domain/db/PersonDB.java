package domain.db;

import domain.model.Person;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonDB {
    /**
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Person person);


    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */
    List<Person> getAll();

    /**
     * @param person The person to be removed
     * @throws DbException if the given person is null
     * @throws DbException if the given person is not in the database
     */
    void delete(Person person);

    void update(Person person);

    Person get(String personUserID);

    void registerTestResult(String userID, LocalDateTime time);

    LocalDateTime getLastTestDate(String userID);

    Boolean hasPositiveTest(String userID);
}