package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
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
    private JLabel jobIDLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel amountDueLabel;
    private JLabel discountLabel;
    private JLabel amountMinusLabel;
    private JCheckBox cardCheckBox;
    private JLabel cardTypeLabel;
    private JLabel expiryDateLabel;
    private JLabel cardNumberLabel;
    private JComboBox cardTypeComboBox;
    private JCheckBox cashCheckBox;
    private JLabel cashGivenLabel;
    private JLabel changeGivenLabel;
    private JTextField expiryPT1;
    private JTextField expiryPT2;
    private JLabel expirySlash;
    private JTextField cardNumberField;
    private List<String[]> paymentData;
    private List<String[]> paymentData1;
    private List<String[]> cardData;
    private List<String[]> cashData;
    private static DecimalFormat df2 = new DecimalFormat("0.00");
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
    private final String[] cardTypes = {
            "Mastercard Debit Card",
            "Visa Debit Card",
            "Amex Credit Card",
            "Mastercard Credit Card",
            "Visa Credit Card"
    };
    private final String[] customerColumns = {
            "firstName",
            "lastName",
            "jobID",
            "amountDue",
            "discount"
    };

    public Payment(Bapers system) {

        this.system = system;

        firstNameField.setBorder(null);
        lastNameField.setBorder(null);
        cardTypeComboBox.setModel(new DefaultComboBoxModel<>(cardTypes));
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
                resetCreatePanel();
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
                    String firstName = paymentData1.get(customerTable.getSelectedRow())[0];
                    String lastName = paymentData1.get(customerTable.getSelectedRow())[1];
                    String jobID = paymentData1.get(customerTable.getSelectedRow())[2];
                    String amountDue = paymentData1.get(customerTable.getSelectedRow())[3];
                    String discount = paymentData1.get(customerTable.getSelectedRow())[4];
                    double deductions = Double.parseDouble(amountDue) - Double.parseDouble(discount);
                    firstNameLabel.setText(firstName);
                    lastNameLabel.setText(lastName);
                    jobIDLabel.setText(jobID);
                    amountDueLabel.setText(amountDue);
                    discountLabel.setText(discount);
                    amountMinusLabel.setText(Double.toString(Double.parseDouble(df2.format(deductions))));
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
                resetLookupPanel();

            }
        });

        cardCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cardCheckBox.isSelected()){
                    cardTypeLabel.setVisible(true);
                    expiryDateLabel.setVisible(true);
                    cardNumberLabel.setVisible(true);
                    cardTypeComboBox.setVisible(true);
                    expiryPT1.setVisible(true);
                    expirySlash.setVisible(true);
                    expiryPT2.setVisible(true);
                    cardNumberField.setVisible(true);

                    cashGivenLabel.setVisible(false);
                    changeGivenLabel.setVisible(false);
                }
                else{
                    cardTypeLabel.setVisible(false);
                    expiryDateLabel.setVisible(false);
                    cardNumberLabel.setVisible(false);
                    cardTypeComboBox.setVisible(false);
                    expiryPT1.setVisible(false);
                    expirySlash.setVisible(false);
                    expiryPT2.setVisible(false);
                    cardNumberField.setVisible(false);
                }

            }
        });
        cashCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cashCheckBox.isSelected()){
                    cashGivenLabel.setVisible(true);
                    changeGivenLabel.setVisible(true);

                    cardTypeLabel.setVisible(false);
                    expiryDateLabel.setVisible(false);
                    cardNumberLabel.setVisible(false);
                    cardTypeComboBox.setVisible(false);
                    expiryPT1.setVisible(false);
                    expirySlash.setVisible(false);
                    expiryPT2.setVisible(false);
                    cardNumberField.setVisible(false);

                }
                else{
                    cashGivenLabel.setVisible(false);
                    changeGivenLabel.setVisible(false);
                }

            }
        });
    }

    public void resetLookupPanel() {
        firstNameField.setText("");
        lastNameField.setText("");
        customerTable.setModel(new DefaultTableModel(null, customerColumns));
    }

    public void resetCreatePanel(){
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        jobIDLabel.setText("");
        amountDueLabel.setText("");
        discountLabel.setText("");
        amountMinusLabel.setText("");
    }

    private void paymentSearch() {
        try {
            paymentData1 = DatabaseConnection.searchPayments(firstNameField.getText(), lastNameField.getText());
            assert paymentData1 != null;
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
