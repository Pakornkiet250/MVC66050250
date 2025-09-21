package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CandidateView extends JFrame {
    private JList<String> jobList;
    private DefaultListModel<String> listModel;
    private JButton applyButton;
    private JButton logoutButton;

    public CandidateView() {
        setTitle("Candidate Dashboard - Open Jobs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        jobList = new JList<>(listModel);
        jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jobList);

        JPanel buttonPanel = new JPanel();
        applyButton = new JButton("Apply for Selected Job");
        logoutButton = new JButton("Logout");
        buttonPanel.add(applyButton);
        buttonPanel.add(logoutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public JList<String> getJobList() {
        return jobList;
    }
    
    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void addApplyListener(ActionListener listener) {
        applyButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}