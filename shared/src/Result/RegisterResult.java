package Result;

import Model.User;

import java.util.UUID;

public class RegisterResult {
    private String authtoken;
    private String username;
    private String personID;
    private boolean success;
    private String message;

    public RegisterResult() {

    }

    public RegisterResult(String message) {
        this.message = message;
    }

    public RegisterResult(String authtoken, String username, String personID, boolean success, String message) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    public RegisterResult register(User user, String authtoken) {
        RegisterResult result = new RegisterResult();
        result.authtoken = authtoken;
        result.username = user.getUsername();
        result.personID = user.getPersonID();
        result.success = true;
        return result;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String generateAuthtoken() {
        return UUID.randomUUID().toString();
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
