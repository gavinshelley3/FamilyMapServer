package Service;

public class RegisterService {
    //Creates a new user account (user row in the database)
    //Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
    //Logs the user in
    //Returns an authtoken

    private String authToken;
    private String userName;
    private String personID;

    public RegisterService() {

    }

    public String register() {
        return authToken;
    }

}
