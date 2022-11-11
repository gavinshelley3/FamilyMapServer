package Service;

import Model.Event;
import Model.Person;
import TreeObjects.Location;
import TreeObjects.LocationData;

public class EventGenerator {

    public static Event birth(Person person, int year, LocationData locationData) {
        Location randomLocation = locationData.getRandomLocation();
        Event birthEvent = new Event("birth", person.getPersonID(), randomLocation.getCity(),
                randomLocation.getCountry(), randomLocation.getLatitude(), randomLocation.getLongitude(), year,
                person.getPersonID() + "_birth", person.getAssociatedUsername());
        return birthEvent;
    }

    public static Event[] marriage(Person mom, Person dad, int marriageYear, LocationData locationData) {
        Location randomLocationMom = new Location();
        Location randomLocationDad = new Location();
        Event momMarriageEvent = new Event("marriage", mom.getPersonID(), randomLocationMom.getCity(),
                randomLocationMom.getCountry(), randomLocationMom.getLatitude(), randomLocationMom.getLongitude(),
                marriageYear, mom.getPersonID() + "_marriage", mom.getAssociatedUsername());
        Event dadMarriageEvent = new Event("marriage", dad.getPersonID(), randomLocationDad.getCity(),
                randomLocationDad.getCountry(), randomLocationDad.getLatitude(), randomLocationDad.getLongitude(),
                marriageYear, dad.getPersonID() + "_marriage", dad.getAssociatedUsername());
        Event[] marriageEvents = {momMarriageEvent, dadMarriageEvent};
        return marriageEvents;
    }

    public static Event[] death(Person child, Person parent, int year, LocationData locationData) {
        Location randomLocationChild = new Location();
        Location randomLocationParent = new Location();
        Event childDeathEvent = new Event("death", child.getPersonID(), randomLocationChild.getCity(),
                randomLocationChild.getCountry(), randomLocationChild.getLatitude(), randomLocationChild.getLongitude(),
                year, child.getPersonID() + "_death", child.getAssociatedUsername());
        Event parentDeathEvent = new Event("death", parent.getPersonID(), randomLocationParent.getCity(),
                randomLocationParent.getCountry(), randomLocationParent.getLatitude(), randomLocationParent.getLongitude(),
                year, parent.getPersonID() + "_death", parent.getAssociatedUsername());
        Event[] deathEvents = {childDeathEvent, parentDeathEvent};
        return deathEvents;
    }


}
