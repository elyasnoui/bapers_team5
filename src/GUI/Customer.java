package GUI;

import System.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;

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
