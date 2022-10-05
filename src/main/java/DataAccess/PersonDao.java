package DataAccess;

public class PersonDao {
    public void add(Person person) {
        System.out.println("Person added: " + person.getFirstName());
    }
}
