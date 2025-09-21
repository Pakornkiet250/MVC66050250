package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private JTable candidateTable;
    private DefaultTableModel tableModel;
    private JButton viewDetailsButton;
    private JButton logoutButton;

    public AdminView() {
        setTitle("Admin Dashboard - All Candidates");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        String[] columnNames = {"ID", "Full Name", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        candidateTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(candidateTable);

        // Button setup
        JPanel buttonPanel = new JPanel();
        viewDetailsButton = new JButton("View Details");
        logoutButton = new JButton("Logout");
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(logoutButton);

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public JTable getCandidateTable() {
        return candidateTable;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addViewDetailsListener(ActionListener listener) {
        viewDetailsButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}