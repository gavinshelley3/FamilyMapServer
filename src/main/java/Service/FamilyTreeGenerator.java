package Service;

import Model.Person;
import Model.User;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.sql.Connection;


public class FamilyTreeGenerator {
    Gson gson = new Gson();
    int peopleCount = 0;
    int eventCount = 0;
    int generationInitial;
    public FamilyTreeGenerator() {

    }

    public void generateData() {

    }

    Person generatePerson(String gender, int generations) {
        Person mother = null;
        Person father = null;
        if (generations > 1) {
            mother = generatePerson("F", generations - 1);
            father = generatePerson("M", generations - 1);
        }
        Person person= new Person();
        return person;
    }

    public void generateFamilyTree(String username, String gender, int generations, User user, Connection connection) throws FileNotFoundException {

    }

    public String getPeopleCount() {
        return "";
    }

    public String getEventCount() {
        return "";
    }
}
