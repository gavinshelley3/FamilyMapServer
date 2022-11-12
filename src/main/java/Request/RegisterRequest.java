package Request;

import Model.User;


public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    public RegisterRequest() {

    }


    public RegisterRequest register(User user) {
        // Create new user
        RegisterRequest request = new RegisterRequest();
        request.username = user.getUsername();
        request.password = user.getPassword();
        request.email = user.getEmail();
        request.firstName = user.getFirstName();
        request.lastName = user.getLastName();
        request.gender = user.getGender();
        return request;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String generatePersonID() {
        String personID;
        personID = firstName + "_" + lastName;
        return personID;
    }

}
