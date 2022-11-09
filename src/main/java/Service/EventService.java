package Service;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Request.EventRequest;
import Result.EventResult;
import TreeObjects.Location;

import java.sql.Connection;

public class EventService {
    private String eventID;
    private String authToken;
    public EventService() {

    }
    // Returns the single Event object with the specified ID (if the event is associated with the current user).
    // The current user is determined by the provided authtoken.
    // Returns ALL events for ALL family members of the current user.
    // The current user is determined from the provided authtoken.
    /**
     * Gets all events for all family members of the current user. The current user is
     * determined from the provided auth token.
     * @param eventRequest
     * @return
     */

    public EventResult getEvents(EventRequest eventRequest) {
        EventResult result = new EventResult();
        Database db = new Database();
        try{
            db.openConnection();
            AuthTokenDao authTokenDao = new AuthTokenDao(db.getConnection());
            EventDao eventDao = new EventDao(db.getConnection());
            String associatedUsername;
            if(authTokenDao.find(eventRequest.getAuthToken()) != null){
                associatedUsername = authTokenDao.find(eventRequest.getAuthToken()).getUsername();
                result.setData(eventDao.findAll(associatedUsername));
                result.setSuccess(true);
                result.setMessage("Successfully found events");
            } else{
                db.closeConnection(false);
                result.setMessage("Error: Invalid auth token");
                result.setSuccess(false);
                return result;
            }
        }catch(DataAccessException e){
            result.setSuccess(false);
            result.setMessage("Error: Internal server error");
            db.closeConnection(false);
            return result;
        }
        db.closeConnection(true);
        return result;
    }


//    public Event birth(Person person) {
//        Event birthEvent = new Event();
//        Location randomLocation = new Location();
//        randomLocation.getRandomLocation();
//        birthEvent.setEventType("Birth");
//        birthEvent.setPersonID(person.getPersonID());
//        birthEvent.setCity(randomLocation.getCity());
//        birthEvent.setCountry(randomLocation.getCountry());
//        birthEvent.setLatitude(randomLocation.getLatitude());
//        birthEvent.setLongitude(randomLocation.getLongitude());
//        birthEvent.setYear(randomLocation.getYear());
//        birthEvent.setAssociatedUsername(person.getAssociatedUsername());
//        birthEvent.setEventID(person.getPersonID() + "_birth");
//        return birthEvent;
//    }
//
//    public Event[] marriage(Person mom, Person dad) {
//        Event[] marriageEvents = new Event[2];
//        Location randomLocation = new Location();
//        randomLocation.getRandomLocation();
//        Event momMarriageEvent = new Event();
//        Event dadMarriageEvent = new Event();
//
//
//        momMarriageEvent.setEventType("Marriage");
//        momMarriageEvent.setPersonID(mom.getPersonID());
//        momMarriageEvent.setCity(randomLocation.getCity());
//        momMarriageEvent.setCountry(randomLocation.getCountry());
//        momMarriageEvent.setLatitude(randomLocation.getLatitude());
//        momMarriageEvent.setLongitude(randomLocation.getLongitude());
//        momMarriageEvent.setYear(randomLocation.getYear());
//        momMarriageEvent.setAssociatedUsername(mom.getAssociatedUsername());
//        momMarriageEvent.setEventID(mom.getPersonID() + "_Marriage");
//
//
//        dadMarriageEvent.setEventType("Marriage");
//        dadMarriageEvent.setPersonID(dad.getPersonID());
//        dadMarriageEvent.setCity(randomLocation.getCity());
//        dadMarriageEvent.setCountry(randomLocation.getCountry());
//        dadMarriageEvent.setLatitude(randomLocation.getLatitude());
//        dadMarriageEvent.setLongitude(randomLocation.getLongitude());
//        dadMarriageEvent.setYear(randomLocation.getYear());
//        dadMarriageEvent.setAssociatedUsername(dad.getAssociatedUsername());
//        dadMarriageEvent.setEventID(dad.getPersonID() + "_Marriage");
//
//        marriageEvents[0] = momMarriageEvent;
//        marriageEvents[1] = dadMarriageEvent;
//        return marriageEvents;
//    }
//
//    public Event[] death(Person person, Person parent) {
//        Event[] deathEvents = new Event[2];
//        Event deathEvent = new Event();
//        Event parentDeathEvent = new Event();
//        Location randomLocation = new Location();
//        randomLocation.getRandomLocation();
//        deathEvent.setEventType("Death");
//        deathEvent.setPersonID(person.getPersonID());
//        deathEvent.setCity(randomLocation.getCity());
//        deathEvent.setCountry(randomLocation.getCountry());
//        deathEvent.setLatitude(randomLocation.getLatitude());
//        deathEvent.setLongitude(randomLocation.getLongitude());
//        deathEvent.setYear(randomLocation.getYear());
//        deathEvent.setAssociatedUsername(person.getAssociatedUsername());
//        deathEvent.setEventID(person.getPersonID() + "_Death");
//
//        Location randomLocation2 = new Location();
//        parentDeathEvent.setEventType("Death");
//        parentDeathEvent.setPersonID(person.getPersonID());
//        parentDeathEvent.setCity(randomLocation2.getCity());
//        parentDeathEvent.setCountry(randomLocation2.getCountry());
//        parentDeathEvent.setLatitude(randomLocation2.getLatitude());
//        parentDeathEvent.setLongitude(randomLocation2.getLongitude());
//        parentDeathEvent.setYear(randomLocation2.getYear());
//        parentDeathEvent.setAssociatedUsername(parent.getAssociatedUsername());
//        parentDeathEvent.setEventID(parent.getPersonID() + "_Death");
//
//        deathEvents[0] = deathEvent;
//        deathEvents[1] = parentDeathEvent;
//        return deathEvents;
//    }

    public EventResult getEvent(EventRequest eventRequest) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();
            AuthTokenDao authTokenDao = new AuthTokenDao(conn);
            EventDao eventDao = new EventDao(conn);
            EventResult eventResult = new EventResult();
            if (authTokenDao.find(eventRequest.getAuthToken()) != null) {
                Event event = eventDao.getEvent(eventRequest.getEventID());
                if (event != null) {
                    AuthToken authToken = authTokenDao.find(eventRequest.getAuthToken());
                    if (event.getAssociatedUsername().equals(authToken.getUsername())) {
                        eventResult.setSuccess(true);
                        eventResult.setAssociatedUsername(event.getAssociatedUsername());
                        eventResult.setCity(event.getCity());
                        eventResult.setCountry(event.getCountry());
                        eventResult.setEventID(event.getEventID());
                        eventResult.setEventType(event.getEventType());
                        eventResult.setLatitude(event.getLatitude());
                        eventResult.setLongitude(event.getLongitude());
                        eventResult.setPersonID(event.getPersonID());
                        eventResult.setYear(event.getYear());
                    }
                    else {
                        db.closeConnection(false);
                        eventResult.setSuccess(false);
                        eventResult.setMessage("Error: Event does not belong to this user");
                    }
                }
                else {
                    db.closeConnection(false);
                    eventResult.setMessage("Error: Event not found");
                    eventResult.setSuccess(false);
                }
            }
            else {
                db.closeConnection(false);
                eventResult.setSuccess(false);
                eventResult.setMessage("Error: Event does not exist");
            }
            db.closeConnection(true);
            return eventResult;
        }
        catch (Exception e) {
            e.printStackTrace();
            EventResult eventResult = new EventResult("Error: " + e.getMessage(), false);
            db.closeConnection(false);
            return eventResult;
        }
    }
}
