package Request;

public class LoadRequest {
    private String username;
    private String password;
    private LoadRequest request;

    public LoadRequest() {}
    public LoadRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoadRequest login(String username, String password) {
        LoadRequest request = new LoadRequest();
        request.username = username;
        request.password = password;
        return request;
    }
}
