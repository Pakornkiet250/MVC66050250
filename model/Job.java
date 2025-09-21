package model;

import java.time.LocalDate;

public class Job {
    private String jobId;
    private String title;
    private String description;
    private String companyId;
    private LocalDate deadline;
    private String status; // "Open" or "Closed"

    public Job(String jobId, String title, String description, String companyId, LocalDate deadline, String status) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters
    public String getJobId() { return jobId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCompanyId() { return companyId; }
    public LocalDate getDeadline() { return deadline; }
    public String getStatus() { return status; }
}