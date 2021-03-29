package GUI;

import System.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Payment {
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
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactNumberField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField niField;
    private JTextField workHoursField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField roleField;
    private JTextField privilegesField;
    private JButton popupCreateButton;
    private List<String[]> paymentData;
    private List<String[]> cardData;
    private List<String[]> cashData;
    private final String[] tableColumns = {
            "Job ID",
            "Amount Due",
            "Is Paid",
            "Discount",
            "Date",
            "Payment Type",
            "Customer ID",
            "Staff ID",
            "Card Type",
            "Expiry Date",
            "Last 4 Digits",
            "Cash Paid",
            "Change Given"
    };

    public Payment(Bapers system) {
        this.system = system;

        try {
            paymentData = DatabaseConnection.getData("payment");
            cardData = DatabaseConnection.getData("card");
            cashData = DatabaseConnection.getData("cash");
            assert paymentData != null && cardData != null && cashData != null;
            List<String[]> paymentTypeData = Stream.concat(cardData.stream(), cashData.stream())
                    .collect(Collectors.toList());
            String[] temp;
            for (String[] pts : paymentTypeData) {
                int i = 0;
                for (String[] ps : paymentData) {
                    if (pts[0].equals(ps[0])) {
                        switch (ps[5]) {
                            case "Card":
                                temp = new String[] { ps[0], '£'+ps[1], ps[2], '£'+ps[3], ps[4], ps[5], ps[6], ps[7], pts[1],
                                        pts[2], pts[3], "", "" };
                                break;
                            case "Cash":
                                temp = new String[] { ps[0], '£'+ps[1], ps[2], '£'+ps[3], ps[4], ps[5], ps[6], ps[7], "",
                                        "", "", '£'+pts[1], '£'+pts[2] };
                                break;
                            default:
                                temp = new String[] { ps[0], "Invalid Payment" };
                                break;
                        } paymentData.set(i, temp);
                    } i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        bannerIcon = new ImageIcon("data/banners/payment.png");
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

        ApplicationWindow.displayTable(table, paymentData, tableColumns);
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
