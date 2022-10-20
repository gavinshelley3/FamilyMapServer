package Service;

import Request.LoginRequest;
import Result.LoginResult;

public class LoginService {
    //Logs the user in
    //Returns an authtoken

    /**
     * Logs in a user
     * @param request
     * @return authToken
     */

    private String authToken;
    private String message;
    private Boolean success;

    public LoginService() {

    }

    public String login(LoginRequest request) {
        // Check if username and password match
        // If so, create new auth token
        // Return success
        return authToken;
    }

}
