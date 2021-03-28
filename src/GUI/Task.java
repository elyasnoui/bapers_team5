package GUI;

import System.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
    private JTextField discountRateField;
    private JTextField staffIDField;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JScrollPane tablePanel;
    private JButton availableTaskButton;
    private JComboBox createTaskComboBox;
    private JLabel createTotalLabel;
    private JLabel createJobIDValue;
    private JButton createLookupButton;
    private JPanel jobLookupPanel;
    private JTextField lookupFromTextField;
    private JScrollPane jobScrollPane;
    private JTable jobTable;
    private JButton lookupSelectButton;
    private JButton lookupCancelButton;
    private JTextField lookupToTextField;
    private JLabel createTechnicianLabel;
    private int technicianID;
    private JPanel editPanel;
    private JComboBox editTaskComboBox;
    private JLabel editJobIDValue;
    private JLabel editPriceLabel;
    private JLabel editTotalLabel;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private ImageIcon bannerIcon;
    private Bapers system;
    private List<String[]> availableTaskData;
    private List<String[]> taskData;
    private List<String[]> jobLookupData;
    private List<String[]> technicianData;
    private String[] jobRow;
    private String[] taskRow;
    private double jobPrice;

    private static DecimalFormat df2 = new DecimalFormat("0.00");

    private String dataRegex = "(?:(?:31(-)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(-)(?:0?[13-9]|1[0-2])\\2))" +
            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(-)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]" +
            "|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(-)(?:(?:0?[1-9" +
            "])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)\\d{2})";

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    private final LineBorder borderError = new LineBorder(Color.RED, 1);

    private final String[] tableColumns = {
            "ID",
            "Available Task ID",
            "Job ID",
            "Description",
            "Department",
            "Date",
            "Time Taken",
            "Price",
            "Staff ID",
            "Completed"
    };
    private final String[] lookupColumns = {
            "Job ID",
            "Start Date",
            "Customer Name"
    };

    public Task(Bapers system) {
        this.system = system;

        try {
            taskData = DatabaseConnection.getData("task");
            assert taskData != null;
            for (String[] ts : taskData) {
                ts[5] = ts[5].substring(0,10);
                ts[7] = '£' + ts[7];
                ts[9] = ts[9].equals("true") ? "Yes" : "No";
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/tasks.png");
        bannerLabel.setIcon(bannerIcon);

        // Side panel listeners
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

        addMouseListeners();

        ApplicationWindow.displayTable(table, taskData, tableColumns);

        // Top button panel listeners
        availableTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMouseListeners();
                system.changeScreen("availableTask", mainPanel);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editPanel.isVisible()) editPanel.setVisible(false);
                else { tablePanel.setVisible(false);
                    buttonPanel.setVisible(false); }

                createPanel.setVisible(true);
                resetLookupPanel();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editPanel.isVisible()) editPanel.setVisible(false);
                else {
                    if (taskData.get(table.getSelectedRow())[9].equals("No")) {
                        tablePanel.setVisible(false);
                        buttonPanel.setVisible(false);
                        editPanel.setVisible(true);
                        resetEditPanel();
                    } else {
                        buttonPanel.setVisible(true);
                        tablePanel.setVisible(true);
                        JOptionPane.showMessageDialog(mainPanel, "Task is already completed.");
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskData.get(table.getSelectedRow())[9].equals("No"))
                    deleteRow();
                else JOptionPane.showMessageDialog(mainPanel, "Task is already completed.");
            }
        });

        // Table listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getSelectionModel().getSelectedItemsCount() == 1) {
                    editButton.addMouseListener(ApplicationWindow.mouseListener);
                    editButton.setToolTipText(null);
                    deleteButton.addMouseListener(ApplicationWindow.mouseListener);
                    deleteButton.setToolTipText(null);
                } else if (table.getSelectionModel().getSelectedItemsCount() > 1) {
                    editButton.removeMouseListener(ApplicationWindow.mouseListener);
                    editButton.setToolTipText("Please select only 1 record");
                    deleteButton.removeMouseListener(ApplicationWindow.mouseListener);
                    deleteButton.setToolTipText("Please select only 1 record");
                } else {
                    editButton.removeMouseListener(ApplicationWindow.mouseListener);
                    editButton.setToolTipText("Please select a record");
                    deleteButton.removeMouseListener(ApplicationWindow.mouseListener);
                    deleteButton.setToolTipText("Please select a record");
                }

                editButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
                deleteButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
            }
        });

        // Lookup panel listeners
        createLookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                jobLookupPanel.setVisible(true);

                resetLookupPanel();
            }
        });
        lookupSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobLookupPanel.setVisible(false);
                createPanel.setVisible(true);

                availableTaskData = DatabaseConnection.getData("availableTask");
                String[] tasks = new String[availableTaskData.size()+1];
                tasks[0] = "Please select a task";
                int count = 1;
                for (String[] ats : availableTaskData) {
                    tasks[count] = ats[1];
                    count++;
                }
                createTaskComboBox.setModel(new DefaultComboBoxModel<>(tasks));

                createJobIDValue.setText(jobLookupData.get(jobTable.getSelectedRow())[0]);
                jobRow = DatabaseConnection.getRowBySingleID("job", Integer.parseInt(createJobIDValue.getText()));

                createTotalLabel.setText('£'+df2.format(Double.parseDouble(jobLookupData.get(jobTable.getSelectedRow())[3])) +
                        " before VAT/Discounts");

                technicianData = DatabaseConnection.getTechnicians();
                Random r = new Random();
                int result = r.nextInt(technicianData.size()-1);
                createTechnicianLabel.setText(technicianData.get(result)[0]+", "+technicianData.get(result)[1]);
                technicianID = Integer.parseInt(technicianData.get(result)[2]);
            }
        });
        lookupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobLookupPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
            }
        });

        // Create panel listeners
        createTaskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobPrice = Double.parseDouble(jobLookupData.get(jobTable.getSelectedRow())[3]);

                if (createTaskComboBox.getSelectedIndex() == 0)
                    createConfirmButton.setToolTipText("Please select a task");
                else {
                    jobPrice += Double.parseDouble(availableTaskData.get(createTaskComboBox.getSelectedIndex() - 1)[4]);
                    createConfirmButton.setToolTipText(null);
                }
                createTotalLabel.setText('£'+df2.format(jobPrice) + " before VAT/Discounts (new job price)");
                createConfirmButton.setEnabled(createTaskComboBox.getSelectedIndex() > 0);
            }
        });
        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime deadline = LocalDateTime.of(
                        Integer.parseInt(jobRow[5].substring(0,4)),      //year
                        Integer.parseInt(jobRow[5].substring(5,7)),      //month
                        Integer.parseInt(jobRow[5].substring(8,10)),     //day
                        Integer.parseInt(jobRow[5].substring(11,13)),    //hour
                        Integer.parseInt(jobRow[5].substring(14,16))     //minute
                );

                // Delaying urgent deadline if another task is added
                if (Integer.parseInt(jobRow[1]) > 0)
                    deadline = deadline.plusMinutes(Long.parseLong(
                            taskData.get(createTaskComboBox.getSelectedIndex() - 1)[3])
                    );

                try {
                    String[] data = availableTaskData.get(createTaskComboBox.getSelectedIndex()-1);
                    if (DatabaseConnection.editJob(Integer.parseInt(createJobIDValue.getText()), Integer.parseInt(jobRow[1]), jobPrice, deadline, jobRow[6])
                    && DatabaseConnection.addTask(Integer.parseInt(data[0]), Integer.parseInt(createJobIDValue.getText()), data[1], data[2], data[3],
                            Double.parseDouble(data[4]), technicianID, 0))
                        system.changeScreen("tasks", mainPanel);

                    else JOptionPane.showMessageDialog(mainPanel, "Couldn't create task");
                } catch (SQLException exception) { exception.printStackTrace(); }
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                jobLookupPanel.setVisible(true);
            }
        });
        lookupFromTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!lookupFromTextField.getText().matches(dataRegex)) {
                    lookupFromTextField.setBorder(borderError);
                    lookupFromTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
                } else {
                    lookupFromTextField.setBorder(null);
                    lookupFromTextField.setToolTipText(null);

                    if (lookupToTextField.getText().matches(dataRegex))
                        jobSearch();
                }

                lookupSelectButton.setEnabled(jobTable.getSelectionModel().getSelectedItemsCount() == 1);
            }
        });
        lookupToTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!lookupToTextField.getText().matches(dataRegex)) {
                    lookupToTextField.setBorder(borderError);
                    lookupToTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
                } else {
                    lookupToTextField.setBorder(null);
                    lookupToTextField.setToolTipText(null);

                    if (lookupFromTextField.getText().matches(dataRegex))
                        jobSearch();
                }

                lookupSelectButton.setEnabled(jobTable.getSelectionModel().getSelectedItemsCount() == 1);
            }
        });
        jobTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lookupSelectButton.setEnabled(jobTable.getSelectionModel().getSelectedItemsCount() == 1);

                if (lookupSelectButton.isEnabled()) lookupSelectButton.setToolTipText(null);
                else lookupSelectButton.setToolTipText("Please search and select a row");

                if (jobTable.getSelectionModel().getSelectedItemsCount() > 1)
                    jobTable.getSelectionModel().clearSelection();
            }
        });

        // Edit panel buttons
        editConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime deadline = LocalDateTime.of(
                        Integer.parseInt(jobRow[5].substring(0,4)),      //year
                        Integer.parseInt(jobRow[5].substring(5,7)),      //month
                        Integer.parseInt(jobRow[5].substring(8,10)),     //day
                        Integer.parseInt(jobRow[5].substring(11,13)),    //hour
                        Integer.parseInt(jobRow[5].substring(14,16))     //minute
                );

                // Adding/subtracting urgent deadline if an existing task is edited
                if (Integer.parseInt(jobRow[1]) > 0)
                    for (String[] ats : availableTaskData)
                        if (ats[1].equals(editTaskComboBox.getSelectedItem())) {
                            int offset = Integer.parseInt(jobRow[6]) - Integer.parseInt(ats[3]);

                            if (offset > -1) deadline = deadline.minusMinutes(Math.abs(offset));
                            else deadline = deadline.plusMinutes(Math.abs(offset));

                            break;
                        }

                try {
                    String[] data = availableTaskData.get(editTaskComboBox.getSelectedIndex());
                    if (DatabaseConnection.editJob(Integer.parseInt(editJobIDValue.getText()), Integer.parseInt(jobRow[1]), jobPrice, deadline, jobRow[6])
                            && DatabaseConnection.editTask(Integer.parseInt(taskRow[0]), Integer.parseInt(data[0]), Integer.parseInt(editJobIDValue.getText()),
                            data[1], data[2], data[3], Double.parseDouble(data[4]), Integer.parseInt(taskRow[8]), 0))
                        system.changeScreen("tasks", mainPanel);
                    else JOptionPane.showMessageDialog(mainPanel, "Couldn't create task");
                } catch (SQLException exception) { exception.printStackTrace(); }
            }
        });
        editCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
            }
        });
        editTaskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String[] ats : availableTaskData)
                    if (ats[1].equals(editTaskComboBox.getSelectedItem())) {
                        jobPrice = (Double.parseDouble(jobRow[2]) - Double.parseDouble(taskRow[7].substring(1))) +
                                Double.parseDouble(ats[4]);
                        editPriceLabel.setText('£'+df2.format(Double.parseDouble(ats[4])));
                        editTotalLabel.setText('£'+df2.format(jobPrice) + " before VAT/Discounts (new job price)");
                        break;
                    }
            }
        });
    }

    private void deleteRow() {
        try {
            String jobID = taskData.get(table.getSelectedRow())[2];
            if (DatabaseConnection.removeTask(Integer.parseInt(taskData.get(table.getSelectedRow())[0]))) {
                boolean jobsLeft = false;

                for (String[] ts : taskData)
                    if (!ts[0].equals(taskData.get(table.getSelectedRow())[0]) && ts[2].equals(jobID)) {
                        jobsLeft = true;
                        break;
                    }

                if (!jobsLeft)
                    if (!DatabaseConnection.removeJob(Integer.parseInt(jobID)))
                        JOptionPane.showMessageDialog(mainPanel, "Couldn't delete job");

                system.changeScreen("tasks", mainPanel);
            } else JOptionPane.showMessageDialog(mainPanel, "Couldn't delete task");
        } catch (SQLException exception) { exception.printStackTrace(); }
    }

    private void resetEditPanel() {
        taskRow = taskData.get(table.getSelectedRow());

        jobRow = DatabaseConnection.getRowBySingleID("job", Integer.parseInt(taskRow[2]));
        assert jobRow != null;

        availableTaskData = DatabaseConnection.getData("availableTask");
        String[] tasks = new String[availableTaskData.size()];
        int count = 0;
        for (String[] ats : availableTaskData) {
            tasks[count] = ats[1];
            count++;
        }
        editTaskComboBox.setModel(new DefaultComboBoxModel<>(tasks));
        count = 0;
        for (int i=0; i<editTaskComboBox.getMaximumRowCount(); i++) {
            editTaskComboBox.setSelectedIndex(count);
            if (String.valueOf(editTaskComboBox.getSelectedItem()).equals(taskRow[3])) {
                editTaskComboBox.setSelectedIndex(i);
                break;
            }
            count++;
        }

        editJobIDValue.setText(taskRow[2]);
    }

    private void resetLookupPanel() {
        lookupFromTextField.setText("");
        lookupFromTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
        lookupToTextField.setText("");
        lookupToTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
        jobTable.setModel(new DefaultTableModel(null, lookupColumns));
        lookupSelectButton.setEnabled(false);
    }

    private void jobSearch() {
        try {
            jobLookupData = DatabaseConnection.searchJobs(sdf2.format(sdf.parse(lookupFromTextField.getText())),
                    sdf2.format(sdf.parse(lookupToTextField.getText())));
            assert jobLookupData != null;
            for (String[] js : jobLookupData)
                if (!(js[2] == null))
                    js[2] = DatabaseConnection.getCustomerName(Integer.parseInt(js[2]));
            ApplicationWindow.displayTable(jobTable, jobLookupData, lookupColumns);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void addMouseListeners() {
        logoutButton.addMouseListener(ApplicationWindow.mouseListener);
        jobsButton.addMouseListener(ApplicationWindow.mouseListener);
        customerButton.addMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.addMouseListener(ApplicationWindow.mouseListener);
        staffButton.addMouseListener(ApplicationWindow.mouseListener);
        tasksButton.addMouseListener(ApplicationWindow.mouseListener);
        reportsButton.addMouseListener(ApplicationWindow.mouseListener);
        databaseButton.addMouseListener(ApplicationWindow.mouseListener);
        availableTaskButton.addMouseListener(ApplicationWindow.mouseListener);
        createButton.addMouseListener(ApplicationWindow.mouseListener);
    }

    private void removeMouseListeners() {
        logoutButton.removeMouseListener(ApplicationWindow.mouseListener);
        jobsButton.removeMouseListener(ApplicationWindow.mouseListener);
        customerButton.removeMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.removeMouseListener(ApplicationWindow.mouseListener);
        staffButton.removeMouseListener(ApplicationWindow.mouseListener);
        tasksButton.removeMouseListener(ApplicationWindow.mouseListener);
        reportsButton.removeMouseListener(ApplicationWindow.mouseListener);
        databaseButton.removeMouseListener(ApplicationWindow.mouseListener);
        availableTaskButton.removeMouseListener(ApplicationWindow.mouseListener);
        createButton.removeMouseListener(ApplicationWindow.mouseListener);
        editButton.removeMouseListener(ApplicationWindow.mouseListener);
        deleteButton.removeMouseListener(ApplicationWindow.mouseListener);
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
