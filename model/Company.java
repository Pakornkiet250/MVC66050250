package model;

public class Company {
    private String companyId;
    private String name;
    private String email;
    private String location;

    public Company(String companyId, String name, String email, String location) {
        this.companyId = companyId;
        this.name = name;
        this.email = email;
        this.location = location;
    }

    // Getters
    public String getCompanyId() { return companyId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return name;
    }
}