package Service;

public class EventService {
    private String eventID;
    private String authToken;
    public EventService() {

    }
    //Returns the single Event object with the specified ID (if the event is associated with the current user).
    // The current user is determined by the provided authtoken.
    //Returns ALL events for ALL family members of the current user.
    // The current user is determined from the provided authtoken.
    /**
     * Gets all events for all family members of the current user. The current user is
     * determined from the provided auth token.
     * @param authToken
     * @return
     */

    public String getEvent(String authToken) {
        return eventID;
    }
}
