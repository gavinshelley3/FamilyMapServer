package Service;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import Model.AuthToken;
import Model.Event;
import Request.EventRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    private EventService eventService;
    private EventRequest eventRequest;
    private Database db;
    private EventDao eventDao;
    private AuthTokenDao aDao;
    private AuthToken authToken;
    private String authTokenString;
    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() throws DataAccessException, SQLException {
        db = new Database();
        Connection conn = db.getConnection();
        eventDao = new EventDao(conn);
        aDao = new AuthTokenDao(conn);

        eventDao.clear();
        aDao.clear();

        event1 = new Event("eventType", "personID", "city", "country", 0, 0, 0, "eventID", "username");
        event2 = new Event("eventType", "personID", "city", "country", 0, 0, 0, "eventID2", "username");

        eventDao.insert(event1);
        eventDao.insert(event2);

        authToken = new AuthToken("authToken", "username");
        authTokenString = authToken.getAuthtoken();

        aDao.insert(authToken);
        conn.commit();
    }

    @AfterEach
    void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void getEventPass() {
        eventRequest = new EventRequest(authTokenString, "eventID");
        eventService = new EventService();
        assertEquals(event1.getEventID(), eventService.getEvent(eventRequest).getEventID());
    }

    @Test
    void getEventFail() {
        eventRequest = new EventRequest(authTokenString, "eventID3");
        eventService = new EventService();
        assertEquals("Error: Event not found", eventService.getEvent(eventRequest).getMessage());
    }

    @Test
    void getEventsPass() {
        eventRequest = new EventRequest(authTokenString, null);
        eventService = new EventService();
        assertEquals(2, eventService.getEvents(eventRequest).getData().length);
    }

    @Test
    void getEventsFail(){
        eventRequest = new EventRequest("authToken2", null);
        eventService = new EventService();
        assertEquals("Error: Invalid auth token", eventService.getEvents(eventRequest).getMessage());
    }
}