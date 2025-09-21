package model;

public class Candidate extends User {
    private String firstName;
    private String lastName;

    public Candidate(String userId, String email, String password, String firstName, String lastName) {
        super(userId, email, password, "CANDIDATE");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
}