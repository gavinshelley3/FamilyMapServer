package Service;

import DataAccess.DataAccessException;
import DataAccess.EventDao;
import DataAccess.PersonDao;
import Model.Event;
import Model.Person;
import Model.User;
import TreeObjects.LocationData;
import TreeObjects.fnames;
import TreeObjects.mnames;
import TreeObjects.snames;
import com.google.gson.Gson;
import java.util.Random;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

import static Service.PersonGenerator.person;


public class FamilyTreeGenerator {
    Gson gson = new Gson();
    int peopleCount = 0;
    int eventCount = 0;
    int initialGeneration;
    LocationData locationData = new LocationData();
    snames surNames = new snames();
    fnames femaleNames = new fnames();
    mnames maleNames = new mnames();
    public FamilyTreeGenerator() {

    }

    public void generateData() {
        try {
            Reader reader = new FileReader("json/locations.json");
            locationData = (LocationData)gson.fromJson(reader, LocationData.class);

            Reader readerSurnames = new FileReader("json/snames.json");
            surNames = (snames)gson.fromJson(readerSurnames, snames.class);

            Reader readerFemaleNames = new FileReader("json/fnames.json");
            femaleNames = (fnames)gson.fromJson(readerFemaleNames, fnames.class);

            Reader readerMaleNames = new FileReader("json/mnames.json");
            maleNames = (mnames)gson.fromJson(readerMaleNames, mnames.class);

            System.out.println("Files found");
        } catch (FileNotFoundException e) {
            System.out.println("Files not found");
            throw new RuntimeException(e);
        }
    }

    Person generatePerson(String gender, int generations) {
        Person mother = null;
        Person father = null;
        if (generations > 1) {
            mother = generatePerson("f", generations - 1);
            father = generatePerson("m", generations - 1);
        }
        Person person= new Person();
        return person;
    }

    public void generateFamilyTree(String username, String gender, int generations, User user, Connection conn) throws FileNotFoundException {
        System.out.println("Generating family tree...");
        if (generations > 0 || gender == "f" || gender == "m") {
            try {
                generateData();
                int birthYear = 1999;
                int marriageYear = 2024;
                int deathYear = 2075;
                initialGeneration = generations;
                Person person = generatePeople(user, username, gender, generations, birthYear, marriageYear,
                        deathYear, conn);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: error generating family tree");
            }
        }
        else {
            System.out.println("Error: error generating family tree");
        }
    }

    public Person generatePeople(User user, String username, String gender, int generations, int birthYear,
                                  int marriageYear, int deathYear, Connection conn) throws DataAccessException {
        try {
            PersonDao personDao = new PersonDao(conn);
            EventDao eventDao = new EventDao(conn);
            Person mom = null;
            Person dad = null;

            Person person = new Person();

            if (generations >= 1) {
                int randMom = generateRandom();
                int randDad = generateRandom();
                int randMarriage = generateRandom();

                mom = generatePeople(user, username,"f", generations - 1, birthYear - randMom,
                        marriageYear - randMarriage, deathYear - randMom, conn);
                dad = generatePeople(user, username,"m", generations - 1, birthYear - randDad,
                        marriageYear - randMarriage, deathYear - randDad, conn);
                mom.setSpouseID(dad.getPersonID());
                dad.setSpouseID(mom.getPersonID());

                EventGenerator eventGenerator = new EventGenerator();
                Event[] weddings = eventGenerator.marriage(mom, dad, marriageYear, locationData);
                eventCount += 2;

                personDao.insertSpouseID(mom.getSpouseID(), mom.getPersonID());
                personDao.insertSpouseID(dad.getSpouseID(), dad.getPersonID());

                eventDao.insert(weddings[0]);
                eventDao.insert(weddings[1]);

                Event[] deathMarriage = EventGenerator.death(mom, dad, deathYear, locationData);
                eventCount += 2;

                eventDao.insert(deathMarriage[0]);
                eventDao.insert(deathMarriage[1]);
            }


            if (dad == null || mom == null) {
                person = PersonGenerator.person(username,gender, surNames, femaleNames, maleNames);
                peopleCount++;
            }
            else {
                if (initialGeneration == generations) {
                    person = new Person(user.getFirstName(), user.getLastName(), gender, user.getPersonID(),
                            dad.getPersonID(), mom.getPersonID(), null, username);
                } else {
                    person = PersonGenerator.person(username, mom, dad, gender, surNames, femaleNames, maleNames);
                }
                peopleCount++;
                birthYear += generateRandom();
            }


            Event birth = EventGenerator.birth(person, birthYear, locationData);
            eventCount++;

            personDao.insert(person);
            eventDao.insert(birth);
            conn.commit();

            return person;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public int generateRandom() {
        int min = 22;
        int max = 25;
        int rand = (int)Math.floor(Math.random()*(max-min+1)+min);
        return rand;
    }
}
