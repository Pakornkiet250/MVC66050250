package model;

public class User {
    private String userId;
    private String email;
    private String password; // Simple password check
    private String role; // "ADMIN" or "CANDIDATE"

    public User(String userId, String email, String password, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}