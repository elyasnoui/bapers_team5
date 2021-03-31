package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import System.Reportpdf;

import System.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;


public class Report {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JPanel buttonPanel;
    private JTable table;
    private JLabel usernameLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JLabel roleLabel;
    private JPanel createPanel;
    private JTextField createContentField;
    private JTextField createDateGeneratedField;
    private JTextField createStartDateField;
    private JTextField createEndDateField;
    private JTextField createNoOfStaffField;
    private JTextField createNoOfJobsField;
    private JTextField createNoOfTasksField;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JScrollPane tablePanel;
    private JButton printButton;
    private JComboBox createReportTypeComboBox;
    private ImageIcon bannerIcon;
    private Bapers system;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    public String reportType;
    private List<String[]> jobData;
    private List<String[]> taskData;
    private List<String[]> reportData;
    private List<String[]> performanceReportData;
    private List<String[]> summaryReportData;
    private List<String[]> jobsReportData;
    private final String[] tableColumns = {
            "ID",
            "Report Type",
            "Content",
            "Date Generated",
            "Start Date",
            "End date",
            "No. Staff",
            "No. Jobs",
            "No. Tasks"
    };
    private final String[] reportTypes = {
            "Please select a type",
            "Job Report",
            "Summary Report",
            "Performance Report"
    };

