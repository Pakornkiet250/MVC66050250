package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class CandidateView extends JFrame {
    private JTable jobTable; 
    private DefaultTableModel tableModel;
    private JButton applyButton;
    private JButton logoutButton;

    public CandidateView() {
        setTitle("Candidate Dashboard - Open Jobs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        String[] columnNames = {"Job Title", "Company", "Deadline"};
        tableModel = new DefaultTableModel(columnNames, 0) {
           
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jobTable = new JTable(tableModel);
        jobTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // เลือกได้ทีละแถว
        jobTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jobTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(jobTable);
        

        JPanel buttonPanel = new JPanel();
        applyButton = new JButton("Apply for Selected Job");
        logoutButton = new JButton("Logout");
        buttonPanel.add(applyButton);
        buttonPanel.add(logoutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
   
    public JTable getJobTable() {
        return jobTable;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

   
    public void addApplyListener(ActionListener listener) {
        applyButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
    
    
    public void addJobTableMouseListener(MouseListener listener) {
        jobTable.addMouseListener(listener);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}