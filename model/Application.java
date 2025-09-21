package model;

import java.time.LocalDate;

public class Application {
    private String jobId;
    private String candidateId;
    private LocalDate applicationDate;

    public Application(String jobId, String candidateId, LocalDate applicationDate) {
        this.jobId = jobId;
        this.candidateId = candidateId;
        this.applicationDate = applicationDate;
    }

    // Getters
    public String getJobId() { return jobId; }
    public String getCandidateId() { return candidateId; }
    public LocalDate getApplicationDate() { return applicationDate; }
}