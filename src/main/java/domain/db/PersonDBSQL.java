package domain.db;

import domain.model.Person;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private final Connection connection;
    private final String schema;

    public PersonDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    public Boolean containsID(String userid)
    {
        for (Person p : this.getAll())
        {
            if (p.getUserid().equals(userid))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Stores the given person in the database
     *
     * @param p The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person p) {
        if (p == null) {
            throw new DbException("Nothing to add.");
        }
        if (this.containsID(p.getUserid()))
        {
            throw new DbException("User already exists");
        }
        String sql = String.format("INSERT INTO %s.person (userid, password, firstname, email, lastname, role) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, p.getUserid());
            statementSQL.setString(2, p.getPassword());
            statementSQL.setString(3, p.getFirstName());
            statementSQL.setString(4, p.getEmail());
            statementSQL.setString(5, p.getLastName());
            statementSQL.setString(6, p.getRole());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Returns a list with all countries stored in the database
     * @return An arraylist with all countries stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<Person>();
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            extractResults(persons, statementSql);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }



    /**
     * Removes the given person from the database
     * @param p The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person is not in the database
     */
    @Override
    public void delete(Person p) {
        if (p == null) {
            throw new DbException("Nothing to delete.");
        }
        String sql = String.format("DELETE FROM %s.person WHERE userid=?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, p.getUserid());
            statementSql.executeQuery();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Person person) {
        delete(person);
        add(person);
    }

    @Override
    public Person get(String personUserID) {
        List<Person> persons = new ArrayList<Person>();
        if (personUserID == null || personUserID.isEmpty()) {
            throw new DbException("invalid userID");
        }
        String sql = String.format("SELECT * FROM %s.person WHERE userid=?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personUserID);
            extractResults(persons, statementSql);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        if (persons.isEmpty())
        {
            throw new DbException("user not in database.");
        }
        return persons.get(0);
    }

    @Override
    public void registerTestResult(String userID, LocalDateTime time) {
        String sql = String.format("UPDATE %s.person SET lasttesttime = ? WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, Timestamp.valueOf(time));
            statementSql.setString(2, userID);
            System.out.println(statementSql);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
       }
    }

    @Override
    public LocalDateTime getLastTestDate(String userID) {
        LocalDateTime out = LocalDateTime.now();
        if (userID == null || userID.isEmpty()) {
            throw new DbException("invalid userID");
        }
        if (!this.containsID(userID)){
            throw new DbException("userID not in DB");
        }
        String sql = String.format("SELECT lasttesttime FROM %s.person WHERE userid=?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userID);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                if (result.getTimestamp("lasttesttime") == null){
                    out = null;
                } else {
                    out = result.getTimestamp("lasttesttime").toLocalDateTime();
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return out;
    }

    @Override
    public Boolean hasPositiveTest(String userID) {
        return null;
    }

    private void extractResults(List<Person> persons, PreparedStatement statementSql) throws SQLException {
        ResultSet result = statementSql.executeQuery();
        while (result.next()) {
            String userID = result.getString("userid");
            String password = result.getString("password");
            String firstName = result.getString("firstname");
            String email = result.getString("email");
            String lastName = result.getString("lastName");
            String role = result.getString("role");
            Person person = new Person(userID, email, password, firstName, lastName, role);
            person.setHashedPassword(password);
            persons.add(person);
        }
    }

}