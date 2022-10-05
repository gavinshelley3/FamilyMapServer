package DataAccess;

public class AuthTokenDao {
    public void add(AuthToken authToken) {
        System.out.println("AuthToken added: " + authToken.getAuthToken());
    }
}
