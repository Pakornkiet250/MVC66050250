package Controller;

import model.*;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppController {
    private LoginView loginView;
    private AuthController authController;

    public AppController(LoginView loginView, AuthController authController) {
        this.loginView = loginView;
        this.authController = authController;

        
        this.loginView.addLoginListener(new LoginListener());
    }

    
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmail();
            String password = loginView.getPassword();
            User user = authController.login(email, password);

            if (user != null) {
                loginView.dispose(); 
                if ("ADMIN".equals(user.getRole())) {
                    showAdminDashboard();
                } else if ("CANDIDATE".equals(user.getRole())) {
                    showCandidateDashboard((Candidate) user);
                }
            } else {
                loginView.showMessage("Login failed. Invalid email or password.");
            }
        }
    }

    private void showAdminDashboard() {
        AdminView adminView = new AdminView();
        loadCandidatesToTable(adminView);
        
        
        adminView.addLogoutListener(e -> {
            adminView.dispose();
            showLoginScreen();
        });

        // View Details Logic
        adminView.addViewDetailsListener(e -> {
            int selectedRow = adminView.getCandidateTable().getSelectedRow();
            if (selectedRow >= 0) {
                String candidateId = (String) adminView.getTableModel().getValueAt(selectedRow, 0);
                Candidate candidate = findCandidateById(candidateId);
                if (candidate != null) {
                    showCandidateDetails(candidate);
                }
            } else {
                adminView.showMessage("Please select a candidate to view details.");
            }
        });

        adminView.setVisible(true);
    }

    private void showCandidateDashboard(Candidate candidate) {
        CandidateView candidateView = new CandidateView();
        loadOpenJobsToTable(candidateView); 

        
        candidateView.addLogoutListener(e -> {
            candidateView.dispose();
            showLoginScreen();
        });
        
        
        candidateView.addApplyListener(e -> {
            int selectedRow = candidateView.getJobTable().getSelectedRow();
            if (selectedRow >= 0) {
                applyForJob(selectedRow, candidate, candidateView);
            } else {
                candidateView.showMessage("Please select a job to apply for.");
            }
        });

        
        candidateView.addJobTableMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    int selectedRow = candidateView.getJobTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        applyForJob(selectedRow, candidate, candidateView);
                    }
                }
            }
        });

        candidateView.setVisible(true);
    }
    
    
    private void applyForJob(int selectedRow, Candidate candidate, CandidateView view) {
        List<Job> openJobs = getOpenJobs();
        Job selectedJob = openJobs.get(selectedRow); // ดึง Job object จาก list โดยใช้ index ของแถวที่เลือก

        // Business logic
        if (LocalDate.now().isAfter(selectedJob.getDeadline())) {
            view.showMessage("Application failed: The application deadline has passed.");
            return;
        }
        boolean alreadyApplied = Database.applications.stream()
            .anyMatch(app -> app.getCandidateId().equals(candidate.getUserId()) && app.getJobId().equals(selectedJob.getJobId()));
        if (alreadyApplied) {
            view.showMessage("Application failed: You have already applied for this job.");
            return;
        }

        Application newApp = new Application(selectedJob.getJobId(), candidate.getUserId(), LocalDate.now());
        Database.applications.add(newApp);
        view.showMessage("Application successful for " + selectedJob.getTitle() + "!");
    }
    
   

    private void loadCandidatesToTable(AdminView view) {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0); // Clear existing data
        List<Candidate> sortedCandidates = Database.candidates.stream()
                .sorted(Comparator.comparing(Candidate::getFullName))
                .collect(Collectors.toList());
        
        for (Candidate c : sortedCandidates) {
            model.addRow(new Object[]{c.getUserId(), c.getFullName(), c.getEmail()});
        }
    }

    private void loadOpenJobsToTable(CandidateView view) {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0); // เคลียร์ข้อมูลเก่า
        
        getOpenJobs().forEach(job -> {
             Company company = findCompanyById(job.getCompanyId());
             tableModel.addRow(new Object[]{job.getTitle(), company.getName(), job.getDeadline().toString()});
        });
    }

    private void showCandidateDetails(Candidate candidate) {
        List<String> appliedJobsInfo = Database.applications.stream()
                .filter(app -> app.getCandidateId().equals(candidate.getUserId()))
                .map(app -> {
                    Job job = findJobById(app.getJobId());
                    Company company = findCompanyById(job.getCompanyId());
                    return String.format("• %s at %s (Applied on: %s)", job.getTitle(), company.getName(), app.getApplicationDate());
                })
                .collect(Collectors.toList());

        StringBuilder details = new StringBuilder();
        details.append("ID: ").append(candidate.getUserId()).append("\n");
        details.append("Name: ").append(candidate.getFullName()).append("\n");
        details.append("Email: ").append(candidate.getEmail()).append("\n\n");
        details.append("Applied Jobs:\n");

        if (appliedJobsInfo.isEmpty()) {
            details.append("- No applications found.");
        } else {
            details.append(String.join("\n", appliedJobsInfo));
        }
        
        JOptionPane.showMessageDialog(null, details.toString(), "Candidate Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showLoginScreen() {
        LoginView newLoginView = new LoginView();
        new AppController(newLoginView, new AuthController());
        newLoginView.setVisible(true);
    }
    
   
    private List<Job> getOpenJobs() {
         return Database.jobs.stream()
                .filter(job -> "Open".equals(job.getStatus()))
                .sorted(Comparator.comparing(Job::getTitle))
                .collect(Collectors.toList());
    }
    private Candidate findCandidateById(String id) {
        return Database.candidates.stream().filter(c -> c.getUserId().equals(id)).findFirst().orElse(null);
    }
    private Job findJobById(String id) {
        return Database.jobs.stream().filter(j -> j.getJobId().equals(id)).findFirst().orElse(null);
    }
    private Company findCompanyById(String id) {
        return Database.companies.stream().filter(c -> c.getCompanyId().equals(id)).findFirst().orElse(null);
    }
}