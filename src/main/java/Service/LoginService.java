package Service;

import DataAccess.AuthTokenDao;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Request.LoginRequest;
import Result.LoginResult;

import java.util.UUID;

public class LoginService {
    //Logs the user in
    //Returns an authtoken

    /**
     * Logs in a user
     * @param request
     * @return authToken
     */
    // Check if username and password match
    // If so, create new auth token
    // Return success

    private String authToken;
    private String message;
    private Boolean success;

    public LoginService() {

    }

    public LoginResult login(LoginRequest request) {
        LoginResult result = new LoginResult();
        Database db = new Database();
        UUID uuid = UUID.randomUUID();
        String authTokenString = uuid.toString();
        AuthToken authToken = new AuthToken(authTokenString, request.getUserName());

        try{
            db.openConnection();
            UserDao userDao = new UserDao(db.getConnection());
            AuthTokenDao authTokenDao = new AuthTokenDao(db.getConnection());
            if (userDao.find(request.getUserName()) != null) {
                if (userDao.find(request.getUserName()).getPassword().equals(request.getPassword())) {
                    authTokenDao.insert(authToken);
                    db.closeConnection(true);
                    result.setAuthToken(authTokenString);
                    result.setSuccess(true);
                    result.setMessage("Successfully logged in.");
                    result.setUserName(request.getUserName());
                } else {
                    db.closeConnection(false);
                    result.setSuccess(false);
                    result.setMessage("Error: Incorrect password.");
                }
            } else {
                db.closeConnection(false);
                result.setSuccess(false);
                result.setMessage("Error: User does not exist.");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Error: " + e.getMessage());
            try {
                db.closeConnection(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

}
