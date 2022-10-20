package Result;

import Model.Person;

import java.lang.reflect.Array;

public class PersonResult {
    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private String message;
    private boolean success;
    private PersonResult result;
    public PersonResult() {

    }
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender,
     String fatherID, String motherID, String spouseID, String message, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.message = message;
        this.success = success;
    }

    public PersonResult person(String associatedUsername, String personID, String firstName, String lastName,
                            String gender,
                       String fatherID, String motherID, String spouseID, String message, boolean success) {
        PersonResult result = new PersonResult();
        result.associatedUsername = associatedUsername;
        result.personID = personID;
        result.firstName = firstName;
        result.lastName = lastName;
        result.gender = gender;
        result.fatherID = fatherID;
        result.motherID = motherID;
        result.spouseID = spouseID;
        result.message = message;
        result.success = success;
        return result;
    }

    public Person[] persons() {
        Person[] persons = new Person[0];
        return persons;
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
