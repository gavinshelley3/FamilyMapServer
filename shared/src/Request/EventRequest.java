package Request;

public class EventRequest {
    private String eventID;
    private String authtoken;

    public EventRequest(String authtoken, String eventID) {
        this.eventID = eventID;
        this.authtoken = authtoken;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
