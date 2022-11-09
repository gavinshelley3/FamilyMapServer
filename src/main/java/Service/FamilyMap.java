package Service;

import Model.User;

public class FamilyMap {
    private String authToken;
    private String username;
    private String personID;
    private boolean success;
    private String message;
    private FamilyMap result;

    public FamilyMap() {

    }
    public FamilyMap(String authToken, String username, String personID, boolean success, String message) {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    public FamilyMap register(User user, String authToken) {
        FamilyMap result = new FamilyMap();
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

    public FamilyMap getResult() {
        return result;
    }

    public void setResult(FamilyMap result) {
        this.result = result;
    }
}
