package GUI;

import System.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Job {
    private Bapers system;
    private JPanel sidePanel;
    private JLabel usernameLabel;
    private JLabel roleLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel contentPanel;
    private ImageIcon bannerIcon;
    private JLabel bannerLabel;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JTable table;
    private JPanel mainPanel;
    private JPanel createPanel;
    private JButton popupCancelButton;
    private JScrollPane tablePanel;
    private JTextField priceField;
    private JTextField statusField;
    private JTextField customerIDField;
    private JButton popupCreateButton;
    private JCheckBox isUrgentCheckBox;
    private JTextField ddField;
    private JTextField mmField;
    private JTextField yyyyField;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    private JPanel deadlinePanel;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private List<String[]> jobData;
    private final String[] tableColumns = {
            "ID",
            "Is Urgent",
            "Price",
            "Start Date",
            "End Date",
            "Deadline",
            "Status",
            "Customer ID"

    };

    public Job(Bapers system) {
        this.system = system;

        try {
            jobData = DatabaseConnection.getData("job");
            assert jobData != null;
            for (String[] js : jobData) {
                js[1] = js[1].equals("true") ? "Yes" : "No";
                js[2] = '£' + js[2];
            }
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/job.png");
        bannerLabel.setIcon(bannerIcon);

        checkBoxIcon = new ImageIcon("data/graphics/test.png");
        selectedCheckBoxIcon = new ImageIcon("data/graphics/test2.png");
        isUrgentCheckBox.setIcon(checkBoxIcon);
        isUrgentCheckBox.setSelectedIcon(selectedCheckBoxIcon);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { system.changeScreen("logout", mainPanel); }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("jobs", mainPanel);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("customers", mainPanel);
            }
        });
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("payments", mainPanel);
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("staff", mainPanel);
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("tasks", mainPanel);
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("reports", mainPanel);
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("database", mainPanel);
            }
        });

        logoutButton.addMouseListener(ApplicationWindow.mouseListener);
        jobsButton.addMouseListener(ApplicationWindow.mouseListener);
        customerButton.addMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.addMouseListener(ApplicationWindow.mouseListener);
        staffButton.addMouseListener(ApplicationWindow.mouseListener);
        tasksButton.addMouseListener(ApplicationWindow.mouseListener);
        reportsButton.addMouseListener(ApplicationWindow.mouseListener);
        databaseButton.addMouseListener(ApplicationWindow.mouseListener);
        createButton.addMouseListener(ApplicationWindow.mouseListener);
        editButton.addMouseListener(ApplicationWindow.mouseListener);
        deleteButton.addMouseListener(ApplicationWindow.mouseListener);

        ApplicationWindow.displayTable(table, jobData, tableColumns);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);

                // Adding key listeners for date filtering
                priceField.addKeyListener(ApplicationWindow.inputValidator);

                for (int i=0; i<3; i++) {
                    startDatePanel.getComponents()[i].addKeyListener(ApplicationWindow.inputValidator);
                    endDatePanel.getComponents()[i].addKeyListener(ApplicationWindow.inputValidator);
                    deadlinePanel.getComponents()[i].addKeyListener(ApplicationWindow.inputValidator);
                }
            }
        });
        popupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);

                // Removing key listeners when no longer needed
                priceField.removeKeyListener(ApplicationWindow.inputValidator);

                for (int i=0; i<3; i++) {
                    startDatePanel.getComponents()[i].removeKeyListener(ApplicationWindow.inputValidator);
                    endDatePanel.getComponents()[i].removeKeyListener(ApplicationWindow.inputValidator);
                    deadlinePanel.getComponents()[i].removeKeyListener(ApplicationWindow.inputValidator);
                }
            }
        });
        popupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String[]> customers = DatabaseConnection.getData("customer");
                assert customers != null;
                for (String[] cs : customers) {
                    if (cs[0].equals(customerIDField.getText())) {
                        //DatabaseConnection.addJob(isUrgentCheckBox.isSelected(), priceField.getText(), )
                        return;
                    }
                }
                JOptionPane.showMessageDialog(mainPanel, "Customer ID does not exist.");
            }
        });
        popupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getUsername() {
        return usernameLabel;
    }

    public void setUsername(String username) {
        this.usernameLabel.setText(username);
    }

    public JLabel getRole() {
        return roleLabel;
    }

    public void setRole(String role) {
        this.roleLabel.setText(role);
    }
}
