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
    private Event[] data;
    public EventResult() {

    }
    public EventResult(Event event, String message, boolean success){
        this.associatedUsername = event.getAssociatedUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
        this.message = message;
        this.success = success;
    }

    public EventResult(Event[] data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public EventResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Event[] getData() {
        Event[] events = new Event[0];
        return events;
    }

    public void setData(Event[] data) {
        this.data = data;
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

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID; 
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
