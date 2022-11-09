package Result;

import Model.User;
import Request.RegisterRequest;

public class RegisterResult {
    private String authToken;
    private String username;
    private String personID;
    private boolean success;
    private String message;
    private RegisterResult result;

    public RegisterResult() {

    }
    public RegisterResult(String authToken, String username, String personID, boolean success, String message) {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    public RegisterResult register(User user, String authToken) {
        RegisterResult result = new RegisterResult();
        result.authToken = authToken;
        result.username = user.getUsername();
        result.personID = user.getPersonID();
        result.success = true;
        return result;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
