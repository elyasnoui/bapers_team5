
package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class Staff {
    private Bapers system;
    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable table;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JButton createButton;
    private JLabel bannerLabel;
    private JPanel sidePanel;
    private JLabel usernameLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel buttonPanel;
    private JLabel roleLabel;
    private JPanel tablePanel;
    private JPanel createPanel;
    private JTextField createFirstNameField;
    private JTextField createLastNameField;
    private JTextField createContactNumberField;
    private JTextField createAddressFirstField;
    private JTextField createEmailField;
    private JTextField createPostcodeField;
    private JTextField createAddressSecondField;
    private JTextField createCityField;
    private JComboBox createTitleComboBox;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JPasswordField createPasswordField;
    private JTextField createUsernameField;
    private JPasswordField createPasswordConfirmField;
    private JTextField createRoleField;
    private JComboBox editTitleComboBox;
    private JTextField editFirstNameField;
    private JTextField editLastNameField;
    private JTextField editContactNumberField;
    private JPanel editPanel;
    private JTextField editAddressFirstField;
    private JTextField editAddressSecondField;
    private JTextField editCityField;
    private JTextField editPostcodeField;
    private JTextField editUsernameField;
    private JPasswordField editPasswordField;
    private JPasswordField editPasswordConfirmField;
    private String editHexPassword;
    private JTextField editRoleField;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private JTextField editEmailField;
    private JLabel editStaffIDField;
    private ImageIcon bannerIcon;
    private List<String[]> staffData;
    private final String[] tableColumns = {
            "ID",
            "Title",
            "First Name",
            "Last Name",
            "Contact Number",
            "Address",
            "Email",
            "Username",
            "Password",
            "Role"
    };
    private final String[] titles = {
            "Mr",
            "Mrs",
            "Ms",
            "Dr",
            "Prof",
            "Prefer not to say"
    };

    public Staff(Bapers system) {
        this.system = system;

        try {
            staffData = DatabaseConnection.getData("staff");
            for (String[] ss : staffData)
                ss[8] = "•••••••";
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/staff.png");
        bannerLabel.setIcon(bannerIcon);

        addMouseListeners();

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

        ApplicationWindow.displayTable(table, staffData, tableColumns);

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
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                editPanel.setVisible(true);

                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                resetEditPanel(id);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                DatabaseConnection.removeStaff(id);
            }
        });

        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(createFirstNameField, createLastNameField, createContactNumberField, createAddressFirstField
                , createAddressSecondField, createCityField, createPostcodeField, createEmailField, createUsernameField
                , createPasswordField, createPasswordConfirmField, createRoleField)) {

                    String address;
                    if (!createAddressSecondField.getText().isEmpty())
                        address = createAddressFirstField.getText()+", "+createAddressSecondField.getText()+", "
                                +createCityField.getText()+", "+createPostcodeField.getText();
                    else address = createAddressFirstField.getText()+", "+createCityField.getText()
                            +", "+createPostcodeField.getText();

                    String title;
                    if (createTitleComboBox.getSelectedIndex() == 5)
                        title = "";
                    else
                        title = String.valueOf(createTitleComboBox.getSelectedItem());

                    try {
                        if (!DatabaseConnection.addStaff(title, createFirstNameField.getText(), createLastNameField.getText()
                        , createContactNumberField.getText(), address, createEmailField.getText(), createUsernameField.getText()
                        , DatabaseConnection.md5(createPasswordField.getText()), createRoleField.getText()))
                            JOptionPane.showMessageDialog(mainPanel, "Couldn't insert staff");
                        system.changeScreen("staff", mainPanel);
                    } catch (NoSuchAlgorithmException | SQLException | UnsupportedEncodingException exp) { exp.printStackTrace(); }
                }
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
        editConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(editFirstNameField, editLastNameField, editContactNumberField, editAddressFirstField
                        , editAddressSecondField, editCityField, editPostcodeField, editEmailField, editUsernameField
                        , editPasswordField, editPasswordConfirmField, editRoleField)) {

                    String address;
                    if (!editAddressSecondField.getText().isEmpty())
                        address = editAddressFirstField.getText()+", "+editAddressSecondField.getText()+", "
                                +editCityField.getText()+", "+editPostcodeField.getText();
                    else address = editAddressFirstField.getText()+", "+editCityField.getText()
                            +", "+editPostcodeField.getText();

                    String title;
                    if (editTitleComboBox.getSelectedIndex() == 5)
                        title = "";
                    else
                        title = String.valueOf(editTitleComboBox.getSelectedItem());

                    try {
                        if (!editPasswordField.getText().isEmpty()) {
                            if (!DatabaseConnection.editStaff(Integer.parseInt(editStaffIDField.getText()), title, editFirstNameField.getText(),
                                    editLastNameField.getText(), editContactNumberField.getText(), address, editEmailField.getText(),
                                    editUsernameField.getText(), DatabaseConnection.md5(editPasswordField.getText()), editRoleField.getText()))
                                JOptionPane.showMessageDialog(mainPanel, "Couldn't edit staff with updated password");
                        } else {
                            if (!DatabaseConnection.editStaff(Integer.parseInt(editStaffIDField.getText()), title, editFirstNameField.getText(),
                                    editLastNameField.getText(), editContactNumberField.getText(), address, editEmailField.getText(),
                                    editUsernameField.getText(), editHexPassword, editRoleField.getText()))
                                JOptionPane.showMessageDialog(mainPanel, "Couldn't edit staff with previous password");
                        }
                        system.changeScreen("staff", mainPanel);
                    } catch (NoSuchAlgorithmException | SQLException | UnsupportedEncodingException exp) { exp.printStackTrace(); }
                }
            }
        });
        editCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);

                removeEditListeners();
            }
        });
    }

    private boolean validatePanel
            (JTextField firstNameField, JTextField lastNameField, JTextField contactNumberField,
             JTextField addressFirstField, JTextField addressSecondField, JTextField cityField, JTextField postcodeField,
             JTextField emailField, JTextField usernameField, JPasswordField passwordField, JPasswordField passwordConfirmField,
             JTextField roleField) {

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

        if (!usernameField.getText().matches(ApplicationWindow.usernameRegex)) {
            usernameField.setBorder(ApplicationWindow.borderError);
            usernameField.setToolTipText("Please enter only letters and numbers (5,15)");
        } else {
            usernameField.setBorder(null);
            usernameField.setToolTipText(null);
        }

        if (passwordField.getName().equals("newPassword")) {
            if (passwordField.getText().isEmpty()) {
                passwordField.setBorder(null);
                passwordField.setToolTipText(null);
            } else if (!passwordField.getText().matches(ApplicationWindow.passwordRegex)) {
                passwordField.setBorder(ApplicationWindow.borderError);
                passwordField.setToolTipText("Please enter only letters and numbers (5,15)");
            } else {
                passwordField.setBorder(null);
                passwordField.setToolTipText(null);
            }
        }

        if (passwordConfirmField.getName().equals("newPassword")) {
            if (passwordConfirmField.getText().isEmpty()) {
                passwordConfirmField.setBorder(null);
                passwordConfirmField.setToolTipText(null);
            } else if (!passwordConfirmField.getText().matches(ApplicationWindow.passwordRegex)) {
                passwordConfirmField.setBorder(ApplicationWindow.borderError);
                passwordConfirmField.setToolTipText("Please enter only letters and numbers (5,15)");
            } else if (!String.valueOf(passwordConfirmField.getText()).equals(String.valueOf(passwordField.getText()))) {
                passwordConfirmField.setBorder(ApplicationWindow.borderError);
                passwordConfirmField.setToolTipText("Passwords do not match");
            } else {
                passwordConfirmField.setBorder(null);
                passwordConfirmField.setToolTipText(null);
            }
        }

        if (!roleField.getText().matches(ApplicationWindow.roleRegex)) {
            roleField.setBorder(ApplicationWindow.borderError);
            roleField.setToolTipText("Please enter only letters");
        } else {
            roleField.setBorder(null);
            roleField.setToolTipText(null);
        }

        if (!(firstNameField.getBorder() == ApplicationWindow.borderError || lastNameField.getBorder() == ApplicationWindow.borderError
        || contactNumberField.getBorder() == ApplicationWindow.borderError || addressFirstField.getBorder() == ApplicationWindow.borderError
        || addressSecondField.getBorder() == ApplicationWindow.borderError || cityField.getBorder() == ApplicationWindow.borderError
        || postcodeField.getBorder() == ApplicationWindow.borderError || emailField.getBorder() == ApplicationWindow.borderError
        || usernameField.getBorder() == ApplicationWindow.borderError || passwordField.getBorder() == ApplicationWindow.borderError
        || passwordConfirmField.getBorder() == ApplicationWindow.borderError || roleField.getBorder() == ApplicationWindow.borderError))
            return true;
        else return false;
    }

    private void resetCreatePanel() {
        createTitleComboBox.setModel(new DefaultComboBoxModel(titles));
        createFirstNameField.setText("");
        createFirstNameField.setBorder(null);
        createLastNameField.setText("");
        createLastNameField.setBorder(null);
        createContactNumberField.setText("");
        createContactNumberField.setBorder(null);
        createAddressFirstField.setText("");
        createAddressFirstField.setBorder(null);
        createAddressSecondField.setText("");
        createAddressSecondField.setBorder(null);
        createCityField.setText("");
        createCityField.setBorder(null);
        createPostcodeField.setText("");
        createPostcodeField.setBorder(null);
        createEmailField.setText("");
        createEmailField.setBorder(null);
        createUsernameField.setText("");
        createUsernameField.setBorder(null);
        createPasswordField.setText("");
        createPasswordField.setBorder(null);
        createPasswordConfirmField.setText("");
        createPasswordConfirmField.setBorder(null);
        createRoleField.setText("");
        createRoleField.setBorder(null);

        addCreateListeners();
    }

    private void resetEditPanel(int id) {
        editTitleComboBox.setModel(new DefaultComboBoxModel(titles));
        editTitleComboBox.setSelectedIndex(0);
        editFirstNameField.setBorder(null);
        editLastNameField.setBorder(null);
        editContactNumberField.setBorder(null);
        editAddressFirstField.setBorder(null);
        editAddressSecondField.setBorder(null);
        editCityField.setBorder(null);
        editPostcodeField.setBorder(null);
        editEmailField.setBorder(null);
        editUsernameField.setBorder(null);
        editPasswordField.setBorder(null);
        editPasswordConfirmField.setBorder(null);
        editRoleField.setBorder(null);

        String[] staffData = DatabaseConnection.getRowBySingleID("staff", id);
        assert staffData != null;
        editStaffIDField.setText(staffData[0]);
        while (!String.valueOf(editTitleComboBox.getSelectedItem()).equals(staffData[1]))
            editTitleComboBox.setSelectedIndex(editTitleComboBox.getSelectedIndex()+1);
        editFirstNameField.setText(staffData[2]);
        editLastNameField.setText(staffData[3]);
        editContactNumberField.setText(staffData[4]);
        String[] address = staffData[5].split(", ");
        editAddressFirstField.setText(address[0]);
        if (address.length == 4) {
            editAddressSecondField.setText(address[1]);
            editCityField.setText(address[2]);
            editPostcodeField.setText(address[3]);
        } else {
            editAddressSecondField.setText("");
            editCityField.setText(address[1]);
            editPostcodeField.setText(address[2]);
        }
        editEmailField.setText(staffData[6]);
        editUsernameField.setText(staffData[7]);
        editHexPassword = staffData[8];
        editRoleField.setText(staffData[9]);

        addEditListeners();
    }

    private void addEditListeners() {
        editFirstNameField.addKeyListener(ApplicationWindow.regexListener);
        editLastNameField.addKeyListener(ApplicationWindow.regexListener);
        editContactNumberField.addKeyListener(ApplicationWindow.regexListener);
        editAddressFirstField.addKeyListener(ApplicationWindow.regexListener);
        editAddressSecondField.addKeyListener(ApplicationWindow.regexListener);
        editCityField.addKeyListener(ApplicationWindow.regexListener);
        editPostcodeField.addKeyListener(ApplicationWindow.regexListener);
        editEmailField.addKeyListener(ApplicationWindow.regexListener);
        editUsernameField.addKeyListener(ApplicationWindow.regexListener);
        editPasswordField.addKeyListener(ApplicationWindow.regexListener);
        editPasswordConfirmField.addKeyListener(ApplicationWindow.regexListener);
        editRoleField.addKeyListener(ApplicationWindow.regexListener);
    }

    private void removeEditListeners() {
        editFirstNameField.removeKeyListener(ApplicationWindow.regexListener);
        editLastNameField.removeKeyListener(ApplicationWindow.regexListener);
        editContactNumberField.removeKeyListener(ApplicationWindow.regexListener);
        editAddressFirstField.removeKeyListener(ApplicationWindow.regexListener);
        editAddressSecondField.removeKeyListener(ApplicationWindow.regexListener);
        editCityField.removeKeyListener(ApplicationWindow.regexListener);
        editPostcodeField.removeKeyListener(ApplicationWindow.regexListener);
        editEmailField.removeKeyListener(ApplicationWindow.regexListener);
        editUsernameField.removeKeyListener(ApplicationWindow.regexListener);
        editPasswordField.removeKeyListener(ApplicationWindow.regexListener);
        editPasswordConfirmField.removeKeyListener(ApplicationWindow.regexListener);
        editRoleField.removeKeyListener(ApplicationWindow.regexListener);
        editHexPassword = null;
    }

    private void addCreateListeners() {
        createFirstNameField.addKeyListener(ApplicationWindow.regexListener);
        createLastNameField.addKeyListener(ApplicationWindow.regexListener);
        createContactNumberField.addKeyListener(ApplicationWindow.regexListener);
        createAddressFirstField.addKeyListener(ApplicationWindow.regexListener);
        createAddressSecondField.addKeyListener(ApplicationWindow.regexListener);
        createCityField.addKeyListener(ApplicationWindow.regexListener);
        createPostcodeField.addKeyListener(ApplicationWindow.regexListener);
        createEmailField.addKeyListener(ApplicationWindow.regexListener);
        createUsernameField.addKeyListener(ApplicationWindow.regexListener);
        createPasswordField.addKeyListener(ApplicationWindow.regexListener);
        createPasswordConfirmField.addKeyListener(ApplicationWindow.regexListener);
        createRoleField.addKeyListener(ApplicationWindow.regexListener);
    }

    private void removeCreateListeners() {
        createFirstNameField.removeKeyListener(ApplicationWindow.regexListener);
        createLastNameField.removeKeyListener(ApplicationWindow.regexListener);
        createContactNumberField.removeKeyListener(ApplicationWindow.regexListener);
        createAddressFirstField.removeKeyListener(ApplicationWindow.regexListener);
        createAddressSecondField.removeKeyListener(ApplicationWindow.regexListener);
        createCityField.removeKeyListener(ApplicationWindow.regexListener);
        createPostcodeField.removeKeyListener(ApplicationWindow.regexListener);
        createEmailField.removeKeyListener(ApplicationWindow.regexListener);
        createUsernameField.removeKeyListener(ApplicationWindow.regexListener);
        createPasswordField.removeKeyListener(ApplicationWindow.regexListener);
        createPasswordConfirmField.removeKeyListener(ApplicationWindow.regexListener);
        createRoleField.removeKeyListener(ApplicationWindow.regexListener);
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
