package Result;

import Model.User;

public class LoginResult {
    private String authToken;
    private String userName;
    private String personID;
    private boolean success;
    private String message;
    private LoginResult result;
    public LoginResult() {}
    public LoginResult(String authToken, String userName, String personID, boolean success, String message) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    public LoginResult login(User user, String authToken) {
        LoginResult result = new LoginResult();
        result.authToken = authToken;
        result.userName = user.getUsername();
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
