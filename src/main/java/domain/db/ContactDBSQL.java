package domain.db;

import domain.model.Contact;
import domain.model.Person;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB{

    private final Connection connection;
    private final String schema;

    public ContactDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.contact (userid, firstname, lastname, email, gsm, time) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getUserID());
            statementSQL.setString(2, contact.getfName());
            statementSQL.setString(3, contact.getlName());
            statementSQL.setString(4, contact.getEmail());
            statementSQL.setString(5, contact.getPhoneNumber());
            statementSQL.setTimestamp(6, Timestamp.valueOf(contact.getTime()));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<Contact>();
        String sql = String.format("SELECT * FROM %s.contact ORDER BY time", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userID = result.getString("userid");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String gsm = result.getString("gsm");
                Timestamp timeStamp = result.getTimestamp("time");
                Contact contact = new Contact(userID, firstName, lastName, timeStamp.toLocalDateTime(), gsm, email);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllFromUserWithID(String userID) {
        List<Contact> contacts = new ArrayList<Contact>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? ORDER BY time", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userID);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String gsm = result.getString("gsm");
                Timestamp timeStamp = result.getTimestamp("time");
                Contact contact = new Contact(userID, firstName, lastName, timeStamp.toLocalDateTime(), gsm, email);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
