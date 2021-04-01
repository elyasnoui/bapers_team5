package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Payment extends Form {
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
    private JButton makeButton;
    private JTable table;
    private JPanel mainPanel;
    private JPanel createPanel;
    private JButton popupCancelButton;
    private JScrollPane tablePanel;
    private JButton popupCreateButton;
    private JButton lookupButton;
    private JPanel customerLookupPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTable customerTable;
    private JButton lookupSelectButton;
    private JButton lookupCancelButton;
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
    private JPanel jobLookupPanel;
    private JTextField lookupToTextField;
    private JScrollPane jobScrollPane;
    private JTable jobTable;
    private JTextField lookupFromTextField;
    private JComboBox createPaymentTypeComboBox;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JLabel createCustomerNameField;
    private JLabel createJobIDField;
    private JLabel createTotalBeforeChargesField;
    private JLabel createDiscountsField;
    private JLabel createFinalTotalField;
    private JLabel createVatField;
    private JLabel createSurchargeField;
    private JLabel createCardTypeLabel;
    private JComboBox createCardTypeComboBox;
    private JLabel createExpiryDateLabel;
    private JTextField createExpiryDateField;
    private JTextField createLastFourDigitsField;
    private JLabel createLastFourDigitsLabel;
    private JLabel createCashPaidLabel;
    private JTextField createCashPaidField;
    private JLabel createChangeDueLabel;
    private JLabel createChangeGivenValue;
    private List<String[]> paymentData;
    private List<String[]> paymentData1;
    private List<String[]> cardData;
    private List<String[]> cashData;
    private List<String[]> jobLookupData;
    private int customerID;
    private int staffID;
    private static DecimalFormat df2 = new DecimalFormat("0.00");

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    private final String[] tableColumns = {
            "Job ID",
            "Amount Due",
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
    private final String[] paymentTypes = {
            "Please select a payment type",
            "Card",
            "Cash"
    };
    private final String[] customerColumns = {
            "firstName",
            "lastName",
            "jobID",
            "amountDue",
            "discount"
    };
    private final String[] lookupColumns = {
            "Job ID",
            "Start Date",
            "Customer Name",
            "Price",
            "Status"
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
                        switch (ps[4]) {
                            case "Card":
                                temp = new String[] { ps[0], '£'+ps[1], '£'+ps[2], ps[3], ps[4], ps[5], ps[6], pts[1],
                                        pts[2], pts[3], "", "" };
                                break;
                            case "Cash":
                                temp = new String[] { ps[0], '£'+ps[1], '£'+ps[2], ps[3], ps[4], ps[5], ps[6], "",
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

        ApplicationWindow.displayTable(table, paymentData, tableColumns);
        makeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                jobLookupPanel.setVisible(true);

                resetLookupPanel();
            }
        });
        lookupSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobLookupPanel.setVisible(false);
                createPanel.setVisible(true);

                createCustomerNameField.setText(String.valueOf(jobTable.getValueAt(jobTable.getSelectedRow(), 2)));
                createJobIDField.setText(String.valueOf(jobTable.getValueAt(jobTable.getSelectedRow(), 0)));
                createTotalBeforeChargesField.setText(String.valueOf(jobTable.getValueAt(jobTable.getSelectedRow(), 3)));
                double price = Double.parseDouble(createTotalBeforeChargesField.getText());
                customerID = Integer.parseInt(jobLookupData.get(jobTable.getSelectedRow())[6]);
                staffID = Integer.parseInt(jobLookupData.get(jobTable.getSelectedRow())[6]);
                double surcharge = 0;
                switch (jobLookupData.get(jobTable.getSelectedRow())[5]) {
                    case "1":
                        surcharge = (price*1.5);
                        price += surcharge;
                        break;
                    case "2":
                        surcharge = price;
                        price += surcharge;
                        break;
                    case "3":
                        surcharge = (price*0.5);
                        price += surcharge;
                        break;
                    case "4":
                        surcharge = (price*0.25);
                        price += surcharge;
                        break;
                }
                createSurchargeField.setText("£"+df2.format(surcharge));

                double discount = 0;
                if (DatabaseConnection.isCustomerValuedCustomer(customerID)) {
                    System.out.println(customerID);
                    String[] vcRow = DatabaseConnection.getRowBySingleID("valuedCustomer", customerID);
                    assert vcRow != null;
                    int jobID = Integer.parseInt(jobLookupData.get(jobTable.getSelectedRow())[0]);
                    switch (vcRow[1]) {
                        case "Fixed Discount":
                            discount += price/100 * Double.parseDouble(vcRow[2]);
                            break;
                        case "Variable Discount":
                            String[] vDiscounts = vcRow[2].split(",");
                            for (String d : vDiscounts) {
                                String[] vTemp = d.split("-");
                                List<String[]> taskPrices = DatabaseConnection.getTasksToDiscount(jobID, Integer.parseInt(vTemp[0]));
                                for (String[] p : taskPrices)
                                    discount += Double.parseDouble(p[0])/100 * Double.parseDouble(vTemp[1]);
                            }
                            break;
                        case "Flexible Discount":
                            String[] fDiscounts = vcRow[2].split(",");
                            double volume = DatabaseConnection.getPaymentVolume(customerID);
                            String[] fTemp = { "0", "0" };
                            if (volume <= 1000) fTemp = fDiscounts[0].split("-");
                            else if (volume <= 2000) fTemp = fDiscounts[1].split("-");
                            else if (volume > 2000) fTemp = fDiscounts[2].split("-");
                            discount = price/100 * Double.parseDouble(fTemp[1]);
                            break;
                    }
                }
                createVatField.setText("£"+df2.format(price*0.2));
                createDiscountsField.setText("£"+df2.format(discount));
                double finalPrice = (price+price*0.2)+surcharge-discount;
                createFinalTotalField.setText("£"+df2.format(finalPrice));

                resetCreatePanel();
            }
        });
        lookupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jobLookupPanel.setVisible(false);
                tablePanel.setVisible(true);
                buttonPanel.setVisible(true);
            }
        });
        KeyAdapter listener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getComponent() instanceof JTextField) {
                    if (!((JTextField) e.getComponent()).getText().matches(ApplicationWindow.dateRegex)) {
                        ((JTextField) e.getComponent()).setBorder(ApplicationWindow.borderError);
                        ((JTextField) e.getComponent()).setToolTipText("Please enter a valid date (dd-mm-yyyy)");
                    } else {
                        ((JTextField) e.getComponent()).setBorder(null);
                        ((JTextField) e.getComponent()).setToolTipText(null);

                        if (lookupToTextField.getText().matches(ApplicationWindow.dateRegex) &&
                                lookupFromTextField.getText().matches(ApplicationWindow.dateRegex)) {
                            jobSearch();
                        }
                    }
                }
            }
        };
        lookupToTextField.addKeyListener(listener);
        lookupFromTextField.addKeyListener(listener);

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
        createPaymentTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCardTypeLabel.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);
                createCardTypeComboBox.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);
                createExpiryDateLabel.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);
                createExpiryDateField.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);
                createLastFourDigitsLabel.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);
                createLastFourDigitsField.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 1);

                createCashPaidLabel.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 2);
                createCashPaidField.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 2);
                createChangeDueLabel.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 2);
                createChangeGivenValue.setVisible(createPaymentTypeComboBox.getSelectedIndex() == 2);
            }
        });
        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (createPaymentTypeComboBox.getSelectedIndex()) {
                    case 1:
                        if (createExpiryDateField.getText().matches(ApplicationWindow.expiryRegex) &&
                        createLastFourDigitsField.getText().matches(ApplicationWindow.last4Digits)) {
                            try {
                                if (DatabaseConnection.addPayment(Integer.parseInt(createJobIDField.getText()), Double.parseDouble(createFinalTotalField.getText().substring(1))
                                , Double.parseDouble(createDiscountsField.getText().substring(1)), String.valueOf(createPaymentTypeComboBox.getSelectedItem()), customerID, 4)) {
                                    if (DatabaseConnection.addCard(Integer.parseInt(createJobIDField.getText()), String.valueOf(createCardTypeComboBox.getSelectedItem()), createExpiryDateField.getText(),
                                            Integer.parseInt(createLastFourDigitsField.getText()))) {
                                        DatabaseConnection.updateJobStatus(Integer.parseInt(createJobIDField.getText()), "Paid");
                                    }
                                }
                            } catch (SQLException exception) { exception.printStackTrace(); }
                        }
                        break;
                    case 2:
                        if (createCashPaidField.getText().matches(ApplicationWindow.money) && Double.parseDouble(createCashPaidField.getText()) >=
                        Double.parseDouble(createFinalTotalField.getText().substring(1))) {
                            try {
                                if (DatabaseConnection.addPayment(Integer.parseInt(createJobIDField.getText()), Double.parseDouble(createFinalTotalField.getText().substring(1))
                                        , Double.parseDouble(createDiscountsField.getText().substring(1)), String.valueOf(createPaymentTypeComboBox.getSelectedItem()), customerID, 4)) {
                                    if (!DatabaseConnection.addCash(Integer.parseInt(createJobIDField.getText()), Double.parseDouble(createCashPaidField.getText()),
                                            Double.parseDouble(createChangeGivenValue.getText().substring(1)))) {
                                        DatabaseConnection.updateJobStatus(Integer.parseInt(createJobIDField.getText()), "Paid");
                                    }
                                }
                            } catch (SQLException exception) { exception.printStackTrace(); }
                        } else JOptionPane.showMessageDialog(mainPanel, "You can only pay at or above the total amount");

                        break;
                    default:
                        JOptionPane.showMessageDialog(mainPanel, "Please select a payment type");
                        break;
                }

                try {
                    DatabaseConnection.updateJobStatus(Integer.parseInt(createJobIDField.getText()), "Paid");
                } catch (SQLException exception) { exception.printStackTrace(); }
                system.changeScreen("payments", mainPanel);
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                tablePanel.setVisible(true);
                buttonPanel.setVisible(true);

                removeCreateListeners();
            }
        });
        createCashPaidField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (createCashPaidField.getText().matches(ApplicationWindow.money)) {
                    try {
                        if (Double.parseDouble(createCashPaidField.getText()) >
                                Double.parseDouble(createFinalTotalField.getText().substring(1))) {
                            createCashPaidField.setBorder(null);
                            createChangeGivenValue.setText("£"+ df2.format(Math.abs(Double.parseDouble(
                                    createFinalTotalField.getText().substring(1)) - Double.parseDouble(createCashPaidField.getText()))));
                        } else createCashPaidField.setBorder(ApplicationWindow.borderError);
                    } catch (NumberFormatException ignored) {}
                }
            }
        });
    }

    private void jobSearch() {
        try {
            jobLookupData = DatabaseConnection.searchJobs(sdf2.format(sdf.parse(lookupFromTextField.getText())),
                    sdf2.format(sdf.parse(lookupToTextField.getText())), "Unpaid");
            assert jobLookupData != null;
            for (String[] js : jobLookupData)
                if (!(js[2] == null))
                    js[2] = DatabaseConnection.getCustomerName(Integer.parseInt(js[2]));
            ApplicationWindow.displayTable(jobTable, jobLookupData, lookupColumns);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void resetLookupPanel() {
        lookupFromTextField.setText("");
        lookupFromTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
        lookupFromTextField.setBorder(null);
        lookupToTextField.addKeyListener(ApplicationWindow.regexListener);
        lookupToTextField.setText("");
        lookupToTextField.setToolTipText("Please enter a valid date (dd-mm-yyyy)");
        lookupToTextField.setBorder(null);
        lookupToTextField.addKeyListener(ApplicationWindow.regexListener);
        jobTable.setModel(new DefaultTableModel(null, lookupColumns));
        lookupSelectButton.setEnabled(false);
    }

    private void resetCreatePanel() {
        createPaymentTypeComboBox.setModel(new DefaultComboBoxModel<>(paymentTypes));
        createCardTypeComboBox.setModel(new DefaultComboBoxModel<>(cardTypes));
        createExpiryDateField.setBorder(null);
        createLastFourDigitsField.setBorder(null);
        createCashPaidField.setBorder(null);

        addCreateListeners();
    }

    private void addCreateListeners() {
        createExpiryDateField.addKeyListener(ApplicationWindow.regexListener);
        createLastFourDigitsField.addKeyListener(ApplicationWindow.regexListener);
        createCashPaidField.addKeyListener(ApplicationWindow.regexListener);
    }

    private void removeCreateListeners() {
        createExpiryDateField.addKeyListener(ApplicationWindow.regexListener);
        createLastFourDigitsField.addKeyListener(ApplicationWindow.regexListener);
        createCashPaidField.addKeyListener(ApplicationWindow.regexListener);
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
        makeButton.addMouseListener(ApplicationWindow.highlightListener);
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
        makeButton.removeMouseListener(ApplicationWindow.highlightListener);
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
