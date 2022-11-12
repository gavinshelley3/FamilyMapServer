package Service;

import Model.Person;
import TreeObjects.fnames;
import TreeObjects.mnames;
import TreeObjects.snames;

import java.util.Objects;

public class PersonGenerator {

    public PersonGenerator() {

    }

    public static Person person(String username, String gender, snames surnames, fnames femaleNames,
                                mnames maleNames) {
        gender = gender.toLowerCase();
        if (gender.equals("m") || gender.equals("f")) {
            Person person = new Person();
            person.setGender(gender);

            if (gender.equals("m")) {
                person.setFirstName(maleNames.getRandomName());
            } else {
                person.setFirstName(femaleNames.getRandomName());
            }

            person.setAssociatedUsername(username);
            person.setLastName(surnames.getRandomSname());
            person.setPersonID(person.getFirstName() + "_" + person.getLastName());

            return person;
        }
        else {
            return null;
        }
    }

    public static Person person(String username, Person mom, Person dad, String gender, snames surnames,
                                fnames femaleNames, mnames maleNames) {
        if (gender != null) {
            gender = gender.toLowerCase();
        }
        if (Objects.equals(gender, "m") || Objects.equals(gender, "f")) {
            Person person = new Person();
            person.setGender(gender);

            if (gender.equals("m")) {
                person.setFirstName(maleNames.getRandomName());
            } else {
                person.setFirstName(femaleNames.getRandomName());
            }

            person.setAssociatedUsername(username);
            person.setLastName(surnames.getRandomSname());
            person.setPersonID(person.getFirstName() + "_" + person.getLastName());
            person.setFatherID(dad.getPersonID());
            person.setMotherID(mom.getPersonID());
            person.setPersonID(person.getFirstName() + "_" + person.getLastName());

            return person;
        }
        else {
            return null;
        }
    }

}
