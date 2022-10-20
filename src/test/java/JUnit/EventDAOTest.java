package JUnit;

import DataAccess.Database;
import DataAccess.EventDAO;
import Model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventDAOTest {
    private Database db;
    private Event event;
    private EventDAO eventDAO;

    @BeforeEach
    void setUp() throws Exception {
        db = new Database();
        event = new Event("eventID", "username", "personID", 0, 0, "country", "city", "eventType", 0);
        eventDAO = new EventDAO(db.getConnection());
        eventDAO.clear();
    }

    @AfterEach
    void tearDown() throws Exception {
        db.closeConnection(false);
    }

    @Test
    void insert() throws Exception {
        try {
            eventDAO.insert(event);
            Event compareTest = eventDAO.find(event.getEventID());
            assertNotNull(compareTest);
            assertEquals(event.getEventID(), compareTest.getEventID());
            assertEquals(event.getAssociatedUsername(), compareTest.getAssociatedUsername());
            assertEquals(event.getPersonID(), compareTest.getPersonID());
            assertEquals(event.getLatitude(), compareTest.getLatitude());
            assertEquals(event.getLongitude(), compareTest.getLongitude());
            assertEquals(event.getCountry(), compareTest.getCountry());
            assertEquals(event.getCity(), compareTest.getCity());
            assertEquals(event.getEventType(), compareTest.getEventType());
            assertEquals(event.getYear(), compareTest.getYear());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            eventDAO.insert(null);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void find() throws Exception {
        try {
            eventDAO.insert(event);
            Event compareTest = eventDAO.find(event.getEventID());
            assertNotNull(compareTest);
            assertEquals(event.getEventID(), compareTest.getEventID());
            assertEquals(event.getAssociatedUsername(), compareTest.getAssociatedUsername());
            assertEquals(event.getPersonID(), compareTest.getPersonID());
            assertEquals(event.getLatitude(), compareTest.getLatitude());
            assertEquals(event.getLongitude(), compareTest.getLongitude());
            assertEquals(event.getCountry(), compareTest.getCountry());
            assertEquals(event.getCity(), compareTest.getCity());
            assertEquals(event.getEventType(), compareTest.getEventType());
            assertEquals(event.getYear(), compareTest.getYear());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void clear() throws Exception {
        try {
            eventDAO.insert(event);
            eventDAO.clear();
            Event compareTest = eventDAO.find(event.getEventID());
            assertNull(compareTest);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}