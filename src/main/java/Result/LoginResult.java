package Result;

import Model.User;

public class LoginResult {
    private String authToken;
    private String username;
    private String personID;
    private boolean success;
    private String message;
    public LoginResult() {}
    public LoginResult(String authToken, String username, String personID, boolean success, String message) {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    public LoginResult login(User user, String authToken) {
        LoginResult result = new LoginResult();
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

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
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
