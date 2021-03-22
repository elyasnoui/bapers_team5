package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import System.reportpdf;

import System.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

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
    private JTextField reportTypeField;
    private JTextField contentField;
    private JTextField dateGeneratedField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField noOfStaffField;
    private JTextField noOfJobsField;
    private JTextField noOfTasksField;
    private JButton popupCreateButton;
    private JButton popupCancelButton;
    private JScrollPane tablePanel;
    private JButton printButton;
    private ImageIcon bannerIcon;
    private Bapers system;
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
                            case "Individual Performance Report":
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

        ApplicationWindow.displayTable(table, reportData, tableColumns);
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
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(reportpdf.getFILE()));
                    document.open();
                    reportpdf.addMetaData(document);
                    reportpdf.addTitlePage(document);
                    reportpdf.addContent(document);
                    document.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
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
}
