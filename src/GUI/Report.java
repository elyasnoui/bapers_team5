package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final String[] tableColumns = {
            "ID",
            "Report Type",
            "Date Generated",
            "Start Date",
            "End date",
    };
    private final String[] reportTypes = {
            "Please select a type",
            "Job Report",
            "Summary Report",
            "Performance Report",
            "Customer Sales Report"
    };

    public Report(Bapers system) {
        this.system = system;

        //reportData = DatabaseConnection.getData("report");

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
                        taskData = DatabaseConnection.getTaskForPerformance(rowData[4], rowData[5], "Performance Report");
                        assert taskData != null;
                        List<String[]> performanceReportData = new ArrayList<>();

                            for (String[] t : taskData) {
                                String name = DatabaseConnection.getStaffName(Integer.parseInt(t[8]));
                                String ID = t[1];
                                String department = t[4];
                                String date = t[5].substring(0,10);
                                String startTime = t[5].substring(11,16);
                                String timeTaken = t[6];
                                String total = "";
                                String[] row = { name, ID, department, date, startTime, timeTaken, total };
                                performanceReportData.add(row);
                            }

                        Reportpdf reportpdf = new Reportpdf();

                        for(int i = 0; i < performanceReportData.size(); i++){
                            double total = Double.parseDouble(performanceReportData.get(i)[5]);
                            while(i+1 != performanceReportData.size() && performanceReportData.get(i)[0].equals(performanceReportData.get(i + 1)[0])){
                                total += Double.parseDouble(performanceReportData.get(i+1)[5]);
                                i++;
                            }
                            String[] newRow = { performanceReportData.get(i)[0], performanceReportData.get(i)[1], performanceReportData.get(i)[2],
                            performanceReportData.get(i)[3], performanceReportData.get(i)[4],performanceReportData.get(i)[5],
                             String.valueOf(total)};
                            performanceReportData.set(i, newRow);
                        }

                        /*for (String[] s : performanceReportData) {
                            System.out.println(Arrays.toString(s));
                        }*/


                        try {
                            reportpdf.createPerformanceReport(performanceReportData);
                        } catch (BadElementException badElementException) {
                            badElementException.printStackTrace();
                        } catch (DocumentException documentException) {
                            documentException.printStackTrace();
                        }

                        break;
                    case "Summary Report":

                        //taskData = DatabaseConnection.getTaskForShift((rowData[4]+" 05:00:00"),(rowData[5]+" 14:30:00"), "Summary Report");
                        assert taskData != null;

                        List<String[]> dayShift1 = new ArrayList<>();
                        for(String[] s : taskData){

                            String date = s[5].substring(0,10);
                            String timeTaken = s[6];
                            String department = s[4];
                            String total = "";
                            String[] row = {date, department, timeTaken, total};
                            dayShift1.add(row);
                        }

                        Reportpdf reportpdf3 = new Reportpdf();

                        for(int i = 0; i < dayShift1.size(); i++){
                            double total = Double.parseDouble(dayShift1.get(i)[2]);
                            while(i+1 != dayShift1.size() && dayShift1.get(i)[0].equals(dayShift1.get(i + 1)[0])){
                                total += Double.parseDouble(dayShift1.get(i+1)[2]);
                                i++;
                            }
                            String[] newRow = { dayShift1.get(i)[0], dayShift1.get(i)[1], dayShift1.get(i)[2],
                                    String.valueOf(total)};
                            dayShift1.set(i, newRow);
                        }

                        //taskData = DatabaseConnection.getTaskForShift(rowData[4]+" 14:30:01",rowData[5]+" 22:00:00", "Summary Report");
                        assert taskData != null;

                        List<String[]> dayShift2 = new ArrayList<>();
                        for(String[] s : taskData){

                            String date = s[5].substring(0,10);
                            String timeTaken = s[6];
                            String department = s[4];
                            String total = "";
                            String[] row = {date, department, timeTaken,total};
                            dayShift2.add(row);
                        }

                        for(int i = 0; i < dayShift2.size(); i++){
                            double total = Double.parseDouble(dayShift2.get(i)[2]);
                            while(i+1 != dayShift2.size() && dayShift2.get(i)[0].equals(dayShift2.get(i + 1)[0])){
                                total += Double.parseDouble(dayShift2.get(i+1)[2]);
                                i++;
                            }
                            String[] newRow = { dayShift2.get(i)[0], dayShift2.get(i)[1], dayShift2.get(i)[2],
                                    String.valueOf(total)};
                            dayShift2.set(i, newRow);
                        }

                        //taskData = DatabaseConnection.getTaskForShift(rowData[4]+ " 22:00:01",rowData[5]+ " 04:59:59", "Summary Report");
                        assert taskData != null;

                        List<String[]> nightShift = new ArrayList<>();
                        for(String[] s : taskData){

                            String date = s[5].substring(0,10);
                            String timeTaken = s[6];
                            String department = s[4];
                            String total ="";
                            String[] row = {date, department, timeTaken, total};
                            nightShift.add(row);
                        }

                        for(int i = 0; i < nightShift.size(); i++){
                            double total = Double.parseDouble(nightShift.get(i)[2]);
                            while(i+1 != nightShift.size() && nightShift.get(i)[0].equals(nightShift.get(i + 1)[0])){
                                total += Double.parseDouble(nightShift.get(i+1)[2]);
                                i++;
                            }
                            String[] newRow = { nightShift.get(i)[0], nightShift.get(i)[1], nightShift.get(i)[2],
                                    String.valueOf(total)};
                            nightShift.set(i, newRow);
                        }

                        try {
                            reportpdf3.createSummaryReport(dayShift1,dayShift2,nightShift);
                        } catch (BadElementException badElementException) {
                            badElementException.printStackTrace();
                        } catch (DocumentException documentException) {
                            documentException.printStackTrace();
                        }

                        break;
                    case "Customer Sales Report":
                        jobData = DatabaseConnection.getJobFromDates(rowData[4],rowData[5], "Customer Sales Report");
                        assert jobData != null;

                        List<String[]> customerSalesData = new ArrayList<>();
                        for(String[] c : jobData){

                            String companyName = DatabaseConnection.getCompanyName(Integer.parseInt(c[7]));
                            String name = DatabaseConnection.getCustomerName(Integer.parseInt(c[7]));
                            String ID = c[0];
                            String startDate = c[3];
                            String endDate = c[4];
                            String status = c[6];
                            String amount = c[2];
                            String total = "";
                            String[] row = { companyName, name, ID, startDate, endDate, status, amount, total };
                            customerSalesData.add(row);
                        }
                        Reportpdf reportpdf1 = new Reportpdf();

                        // Calculating total for each job after sorting by name
                        for (int i = 0; i < customerSalesData.size(); i++) {
                            double total = Double.parseDouble(customerSalesData.get(i)[6]);
                            while (i+1 != customerSalesData.size() && customerSalesData.get(i)[1].equals(customerSalesData.get(i + 1)[1])) {
                                total += Double.parseDouble(customerSalesData.get(i+1)[6]);
                                i++;
                            }
                            String[] newRow = { customerSalesData.get(i)[0], customerSalesData.get(i)[1], customerSalesData.get(i)[2],
                                    customerSalesData.get(i)[3], customerSalesData.get(i)[4], customerSalesData.get(i)[5], customerSalesData.get(i)[6],
                                    String.valueOf(total) };
                            customerSalesData.set(i, newRow);
                        }

                        for (String[] s : customerSalesData) {
                            System.out.println(Arrays.toString(s));
                        }

                        try{
                            reportpdf1.createCustomerSalesReport(customerSalesData);
                        } catch (BadElementException badElementException){
                            badElementException.printStackTrace();
                        } catch (DocumentException documentException){
                            documentException.printStackTrace();
                        }

                        break;

                    case "Job Report":
                        int ID = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                        jobData = DatabaseConnection.jobReportWithID(rowData[3],rowData[4], 1);

                        assert jobData != null;
                        List<String[]> jobReportData = new ArrayList<>();
                        for(String[] c : jobData){

                            String companyName = DatabaseConnection.getCompanyName(Integer.parseInt(c[7]));
                            String name = DatabaseConnection.getCustomerName(Integer.parseInt(c[7]));
                            String startDate = c[3];
                            String endDate = c[4];
                            String status = c[6];
                            String amount = c[2];
                            String total = "";
                            String[] row = { companyName, name, String.valueOf(ID), startDate, endDate, status, amount, total };
                            jobReportData.add(row);
                        }

                        Reportpdf reportpdf2 = new Reportpdf();

                        // Calculating total for each job after sorting by name
                        for (int i = 0; i < jobReportData.size(); i++) {
                            double total = Double.parseDouble(jobReportData.get(i)[6]);
                            while (i+1 != jobReportData.size() && jobReportData.get(i)[1].equals(jobReportData.get(i + 1)[1])) {
                                total += Double.parseDouble(jobReportData.get(i+1)[6]);
                                i++;
                            }
                            String[] newRow = { jobReportData.get(i)[0], jobReportData.get(i)[1], jobReportData.get(i)[2],
                                    jobReportData.get(i)[3], jobReportData.get(i)[4], jobReportData.get(i)[5], jobReportData.get(i)[6],
                                    String.valueOf(total) };
                            jobReportData.set(i, newRow);
                        }

                        try{
                            reportpdf2.createJobReport(jobReportData);
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
                    System.out.println("index 0");
                } else if (createReportTypeComboBox.getSelectedIndex() == 2){
                    System.out.println("index 2");
                } else if (createReportTypeComboBox.getSelectedIndex() == 1){
                    System.out.println("index 1");
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
