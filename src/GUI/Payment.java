package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JLabel tasksAddedLabel;
    private JLabel customerIDLabel;
    private JLabel customerIDValue;
    private JPanel urgencyLabelPanel;
    private JLabel deadlineValue;
    private JPanel urgencyPanel;
    private JButton firstTierButton;
    private JButton secondTierButton;
    private JButton thirdTierButton;
    private JLabel isUrgentLabel;
    private JButton popupCreateButton;
    private JButton removeButton;
    private JButton lookupButton;
    private JPanel customerLookupPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JScrollPane customerScrollPane;
    private JTable customerTable;
    private JButton lookupSelectButton;
    private JButton lookupCancelButton;
    private JLabel amountLabel;
    private List<String[]> paymentData;
    private List<String[]> paymentData1;
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
    private final String[] customerColumns = {
            "jobID",
            "amountDue",
            "isPaid",
    };

    public Payment(Bapers system) {

        this.system = system;

        firstNameField.setBorder(null);
        lastNameField.setBorder(null);
        customerTable.setModel(new DefaultTableModel(null, customerColumns));

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

        addMouseListeners();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("logout", mainPanel);
                removeMouseListeners(); }

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

//        logoutButton.addMouseListener(ApplicationWindow.highlightListener);
//        jobsButton.addMouseListener(ApplicationWindow.highlightListener);
//        customerButton.addMouseListener(ApplicationWindow.highlightListener);
//        paymentsButton.addMouseListener(ApplicationWindow.highlightListener);
//        staffButton.addMouseListener(ApplicationWindow.highlightListener);
//        tasksButton.addMouseListener(ApplicationWindow.highlightListener);
//        reportsButton.addMouseListener(ApplicationWindow.highlightListener);
//        databaseButton.addMouseListener(ApplicationWindow.highlightListener);
//        createButton.addMouseListener(ApplicationWindow.highlightListener);
//        editButton.addMouseListener(ApplicationWindow.highlightListener);
//        deleteButton.addMouseListener(ApplicationWindow.highlightListener);

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
        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (firstNameField.getText().length() >= 3 || lastNameField.getText().length() >= 3) {
                    paymentSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (lastNameField.getText().length() >= 3 || firstNameField.getText().length() >= 3) {
                    paymentSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                customerLookupPanel.setVisible(true);
            }
        });
        lookupSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customerTable.getSelectionModel().isSelectionEmpty()) {
                    String ID = paymentData.get(customerTable.getSelectedRow())[0];
                    customerIDValue.setText(ID);
                    customerLookupPanel.setVisible(false);
                    createPanel.setVisible(true);
                }
            }
        });
        lookupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerLookupPanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });

    }


    private void paymentSearch() {
        try {
            paymentData1 = DatabaseConnection.searchPayments(firstNameField.getText(), lastNameField.getText());
            assert paymentData1 != null;
            System.out.println(paymentData1);
            ApplicationWindow.displayTable(customerTable, paymentData1, customerColumns);
        }
        catch (Exception e) { e.printStackTrace(); }
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
        popupCreateButton.addMouseListener(ApplicationWindow.highlightListener);
        popupCancelButton.addMouseListener(ApplicationWindow.highlightListener);
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
        popupCreateButton.removeMouseListener(ApplicationWindow.highlightListener);
        popupCancelButton.removeMouseListener(ApplicationWindow.highlightListener);
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
