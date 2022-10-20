package Result;

import Model.Event;

public class EventResult {
    private String associatedUsername;
    private String eventID;
    private String personID;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;
    private String message;
    private boolean success;
    private EventResult result;
    public EventResult() {

    }
    public EventResult(String associatedUsername, String eventID, String personID, double latitude, double longitude,
                       String country, String city, String eventType, int year, String message, boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.message = message;
        this.success = success;
    }

    public EventResult event(Event event) {
        EventResult result = new EventResult();
        result.associatedUsername = event.getAssociatedUsername();
        result.eventID = event.getEventID();
        result.personID = event.getPersonID();
        result.latitude = event.getLatitude();
        result.longitude = event.getLongitude();
        result.country = event.getCountry();
        result.city = event.getCity();
        result.eventType = event.getEventType();
        result.year = event.getYear();
        result.message = null;
        result.success = true;
        return result;
    }

    public Event[] events() {
        Event[] events = new Event[0];
        return events;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
