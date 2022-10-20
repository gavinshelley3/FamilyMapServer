package Request;

import DataAccess.DataAccessException;
import DataAccess.UserDao;
import Model.Person;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRequest {
    private RegisterRequest request;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    public RegisterRequest() {

    }


    public RegisterRequest register(User user) {
        // Create new user
        RegisterRequest request = new RegisterRequest();
        request.username = user.getUsername();
        request.password = user.getPassword();
        request.email = user.getEmail();
        request.firstName = user.getFirstName();
        request.lastName = user.getLastName();
        request.gender = user.getGender();
        return request;
    }

}
