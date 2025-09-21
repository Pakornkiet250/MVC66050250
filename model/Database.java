package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public static final List<Company> companies = new ArrayList<>();
    public static final List<Job> jobs = new ArrayList<>();
    public static final List<Candidate> candidates = new ArrayList<>();
    public static final List<Application> applications = new ArrayList<>();
    public static final Map<String, User> users = new HashMap<>(); // For login

    static {
      

        // Companies
        Company c1 = new Company("11110001", "Google", "contact@google.com", "Bangkok");
        Company c2 = new Company("22220002", "Agoda", "hr@agoda.com", "Bangkok");
        companies.add(c1);
        companies.add(c2);

        // Jobs 
        jobs.add(new Job("50000001", "Software Engineer", "Develop amazing products.", c1.getCompanyId(), LocalDate.of(2025, 10, 31), "Open"));
        jobs.add(new Job("50000002", "Backend Developer", "Work with APIs and databases.", c1.getCompanyId(), LocalDate.of(2025, 11, 15), "Open"));
        jobs.add(new Job("50000003", "Frontend Developer", "Create beautiful UIs.", c1.getCompanyId(), LocalDate.of(2025, 10, 20), "Open"));
        jobs.add(new Job("50000004", "Data Scientist", "Analyze large datasets.", c1.getCompanyId(), LocalDate.of(2025, 9, 30), "Closed"));
        jobs.add(new Job("60000001", "Product Manager", "Lead product development.", c2.getCompanyId(), LocalDate.of(2025, 11, 5), "Open"));
        jobs.add(new Job("60000002", "DevOps Engineer", "Manage cloud infrastructure.", c2.getCompanyId(), LocalDate.of(2025, 12, 1), "Open"));
        jobs.add(new Job("60000003", "QA Tester", "Ensure software quality.", c2.getCompanyId(), LocalDate.of(2025, 10, 25), "Open"));
        jobs.add(new Job("60000004", "UX/UI Designer", "Design user-friendly interfaces.", c2.getCompanyId(), LocalDate.of(2025, 11, 10), "Open"));
        jobs.add(new Job("60000005", "Java Developer", "Develop scalable services.", c2.getCompanyId(), LocalDate.of(2025, 11, 20), "Open"));
        jobs.add(new Job("60000006", "Data Analyst", "Generate insights from data.", c2.getCompanyId(), LocalDate.of(2025, 10, 15), "Open"));

        // Candidates 
        addCandidate("80000001", "candidate1@mail.com", "pass1", "Peter", "Jones");
        addCandidate("80000002", "candidate2@mail.com", "pass2", "Mary", "Jane");
        addCandidate("80000003", "candidate3@mail.com", "pass3", "John", "Smith");
        addCandidate("80000004", "candidate4@mail.com", "pass4", "David", "Miller");
        addCandidate("80000005", "candidate5@mail.com", "pass5", "Sarah", "Wilson");
        addCandidate("80000006", "candidate6@mail.com", "pass6", "Michael", "Brown");
        addCandidate("80000007", "candidate7@mail.com", "pass7", "Linda", "Davis");
        addCandidate("80000008", "candidate8@mail.com", "pass8", "James", "Garcia");
        addCandidate("80000009", "candidate9@mail.com", "pass9", "Emma", "Martinez");
        addCandidate("80000010", "candidate10@mail.com", "pass10", "William", "Lee");

        // Admin (1)
        User admin = new User("99999999", "admin@jobfair.com", "admin123", "ADMIN");
        users.put(admin.getEmail(), admin);
    }
    
    private static void addCandidate(String id, String email, String pass, String fname, String lname) {
        Candidate c = new Candidate(id, email, pass, fname, lname);
        candidates.add(c);
        users.put(email, c);
    }
}