    public Report(Bapers system) {
        this.system = system;

        try {
            reportData = DatabaseConnection.getData("report");
            performanceReportData = DatabaseConnection.getData("performanceReport");
            summaryReportData = DatabaseConnection.getData("summaryReport");
            jobsReportData = DatabaseConnection.getData("jobReport");
            assert reportData != null && performanceReportData != null && summaryReportData != null && jobsReportData != null;
            List<String[]> reportTypesData = Stream.concat(performanceReportData.stream(), summaryReportData.stream())
                    .collect(Collectors.toList());
            reportTypesData = Stream.concat(reportTypesData.stream(), jobsReportData.stream()).collect(Collectors.toList());
            String[] temp;
            for (String[] rts : reportTypesData) {
                int i = 0;
                for (String[] rs : reportData) {
                    if (rts[0].equals(rs[0])) {
                        switch (rs[1]) {
                            case "Performance Report":
                                temp = new String[] { rs[0], rs[1], rs[2], rs[3].substring(0,16), rs[4], rs[5], rts[1], "", "" };
                                break;
                            case "Job Report":
                                temp = new String[] { rs[0], rs[1], rs[2], rs[3].substring(0,16), rs[4], rs[5], "", rts[1], "" };
                                break;
                            case "Summary Report":
                                temp = new String[] { rs[0], rs[1], rs[2], rs[3].substring(0,16), rs[4], rs[5], "", "", rts[1] };
                                break;
                            default:
                                temp = new String[] { rs[0], "Invalid Report" };
                                break;
                        } reportData.set(i, temp);
                    } i++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/report.png");
        bannerLabel.setIcon(bannerIcon);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("logout", mainPanel);
                removeMouseListeners();
            }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("jobs", mainPanel);
                removeMouseListeners();
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("customers", mainPanel);
                removeMouseListeners();
            }
        });
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("payments", mainPanel);
                removeMouseListeners();
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("staff", mainPanel);
                removeMouseListeners();
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("tasks", mainPanel);
                removeMouseListeners();
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("reports", mainPanel);
                removeMouseListeners();
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("database", mainPanel);
                removeMouseListeners();
            }
        });

        addMouseListeners();

        ApplicationWindow.displayTable(table, reportData, tableColumns);

        // Table listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getSelectionModel().getSelectedItemsCount() == 1) {
                    editButton.addMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText(null);
                    deleteButton.addMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText(null);
                    printButton.addMouseListener(ApplicationWindow.highlightListener);
                    printButton.setToolTipText(null);
                } else if (table.getSelectionModel().getSelectedItemsCount() > 1) {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select only 1 record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select only 1 record");
                    printButton.removeMouseListener(ApplicationWindow.highlightListener);
                    printButton.setToolTipText("Please select only 1 record");
                } else {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select a record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select a record");
                    printButton.removeMouseListener(ApplicationWindow.highlightListener);
                    printButton.setToolTipText("Please select a record");
                }

                editButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
                deleteButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
                printButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);

                resetCreatePanel();
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(true);
                buttonPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                String[] rowData = DatabaseConnection.getRowBySingleID("report", id);
                assert rowData != null;
                switch (rowData[1]) {
                    case "Performance Report":
                        jobData = DatabaseConnection.getJobFromDates(rowData[4], rowData[5]);
                        assert jobData != null;
                        List<String[]> taskData = new ArrayList<>();
                        List<String[]> temp;
                        for (String[] jd : jobData) {
                            temp = DatabaseConnection.getTaskFromJobID(Integer.parseInt(jd[0]));
                            assert temp != null;
                            for (String[] t : temp) {
                                taskData.add(t);
                            }
                        }

                        List<String[]> performanceReportData = new ArrayList<>();
                        for (String[] t : taskData) {
                            String name = DatabaseConnection.getStaffName(Integer.parseInt(t[8]));
                            String ID = t[0];
                            String department = t[4];
                            String date = t[5].substring(0,10);
                            String startTime = t[5].substring(11,16);
                            String timeTaken = t[6];
                            String[] row = { name, ID, department, date, startTime, timeTaken };
                            performanceReportData.add(row);
                        }

                        Reportpdf reportpdf = new Reportpdf();

                        try {
                            reportpdf.createPerformanceReport(performanceReportData);
                        } catch (BadElementException badElementException) {
                            badElementException.printStackTrace();
                        } catch (DocumentException documentException) {
                            documentException.printStackTrace();
                        }

                        break;
                    case "Summary Report":




                        break;
                    case "Job Report":

                        jobData = DatabaseConnection.getJobFromDates(rowData[4],rowData[5]);
                        assert jobData != null;

                        List<String[]> jobPerformanceData = new ArrayList<>();
                        for(String[] c : jobData){

                            String name = DatabaseConnection.getCustomerName(Integer.parseInt(c[7]));
                            String ID = c[0];
                            String startDate = c[3].substring(0,10);
                            String endDate = c[4].substring(11,16);
                            String status = c[6];
                            String[] row = {name, ID, startDate, endDate,status};
                            jobPerformanceData.add(row);
                        }
                        Reportpdf reportpdf1 = new Reportpdf();

                        try{
                            reportpdf1.createJobReport(jobPerformanceData);
                        } catch (BadElementException badElementException){
                            badElementException.printStackTrace();
                        } catch (DocumentException documentException){
                            documentException.printStackTrace();
                        }

                        break;
                }
            }
        });


        createReportTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createConfirmButton.setEnabled(createReportTypeComboBox.getSelectedIndex() != 0);
                if(createReportTypeComboBox.getSelectedIndex() == 3 ){
                    Reportpdf.performanceReport();
                } else if (createReportTypeComboBox.getSelectedIndex() == 2){
                    Reportpdf.summaryReport();
                } else if (createReportTypeComboBox.getSelectedIndex() == 1){
                    Reportpdf.jobReport();
                }
            }
        });
    }

    private void resetCreatePanel() {
        createReportTypeComboBox.setModel(new DefaultComboBoxModel<>(reportTypes));

        createContentField.setBorder(null);
        createDateGeneratedField.setBorder(null);
        createStartDateField.setBorder(null);
        createEndDateField.setBorder(null);
        createNoOfJobsField.setBorder(null);
        createNoOfStaffField.setBorder(null);
        createNoOfTasksField.setBorder(null);
    }

    private void addMouseListeners() {
        logoutButton.addMouseListener(ApplicationWindow.highlightListener);
        jobsButton.addMouseListener(ApplicationWindow.highlightListener);
        customerButton.addMouseListener(ApplicationWindow.highlightListener);
        paymentsButton.addMouseListener(ApplicationWindow.highlightListener);
        staffButton.addMouseListener(ApplicationWindow.highlightListener);
        tasksButton.addMouseListener(ApplicationWindow.highlightListener);
        reportsButton.addMouseListener(ApplicationWindow.highlightListener);
        databaseButton.addMouseListener(ApplicationWindow.highlightListener);
        createButton.addMouseListener(ApplicationWindow.highlightListener);
        editButton.addMouseListener(ApplicationWindow.highlightListener);
        deleteButton.addMouseListener(ApplicationWindow.highlightListener);
    }

    private void removeMouseListeners() {
        logoutButton.removeMouseListener(ApplicationWindow.highlightListener);
        jobsButton.removeMouseListener(ApplicationWindow.highlightListener);
        customerButton.removeMouseListener(ApplicationWindow.highlightListener);
        paymentsButton.removeMouseListener(ApplicationWindow.highlightListener);
        staffButton.removeMouseListener(ApplicationWindow.highlightListener);
        tasksButton.removeMouseListener(ApplicationWindow.highlightListener);
        reportsButton.removeMouseListener(ApplicationWindow.highlightListener);
        databaseButton.removeMouseListener(ApplicationWindow.highlightListener);
        createButton.removeMouseListener(ApplicationWindow.highlightListener);
        editButton.removeMouseListener(ApplicationWindow.highlightListener);
        deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
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
