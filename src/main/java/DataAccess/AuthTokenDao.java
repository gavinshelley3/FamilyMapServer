package DataAccess;

import Model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AuthTokenDao {
    private final Connection conn;
    public AuthTokenDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new authToken into the database
     * @param authToken the authToken to be inserted
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public void insert(AuthToken authToken) throws DataAccessException {
        String sql = "INSERT INTO AuthTokenTable (authtoken, username) " +
                "VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthtoken());
            stmt.setString(2, authToken.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds a authToken in the database
     * @param authtoken the authtoken of the authToken to be found
     * @return the authToken with the given authtoken
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public AuthToken find(String authtoken) throws DataAccessException {
        if (authtoken == null) {
            throw new DataAccessException("authtoken cannot be null");
        }
        AuthToken authToken;
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthTokenTable WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authToken = new AuthToken(rs.getString("authtoken"), rs.getString("username"));
                return authToken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        }
    }

    /**
     * Deletes all authtokens from the database
     * @throws DataAccessException if an error occurs while accessing the database
     */
    public void clear() throws DataAccessException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM AuthTokenTable")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing AuthTokenTable");
        }
    }
}
