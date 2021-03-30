package GUI;

import System.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Customer extends JFrame{

    private Bapers system;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JButton customerButton;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JTable table;
    private JLabel usernameLabel;
    private JLabel roleLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JPanel createPanel;
    private JTextField createFirstNameField;
    private JTextField createLastNameField;
    private JTextField createContactNumberField;
    private JTextField createAddressFirstField;
    private JTextField createEmailField;
    private JTextField createDiscountRateField;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JPanel tablePanel;
    private ImageIcon bannerIcon;

    private JCheckBox createVcCheckBox;
    private JLabel createAgreedDiscountLabel;
    private JLabel createDiscountRateLabel;
    private JComboBox createAgreedDiscountComboBox;
    private JTextField createPostcodeField;
    private JTextField createAddressSecondField;
    private JTextField createCityField;
    private JPanel variableDiscountPanel;
    private JTable variableDiscountTable;
    private JScrollPane variableScrollPane;
    private JButton variableAssignButton;
    private JButton variableCancelButton;
    private JButton createVariableChangeButton;
    private JLabel createVariableDiscountsLabel;
    private JPanel editPanel;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private JCheckBox editVcCheckBox;
    private JTextField editFirstNameField;
    private JTextField editLastNameField;
    private JTextField editContactNumberField;
    private JTextField editAddressFirstField;
    private JTextField editAddressSecondField;
    private JTextField editCityField;
    private JTextField editPostcodeField;
    private JTextField editEmailField;
    private JComboBox editAgreedDiscountComboBox;
    private JTextField editDiscountRateField;
    private JButton editVariableChangeButton;
    private JLabel editAgreedDiscountLabel;
    private JLabel editDiscountRateLabel;
    private JLabel editVariableDiscountsLabel;
    private JTextField createCompanyField;
    private JTextField editCompanyField;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private boolean createPanelActive = false;
    private boolean editPanelActive = false;
    private boolean variableDiscountsSelected = false;

    private boolean isError[] = new boolean[7];

    private List<String[]> customerData;
    private List<String[]> valuedCustomerData;
    private List<String[]> variableDiscountData;
    private List<String[]> discounts;

    private final String[] tableColumns = {
            "ID",
            "Company Name",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Email",
            "Agreed Discount",
            "Discount Rate"
    };
    private final String[] discountTypes = {
            "Fixed Discount",
            "Flexible Discount",
            "Variable Discount"
    };
    private final String[] variableDiscountColumns = {
            "Task",
            "Discount"
    };

    public Customer(Bapers system) {
        this.system = system;

        try {
            customerData = DatabaseConnection.getData("customer");
            valuedCustomerData = DatabaseConnection.getData("valuedCustomer");
            assert customerData != null && valuedCustomerData != null;
            String[] temp;
            for (String[] vs : valuedCustomerData) {
                int i = 0;
                for (String[] cs : customerData) {
                    if (vs[0].equals(cs[0])) {
                        temp = new String[] { cs[0], cs[1], cs[2], cs[3], cs[4], cs[5], cs[6], vs[1], vs[2] };
                        customerData.set(i, temp);
                    } i++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/customer.png");
        bannerLabel.setIcon(bannerIcon);

        checkBoxIcon = new ImageIcon("data/graphics/test.png");
        selectedCheckBoxIcon = new ImageIcon("data/graphics/test2.png");
        createVcCheckBox.setIcon(checkBoxIcon);
        createVcCheckBox.setSelectedIcon(selectedCheckBoxIcon);
        editVcCheckBox.setIcon(checkBoxIcon);
        editVcCheckBox.setSelectedIcon(selectedCheckBoxIcon);

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

        ApplicationWindow.displayTable(table, customerData, tableColumns);

        // Table listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getSelectionModel().getSelectedItemsCount() == 1) {
                    editButton.addMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText(null);
                    deleteButton.addMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText(null);
                } else if (table.getSelectionModel().getSelectedItemsCount() > 1) {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select only 1 record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select only 1 record");
                } else {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select a record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select a record");
                }

                editButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
                deleteButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                tablePanel.setVisible(false);
                createPanel.setVisible(true);
                resetCreatePanel();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                tablePanel.setVisible(false);
                editPanel.setVisible(true);

                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                resetEditPanel(id);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createVcCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAgreedDiscountLabel.setVisible(createVcCheckBox.isSelected());
                createAgreedDiscountComboBox.setVisible(createVcCheckBox.isSelected());
                createDiscountRateLabel.setVisible(
                        createVcCheckBox.isSelected() && createAgreedDiscountComboBox.getSelectedIndex() == 0
                );
                createDiscountRateField.setVisible(
                        createVcCheckBox.isSelected() && createAgreedDiscountComboBox.getSelectedIndex() == 0
                );
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);

                removeCreateListeners();
            }
        });
        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(createVcCheckBox, createCompanyField, createFirstNameField, createLastNameField, createContactNumberField
                , createAddressFirstField, createAddressSecondField, createCityField, createPostcodeField, createEmailField
                , createAgreedDiscountComboBox, createDiscountRateField)) {

                    String address;
                    if (!createAddressSecondField.getText().isEmpty())
                        address = createAddressFirstField.getText()+", "+createAddressSecondField.getText()+", "
                                +createCityField.getText()+", "+createPostcodeField.getText();
                    else address = createAddressFirstField.getText()+", "+createCityField.getText()
                            +", "+createPostcodeField.getText();

                    String companyName;
                    if (createCompanyField.getText().isEmpty())
                        companyName = "";

                    // Valued Customer Insert
                    if (createVcCheckBox.isSelected()) {
                        String value = "";
                        switch (createAgreedDiscountComboBox.getSelectedIndex()) {
                            case 0:
                                value = createDiscountRateField.getText();
                                break;
                            case 2:
                                for (String[] vd : variableDiscountData) {
                                    System.out.println(Arrays.toString(vd));
                                    value += vd[2] + "-" + vd[1] + ",";
                                }
                                value = value.substring(0, value.length()-1);
                                break;
                        }

                        /*try {
                            if (DatabaseConnection.addCustomer(createCompanyField.getText(), createFirstNameField.getText(),
                                    createLastNameField.getText(), createContactNumberField.getText(), address, createEmailField.getText())) {
                                int ID = DatabaseConnection.getNextID("customer");
                                if (DatabaseConnection.addValuedCustomer(ID, String.valueOf(createAgreedDiscountComboBox.getSelectedItem()), value))
                                    system.changeScreen("customers", mainPanel);
                                else JOptionPane.showMessageDialog(mainPanel, "Couldn't insert valued customer, regular customer created");
                            } else JOptionPane.showMessageDialog(mainPanel, "Couldn't insert customer");
                        } catch (SQLException exception) { exception.printStackTrace(); }*/
                    }

                    // Regular Customer Insert
                    else {
                        try {
                            if (DatabaseConnection.addCustomer(createCompanyField.getText(), createFirstNameField.getText(),
                                    createLastNameField.getText(), createContactNumberField.getText(), address, createEmailField.getText())) {
                                system.changeScreen("customers", mainPanel);
                            } else JOptionPane.showMessageDialog(mainPanel, "Couldn't insert customer");
                        } catch (SQLException exception) { exception.printStackTrace(); }
                    }
                }
            }
        });
        editConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(editVcCheckBox, editCompanyField, editFirstNameField, editLastNameField, editContactNumberField
                        , editAddressFirstField, editAddressSecondField, editCityField, editPostcodeField, editEmailField
                        , editAgreedDiscountComboBox, editDiscountRateField)) {

                    String address;
                    if (!editAddressSecondField.getText().isEmpty())
                        address = editAddressFirstField.getText() + ", " + editAddressSecondField.getText() + ", "
                                + editCityField.getText() + ", " + editPostcodeField.getText();
                    else address = editAddressFirstField.getText() + ", " + editCityField.getText()
                            + ", " + editPostcodeField.getText();
                }
            }
        });
        editCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createAgreedDiscountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDiscountRateLabel.setVisible(
                        createVcCheckBox.isSelected() && createAgreedDiscountComboBox.getSelectedIndex() == 0
                );
                createDiscountRateField.setVisible(
                        createVcCheckBox.isSelected() && createAgreedDiscountComboBox.getSelectedIndex() == 0
                );

                if (!variableDiscountsSelected && createAgreedDiscountComboBox.getSelectedIndex() == 2) {
                    createPanel.setVisible(false);
                    variableDiscountPanel.setVisible(true);
                    resetVariableDiscountPanel();
                }
                createVariableDiscountsLabel.setVisible(
                        variableDiscountsSelected && createAgreedDiscountComboBox.getSelectedIndex() == 2
                );
                createVariableChangeButton.setVisible(
                        variableDiscountsSelected && createAgreedDiscountComboBox.getSelectedIndex() == 2
                );
            }
        });
        variableAssignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateVariableDiscount()) {
                    variableDiscountsSelected = true;
                    for (int row = 0; row<variableDiscountTable.getRowCount(); row++) {
                        String value = variableDiscountTable.getModel().getValueAt(row, 1).toString();
                        String[] temp = { variableDiscountData.get(row)[1], value };
                        variableDiscountData.set(row, temp);
                    }

                    variableDiscountPanel.setVisible(false);
                    if (createPanelActive) {
                        createPanel.setVisible(true);
                        createVariableDiscountsLabel.setVisible(true);
                        createVariableChangeButton.setVisible(true);
                    }
                    /*else if (editPanelActive) {
                        editPanel.setVisible(true);
                    }*/
                }
            }
        });
        createVariableChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                variableDiscountPanel.setVisible(true);
            }
        });
        variableCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!variableDiscountsSelected) {
                    if (createPanelActive)
                        createAgreedDiscountComboBox.setSelectedIndex(0);
                    else if (editPanelActive)
                        editAgreedDiscountComboBox.setSelectedIndex(0);
                }

                variableDiscountPanel.setVisible(false);
                if (createPanelActive)
                    createPanel.setVisible(true);
                else if (editPanelActive)
                    editPanel.setVisible(true);
            }
        });
        editVariableChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel.setVisible(false);
                variableDiscountPanel.setVisible(true);
            }
        });
        editAgreedDiscountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDiscountRateLabel.setVisible(
                        editVcCheckBox.isSelected() && editAgreedDiscountComboBox.getSelectedIndex() == 0
                );
                editDiscountRateField.setVisible(
                        editVcCheckBox.isSelected() && editAgreedDiscountComboBox.getSelectedIndex() == 0
                );

                if (!variableDiscountsSelected && editAgreedDiscountComboBox.getSelectedIndex() == 2) {
                    editPanel.setVisible(false);
                    variableDiscountPanel.setVisible(true);
                    resetVariableDiscountPanel();
                }
                editVariableDiscountsLabel.setVisible(
                        variableDiscountsSelected && editAgreedDiscountComboBox.getSelectedIndex() == 2
                );
                editVariableChangeButton.setVisible(
                        variableDiscountsSelected && editAgreedDiscountComboBox.getSelectedIndex() == 2
                );
            }
        });
    }

    private boolean validateVariableDiscount() {
        boolean errorDetected = false;
        for (int row = 0; row<variableDiscountTable.getRowCount(); row++) {
            if (variableDiscountTable.getModel().getValueAt(row, 1) != null) {
                String value = variableDiscountTable.getModel().getValueAt(row, 1).toString();
                if (!value.matches(ApplicationWindow.discountRegex)) {

                    errorDetected = true;
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please enter only valid digits (0-100)");
                errorDetected = true;
                break;
            }
        }
        return errorDetected;
    }

    private boolean validatePanel
            (JCheckBox vcCheckBox, JTextField companyField, JTextField firstNameField, JTextField lastNameField, JTextField contactNumberField,
             JTextField addressFirstField, JTextField addressSecondField, JTextField cityField, JTextField postcodeField,
             JTextField emailField, JComboBox agreedDiscountComboBox, JTextField discountRateField) {

        if (!companyField.getText().isEmpty() && !companyField.getText().matches(ApplicationWindow.companyRegex)) {
            companyField.setBorder(ApplicationWindow.borderError);
            companyField.setToolTipText("Please enter only letters, numbers, commas or '-'");
        } else {
            companyField.setBorder(null);
            companyField.setToolTipText(null);
        }

        if (!firstNameField.getText().matches(ApplicationWindow.nameRegex)) {
            firstNameField.setBorder(ApplicationWindow.borderError);
            firstNameField.setToolTipText("Please enter a valid name, e.g. 'John'");
        } else {
            firstNameField.setBorder(null);
            firstNameField.setToolTipText(null);
        }

        if (!lastNameField.getText().matches(ApplicationWindow.nameRegex)) {
            lastNameField.setBorder(ApplicationWindow.borderError);
            lastNameField.setToolTipText("Please enter a valid name, e.g. 'John'");
        } else {
            lastNameField.setBorder(null);
            lastNameField.setToolTipText(null);
        }

        if (!contactNumberField.getText().matches(ApplicationWindow.contactNumberRegex)) {
            contactNumberField.setBorder(ApplicationWindow.borderError);
            contactNumberField.setToolTipText("Please enter a valid UK number (11 digits)");
        } else {
            contactNumberField.setBorder(null);
            contactNumberField.setToolTipText(null);
        }

        if (!addressFirstField.getText().matches(ApplicationWindow.addressLineRegex)) {
            addressFirstField.setBorder(ApplicationWindow.borderError);
            addressFirstField.setToolTipText("Please enter only letters and numbers");
        } else {
            addressFirstField.setBorder(null);
            addressFirstField.setToolTipText(null);
        }

        if (!addressSecondField.getText().isEmpty() && !addressSecondField.getText().matches(ApplicationWindow.addressLineRegex)) {
            addressSecondField.setBorder(ApplicationWindow.borderError);
            addressSecondField.setToolTipText("Please enter only letters and numbers");
        } else {
            addressSecondField.setBorder(null);
            addressSecondField.setToolTipText(null);
        }

        if (!cityField.getText().matches(ApplicationWindow.cityRegex)) {
            cityField.setBorder(ApplicationWindow.borderError);
            cityField.setToolTipText("Please enter only letters");
        } else {
            cityField.setBorder(null);
            cityField.setToolTipText(null);
        }

        if (!postcodeField.getText().matches(ApplicationWindow.postcodeRegex)) {
            postcodeField.setBorder(ApplicationWindow.borderError);
            postcodeField.setToolTipText("Please enter a valid UK postcode");
        } else {
            postcodeField.setBorder(null);
            postcodeField.setToolTipText(null);
        }

        if (!emailField.getText().matches(ApplicationWindow.emailRegex)) {
            emailField.setBorder(ApplicationWindow.borderError);
            emailField.setToolTipText("Please enter in email format");
        } else {
            emailField.setBorder(null);
            emailField.setToolTipText(null);
        }

        if ((vcCheckBox.isSelected() && agreedDiscountComboBox.getSelectedIndex() == 0)
                && !discountRateField.getText().matches(ApplicationWindow.discountRegex)) {
            discountRateField.setBorder(ApplicationWindow.borderError);
            discountRateField.setToolTipText("Please enter only digits (0,100)");
        } else {
            discountRateField.setBorder(null);
            discountRateField.setToolTipText(null);
        }

        if (!(firstNameField.getBorder() == ApplicationWindow.borderError || lastNameField.getBorder() == ApplicationWindow.borderError
        || contactNumberField.getBorder() == ApplicationWindow.borderError || addressFirstField.getBorder() == ApplicationWindow.borderError
        || addressSecondField.getBorder() == ApplicationWindow.borderError || cityField.getBorder() == ApplicationWindow.borderError
        || postcodeField.getBorder() == ApplicationWindow.borderError || emailField.getBorder() == ApplicationWindow.borderError)) {

            if (vcCheckBox.isSelected()) {
                boolean vcError = false;
                switch (agreedDiscountComboBox.getSelectedIndex()) {
                    case 0:
                        vcError = discountRateField.getBorder() == ApplicationWindow.borderError;
                        break;
                    case 2:
                        vcError = validateVariableDiscount();
                        break;
                }
                return !vcError;
            }
            return true;
        } else return false;
    }

    private void resetCreatePanel() {
        createAgreedDiscountComboBox.setModel(new DefaultComboBoxModel<>(discountTypes));

        createVcCheckBox.setSelected(false);
        createCompanyField.setBorder(null);
        createFirstNameField.setBorder(null);
        createLastNameField.setBorder(null);
        createContactNumberField.setBorder(null);
        createAddressFirstField.setBorder(null);
        createAddressSecondField.setBorder(null);
        createCityField.setBorder(null);
        createPostcodeField.setBorder(null);
        createEmailField.setBorder(null);
        createDiscountRateField.setBorder(null);
        createAgreedDiscountComboBox.setSelectedIndex(0);
        createVariableDiscountsLabel.setVisible(false);
        createVariableChangeButton.setVisible(false);
        variableDiscountsSelected = false;

        addCreateListeners();
    }

    private void resetEditPanel(int id) {
        editAgreedDiscountComboBox.setModel(new DefaultComboBoxModel<>(discountTypes));

        editCompanyField.setBorder(null);
        editFirstNameField.setBorder(null);
        editLastNameField.setBorder(null);
        editContactNumberField.setBorder(null);
        editAddressFirstField.setBorder(null);
        editAddressSecondField.setBorder(null);
        editCityField.setBorder(null);
        editPostcodeField.setBorder(null);
        editEmailField.setBorder(null);
        editDiscountRateField.setBorder(null);

        String[] customerData = DatabaseConnection.getRowBySingleID("customer", id);

        assert customerData != null;
        editFirstNameField.setText(customerData[1]);
        editLastNameField.setText(customerData[2]);
        editContactNumberField.setText(customerData[3]);

        String[] address = customerData[4].split(", ");

        editAddressFirstField.setText(address[0]);
        editAddressSecondField.setText(address[1]);
        editCityField.setText(address[2]);
        editPostcodeField.setText(address[3]);
        editEmailField.setText(customerData[5]);


        String[] vcData;
        try {
            if (!table.getModel().getValueAt(table.getSelectedRow(), 6).toString().isEmpty()) {
                vcData = DatabaseConnection.getRowBySingleID("valuedCustomer", id);
                editVcCheckBox.setSelected(true);
                editAgreedDiscountComboBox.setVisible(true);

                assert vcData != null;

                if (vcData[1].equals("Variable Discount"))
                    variableDiscountsSelected = true;

                editAgreedDiscountComboBox.setSelectedIndex(0);
                for (int i=0; i<discountTypes.length; i++) {
                    if (Objects.equals(editAgreedDiscountComboBox.getSelectedItem(), vcData[1])) {
                        editAgreedDiscountComboBox.setSelectedIndex(i);
                        break;
                    }
                    if (i+1 != discountTypes.length)
                        editAgreedDiscountComboBox.setSelectedIndex(i+1);
                }
                switch (editAgreedDiscountComboBox.getSelectedIndex()) {
                    case 0:
                        editDiscountRateLabel.setVisible(true);
                        editDiscountRateField.setText(vcData[2]);
                        break;
                    case 2:
                        editVariableDiscountsLabel.setVisible(true);
                        editVariableChangeButton.setVisible(true);

                        variableDiscountData = DatabaseConnection.getAvailableTasks();
                        String[] values = vcData[2].split(",");
                        int i=0;
                        for (String v : values) {
                            String[] temp = v.split("-");
                            values[i] = temp[1];
                            i++;
                        }
                        assert variableDiscountData != null;
                        int j = 0;
                        for (String[] vd : variableDiscountData) {
                            String[] temp = { vd[1], values[j] };
                            variableDiscountData.set(j, temp);
                            j++;
                        }
                        ApplicationWindow.displayTable(variableDiscountTable, variableDiscountData, variableDiscountColumns);
                        variableDiscountTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));

                        break;
                }
            }
        } catch (NullPointerException ignored) { editVcCheckBox.setSelected(false); }

        addEditListeners();
    }

    private void resetVariableDiscountPanel() {
        variableDiscountData = DatabaseConnection.getAvailableTasks();
        assert variableDiscountData != null;
        int i = 0;
        for (String[] vd : variableDiscountData) {
            String[] temp = { vd[1], null, vd[0] };
            variableDiscountData.set(i, temp);
            i++;
        }

        ApplicationWindow.displayTable(variableDiscountTable, variableDiscountData, variableDiscountColumns);
        variableDiscountTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));
    }

    private void addEditListeners() {
        editCompanyField.addKeyListener(ApplicationWindow.regexListener);
        editFirstNameField.addKeyListener(ApplicationWindow.regexListener);
        editLastNameField.addKeyListener(ApplicationWindow.regexListener);
        editContactNumberField.addKeyListener(ApplicationWindow.regexListener);
        editAddressFirstField.addKeyListener(ApplicationWindow.regexListener);
        editEmailField.addKeyListener(ApplicationWindow.regexListener);
        editDiscountRateField.addKeyListener(ApplicationWindow.regexListener);
        editPostcodeField.addKeyListener(ApplicationWindow.regexListener);
        editAddressSecondField.addKeyListener(ApplicationWindow.regexListener);
        editCityField.addKeyListener(ApplicationWindow.regexListener);
        editPanelActive = true;
    }

    private void removeEditListeners() {
        editCompanyField.removeKeyListener(ApplicationWindow.regexListener);
        editFirstNameField.removeKeyListener(ApplicationWindow.regexListener);
        editLastNameField.removeKeyListener(ApplicationWindow.regexListener);
        editContactNumberField.removeKeyListener(ApplicationWindow.regexListener);
        editAddressFirstField.removeKeyListener(ApplicationWindow.regexListener);
        editEmailField.removeKeyListener(ApplicationWindow.regexListener);
        editDiscountRateField.removeKeyListener(ApplicationWindow.regexListener);
        editPostcodeField.removeKeyListener(ApplicationWindow.regexListener);
        editAddressSecondField.removeKeyListener(ApplicationWindow.regexListener);
        editCityField.removeKeyListener(ApplicationWindow.regexListener);
        editPanelActive = false;
    }

    private void addCreateListeners() {
        createCompanyField.addKeyListener(ApplicationWindow.regexListener);
        createFirstNameField.addKeyListener(ApplicationWindow.regexListener);
        createLastNameField.addKeyListener(ApplicationWindow.regexListener);
        createContactNumberField.addKeyListener(ApplicationWindow.regexListener);
        createAddressFirstField.addKeyListener(ApplicationWindow.regexListener);
        createEmailField.addKeyListener(ApplicationWindow.regexListener);
        createDiscountRateField.addKeyListener(ApplicationWindow.regexListener);
        createPostcodeField.addKeyListener(ApplicationWindow.regexListener);
        createAddressSecondField.addKeyListener(ApplicationWindow.regexListener);
        createCityField.addKeyListener(ApplicationWindow.regexListener);
        createPanelActive = true;
    }

    private void removeCreateListeners() {
        createCompanyField.removeKeyListener(ApplicationWindow.regexListener);
        createFirstNameField.removeKeyListener(ApplicationWindow.regexListener);
        createLastNameField.removeKeyListener(ApplicationWindow.regexListener);
        createContactNumberField.removeKeyListener(ApplicationWindow.regexListener);
        createAddressFirstField.removeKeyListener(ApplicationWindow.regexListener);
        createEmailField.removeKeyListener(ApplicationWindow.regexListener);
        createDiscountRateField.removeKeyListener(ApplicationWindow.regexListener);
        createPostcodeField.removeKeyListener(ApplicationWindow.regexListener);
        createAddressSecondField.removeKeyListener(ApplicationWindow.regexListener);
        createCityField.removeKeyListener(ApplicationWindow.regexListener);
        createPanelActive = false;
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

    public JPanel getPanel() {
        return mainPanel;
    }

    public void setPanel(JPanel panel) {
        this.mainPanel = panel;
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
