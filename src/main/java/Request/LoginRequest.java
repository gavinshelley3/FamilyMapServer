package Request;

public class LoginRequest {
    private String userName;
    private String password;
    private LoginRequest request;


    public LoginRequest() {}
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginRequest login(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.userName = username;
        request.password = password;
        return request;
    }
}
