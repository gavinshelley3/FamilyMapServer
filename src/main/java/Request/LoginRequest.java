package Request;

public class LoginRequest {
    private String userName;
    private String password;


    public LoginRequest() {}
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String login(String userName, String password) {
        return "personID";
    }
}
