package GUI;

import System.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.List;

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
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactNumberField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField agreedDiscountField;
    private JTextField discountRateField;
    private JButton popupCreateButton;
    private JButton popupCancelButton;
    private JPanel tablePanel;
    private ImageIcon bannerIcon;

    private JCheckBox vcCheckBox;
    private JLabel agreedDiscountLabel;
    private JLabel discountRateLabel;
    private JLabel firstNameEX;
    private JLabel lastNameEX;
    private JLabel contactNumEX;
    private JLabel addressEX;
    private JLabel emailEX;
    private JLabel agreedDiscountEX;
    private JLabel discountRateEX;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;

    private boolean isError[] = new boolean[7];

    private List<String[]> customerData;
    private List<String[]> valuedCustomerData;
    private final String[] tableColumns = {
            "ID",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Email",
            "Agreed Discount",
            "Discount Rate"
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
                        temp = new String[] { cs[0], cs[1], cs[2], cs[3], cs[4], cs[5], vs[1], vs[2] };
                        customerData.set(i, temp);
                    } i++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/customer.png");
        bannerLabel.setIcon(bannerIcon);

        checkBoxIcon = new ImageIcon("data/graphics/test.png");
        selectedCheckBoxIcon = new ImageIcon("data/graphics/test2.png");
        vcCheckBox.setIcon(checkBoxIcon);
        vcCheckBox.setSelectedIcon(selectedCheckBoxIcon);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("logout", mainPanel);
            }
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

        ApplicationWindow.displayTable(table, customerData, tableColumns);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });
        vcCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean visibility = vcCheckBox.isSelected();
                agreedDiscountLabel.setVisible(visibility);
                agreedDiscountField.setVisible(visibility);
                discountRateLabel.setVisible(visibility);
                discountRateField.setVisible(visibility);
                agreedDiscountEX.setVisible(false);
                discountRateEX.setVisible(false);
            }
        });
        popupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                tablePanel.setVisible(true);
            }
        });
        popupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String name_regex ="^[A-Z][a-z]{3,20}";
                final String number_regex = "^(\\+44\\s?7\\d{3}|\\(?07\\d{3}\\)?)\\s?\\d{3}\\s?\\d{3}$";
                final String address_regex = "^(.{1,100})$";
                final String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                final String agreed_regex = "Variable|Flexible|Fixed";
                final String variable_regex = /*"([0-9]{1,2}(,[0-9]{1,2})){7}";*/ "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$";// Need to be made dynamic according to number of tasks
                final String fixed_regex = "^[0-9]{1,2}";
                final String flexible_regex = "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$";

                // Hide all '!', before checking for errors
                firstNameEX.setVisible(false);
                lastNameEX.setVisible(false);
                contactNumEX.setVisible(false);
                addressEX.setVisible(false);
                emailEX.setVisible(false);
                agreedDiscountEX.setVisible(false);
                discountRateEX.setVisible(false);

                // Check All fields against Regex Strings

                firstNameEX.setVisible(!firstNameField.getText().matches(name_regex));
                isError[0] = !firstNameField.getText().matches(name_regex);

                lastNameEX.setVisible(!lastNameField.getText().matches(name_regex));
                isError[1] = !lastNameField.getText().matches(name_regex);

                contactNumEX.setVisible(!contactNumberField.getText().matches(number_regex));
                isError[2] = !contactNumberField.getText().matches(number_regex);

                addressEX.setVisible(!addressField.getText().matches(address_regex));
                isError[3] = !addressField.getText().matches(address_regex);

                emailEX.setVisible(!emailField.getText().matches(email_regex));
                isError[4] = !emailField.getText().matches(email_regex);

                // If Checkbox selected, then run Regex Checks
                if (vcCheckBox.isSelected()) {
                    agreedDiscountEX.setVisible(!agreedDiscountField.getText().matches(agreed_regex));
                    isError[5] = !agreedDiscountField.getText().matches(agreed_regex);

                    discountRateEX.setVisible(!discountRateField.getText().matches(variable_regex));
                    isError[6] = !discountRateField.getText().matches(variable_regex);
                }

                // If Agreed Field is either, Variable/Fixed/Flexible then select the Regex accordingly
                /*if (vcCheckBox.isSelected()){
                    if (!agreedDiscountField.getText().matches(agreed_regex)) {
                        error = true;
                        agreedDiscountEX.setVisible(true);
                    }

                    if(agreedDiscountField.getText().equals("Variable")) {
                        if (!discountRateField.getText().matches(variable_regex)) {
                            error = true;
                            discountRateEX.setVisible(true);
                        }
                    }
                    else if(agreedDiscountField.getText().equals("Fixed")) {
                        if (!discountRateField.getText().matches(fixed_regex)) {
                            error = true;
                            discountRateEX.setVisible(true);
                        }
                    }
                    else if(agreedDiscountField.getText().equals("Flexible")) {
                        if (!discountRateField.getText().matches(flexible_regex)) {
                            error = true;
                            discountRateEX.setVisible(true);
                        }
                    }
                }*/

                Boolean errorsDetected = false;
                for (boolean err : isError) {
                    if (err) {
                        errorsDetected = true;
                        break;
                    }
                }

                if (!errorsDetected) {
                    try {
                        int ID = DatabaseConnection.getNextID("customer");

                        // If ID test didn't fail
                        if (ID != -1) {
                            DatabaseConnection.addCustomer(firstNameField.getText(), lastNameField.getText(), contactNumberField.getText(),
                                    addressField.getText(), emailField.getText());
                            if (vcCheckBox.isSelected())
                                DatabaseConnection.addValuedCustomer(ID, agreedDiscountField.getText(), discountRateField.getText());
                        }

                        system.changeScreen("customers", mainPanel);

                    } catch (SQLException exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(mainPanel, "Insertion failed, please try again.");
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    //String IDs = customerData.get(table.getSelectedRow())[0];

                    for (int id : table.getSelectedRows()) {
                        try {
                            int ID = Integer.parseInt(customerData.get(id)[0]);
                            DatabaseConnection.deleteCustomerFromTables(ID);
                            DatabaseConnection.removeCustomer(ID);
                            system.changeScreen("customers", mainPanel);
                        }
                        catch (SQLException exception) { exception.printStackTrace(); }
                    }
                }
            }
        });
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
