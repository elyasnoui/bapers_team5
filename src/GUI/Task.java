package GUI;

import System.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Task {
    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable taskTable;
    private JPanel mainPanel;
    private JButton createButton;
    private JLabel bannerLabel;
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
    private JPanel buttonPanel;
    private JTable table;
    private JPanel createPanel;
    private JTextField jobIDField;
    private JTextField descriptionField;
    private JTextField departmentField;
    private JTextField dateField;
    private JTextField timeTakenField;
    private JTextField priceField;
    private JTextField discountRateField;
    private JTextField staffIDField;
    private JButton popupCreateButton;
    private JButton popupCancelButton;
    private JScrollPane tablePanel;
    private JLabel jobIDEX;
    private JLabel descriptionEX;
    private JLabel departmentEX;
    private JLabel dateEX;
    private JLabel timeTakenEX;
    private JLabel priceEX;
    private JLabel discountRateEX;
    private JLabel staffIDEX;
    private ImageIcon bannerIcon;
    private Bapers system;
    private List<String[]> taskData;
    private final String[] tableColumns = {
            "ID",
            "Job ID",
            "Description",
            "Department",
            "Date",
            "Time Taken",
            "Price",
            "Discount Rate",
            "Staff ID",
            "Completed"
    };

    private boolean error = false;

    public Task(Bapers system) {
        this.system = system;

        try {
            taskData = DatabaseConnection.getData("task");
            assert taskData != null;
            for (String[] ts : taskData) {
                ts[4] = ts[4].substring(0,10);
                ts[6] = '£' + ts[6];
                ts[7] += '%';
                ts[9] = ts[9].equals("true") ? "Yes" : "No";
            }
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/tasks.png");
        bannerLabel.setIcon(bannerIcon);

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

        ApplicationWindow.displayTable(table, taskData, tableColumns);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });
        popupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(true);
                buttonPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });
        popupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String jobIDREX ="^[1-9]{1,20}";
                final String descriptionREX = "^";
                final String departmentREX = "Copy Room|Development Area|Packing Department|Finish Room";
                final String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                final String agreed_regex = "Variable|Flexible|Fixed";
                final String variable_regex = /*"([0-9]{1,2}(,[0-9]{1,2})){7}";*/ "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$";// Need to be made dynamic according to number of tasks
                final String fixed_regex = "^[0-9]{1,2}";
                final String flexible_regex = "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$";

                // Hide all '!', before checking for errors
                jobIDEX.setVisible(false);
                descriptionEX.setVisible(false);
                departmentEX.setVisible(false);
                dateEX.setVisible(false);
                timeTakenEX.setVisible(false);
                priceEX.setVisible(false);
                discountRateEX.setVisible(false);
                staffIDEX.setVisible(false);

                // Check All fields against Regex Strings
                if (!jobIDField.getText().matches(jobIDREX)){
                    error = true;
                    jobIDEX.setVisible(true);
                }
                if (!descriptionField.getText().matches(jobIDREX)) {
                    error = true;
                    descriptionEX.setVisible(true);
                }
                if (!departmentField.getText().matches(descriptionREX)) {
                    error = true;
                    departmentEX.setVisible(true);
                }
                if (!dateField.getText().matches(departmentREX)) {
                    error = true;
                    dateEX.setVisible(true);
                }
                if (!timeTakenField.getText().matches(email_regex)) {
                    error = true;
                    timeTakenEX.setVisible(true);
                }
                if (!discountRateField.getText().matches(email_regex)) {
                    error = true;
                    discountRateEX.setVisible(true);
                }
                if (!staffIDField.getText().matches(email_regex)) {
                    error = true;
                    staffIDEX.setVisible(true);
                }


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

    public int getRows(){return table.getRowCount();
    }


}
