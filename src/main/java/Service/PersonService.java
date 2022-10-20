package Service;

import Model.Person;

public class PersonService {
    //Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
    /**
     * Gets the single Person object with the specified ID
     * @param authToken
     * @return person
     */

    private String personID;
    private String authToken;
    private Person person;
    public PersonService() {

    }

    public Person getPerson(String personID, String authToken) {
        return person;
    }



}
