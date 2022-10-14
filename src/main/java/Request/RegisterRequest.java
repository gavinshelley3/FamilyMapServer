package Request;

import DataAccess.DataAccessException;
import DataAccess.UserDao;
import Model.Person;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRequest {
    private User user;

    public RegisterRequest() {}
    public RegisterRequest(User user) {
        this.user = user;
    }

    public String register(User user) {
        return "Register";
    }




}
