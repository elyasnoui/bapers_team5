
package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JButton popupCancelButton;
    private JButton popupCreateButton;
    private ImageIcon bannerIcon;
    private List<String[]> staffData;
    private final String[] tableColumns = {
            "ID",
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
                ss[7] = "•••••••";
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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                tablePanel.setVisible(false);
                createPanel.setVisible(true);

                resetCreatePanel();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        if (!passwordField.getText().matches(ApplicationWindow.passwordRegex)) {
            passwordField.setBorder(ApplicationWindow.borderError);
            passwordField.setToolTipText("Please enter only letters and numbers (5,15)");
        } else {
            passwordField.setBorder(null);
            passwordField.setToolTipText(null);
        }

        if (!passwordConfirmField.getText().matches(ApplicationWindow.passwordRegex)) {
            passwordConfirmField.setBorder(ApplicationWindow.borderError);
            passwordConfirmField.setToolTipText("Please enter only letters and numbers (5,15)");
        } else if (!String.valueOf(passwordConfirmField.getText()).equals(String.valueOf(passwordField.getText()))) {
            passwordConfirmField.setBorder(ApplicationWindow.borderError);
            passwordConfirmField.setToolTipText("Passwords do not match");
        } else {
            passwordConfirmField.setBorder(null);
            passwordConfirmField.setToolTipText(null);
        }

        if (!roleField.getText().matches(ApplicationWindow.usernameRegex)) {
            roleField.setBorder(ApplicationWindow.borderError);
            roleField.setToolTipText("Please enter only letters");
        } else {
            roleField.setBorder(null);
            roleField.setToolTipText(null);
        }

        if (!(firstNameField.getBorder() == ApplicationWindow.borderError || lastNameField.getBorder() == ApplicationWindow.borderError
        || contactNumberField.getBorder() == ApplicationWindow.borderError || addressFirstField.getBorder() == ApplicationWindow.borderError
        || addressSecondField.getBorder() == ApplicationWindow.borderError || cityField.getBorder() == ApplicationWindow.borderError
        || postcodeField.getBorder() == ApplicationWindow.borderError || emailField.getBorder() == ApplicationWindow.borderError)) {


        }

        return false;
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
        createPasswordField.setText("");
        createPasswordField.setBorder(null);
        createPasswordConfirmField.setText("");
        createPasswordConfirmField.setBorder(null);
        createRoleField.setText("");
        createRoleField.setBorder(null);
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
