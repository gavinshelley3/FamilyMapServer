package DataAccess;

import Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PersonDao {
    private final Connection conn;
    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new person into the database
     * @param person the person to be inserted
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public void insert(Person person) throws DataAccessException {
        if (person == null) {
            throw new DataAccessException("Person cannot be null");
        }
        if (find(person.getPersonID()) != null) {
            throw new DataAccessException("Person already exists");
        }
//        if (find(person.getAssociatedUsername()) != null) {
//            throw new DataAccessException("Person already exists");
//        }
        ResultSet rs = null;
        String sql = "INSERT INTO PersonTable (firstName, lastName, gender, personID, spouseID, fatherID, " +
                        "motherID, associatedUsername) " +
                        "VALUES(?,?,?,?,?,?,?,?)";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, person.getFirstName());
                    statement.setString(2, person.getLastName());
                    statement.setString(3, person.getGender());
                    statement.setString(4, person.getPersonID());
                    statement.setString(5, person.getSpouseID());
                    statement.setString(6, person.getFatherID());
                    statement.setString(7, person.getMotherID());
                    statement.setString(8, person.getAssociatedUsername());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DataAccessException("Error encountered while inserting into the database");
                }
            }


    /**
     * Finds a person in the database
     * @param personID the personID of the person to be found
     * @return the person with the given personID
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public Person find(String personID) throws DataAccessException {
        if (personID == null) {
            throw new DataAccessException("PersonID cannot be null");
        }

        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM PersonTable WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("gender"), rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        }
    }

    /**
     * Deletes all persons from the database
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public void clear() throws DataAccessException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM PersonTable")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing PersonTable");
        }
    }
}
