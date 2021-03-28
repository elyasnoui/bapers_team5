package GUI;

import System.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextField createFirstNameField;
    private JTextField createLastNameField;
    private JTextField createContactNumberField;
    private JTextField createAddressField;
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
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;

    private boolean isError[] = new boolean[7];

    private List<String[]> customerData;
    private List<String[]> valuedCustomerData;

    private final String nameRegex = "[A-Z]{1}[a-zA-z-]{1,34}";
    //private final String

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
    private final String[] discountTypes = {
            "Fixed Discount",
            "Flexible Discount",
            "Variable Discount"
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
        createVcCheckBox.setIcon(checkBoxIcon);
        createVcCheckBox.setSelectedIcon(selectedCheckBoxIcon);

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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                tablePanel.setVisible(false);
                createPanel.setVisible(true);
                resetCreatePanel();
            }
        });
        createVcCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAgreedDiscountLabel.setVisible(createVcCheckBox.isSelected());
                createAgreedDiscountComboBox.setVisible(createVcCheckBox.isSelected());

                if (createVcCheckBox.isSelected() && createAgreedDiscountComboBox.getSelectedIndex() == 0) {
                    createDiscountRateLabel.setVisible(true);
                    createDiscountRateField.setVisible(true);
                }
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
            }
        });
        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    //String IDs = customerData.get(table.getSelectedRow())[0];

                    for (int id : table.getSelectedRows()) {
                        int ID = Integer.parseInt(customerData.get(id)[0]);
                        DatabaseConnection.removeCustomer(ID);
                        system.changeScreen("customers", mainPanel);
                    }
                }
            }
        });
        createAgreedDiscountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDiscountRateLabel.setVisible(createAgreedDiscountComboBox.getSelectedIndex() == 0);
                createDiscountRateField.setVisible(createAgreedDiscountComboBox.getSelectedIndex() == 0);
            }
        });
    }

    private void resetCreatePanel() {
        createAgreedDiscountComboBox.setModel(new DefaultComboBoxModel<>(discountTypes));

        createVcCheckBox.setSelected(false);
        createFirstNameField.setBorder(null);
        createLastNameField.setBorder(null);
        createContactNumberField.setBorder(null);
        createAddressField.setBorder(null);
        createEmailField.setBorder(null);
        createDiscountRateField.setBorder(null);
    }

    private void addMouseListeners() {
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
    }

    private void removeMouseListeners() {
        logoutButton.removeMouseListener(ApplicationWindow.mouseListener);
        jobsButton.removeMouseListener(ApplicationWindow.mouseListener);
        customerButton.removeMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.removeMouseListener(ApplicationWindow.mouseListener);
        staffButton.removeMouseListener(ApplicationWindow.mouseListener);
        tasksButton.removeMouseListener(ApplicationWindow.mouseListener);
        reportsButton.removeMouseListener(ApplicationWindow.mouseListener);
        databaseButton.removeMouseListener(ApplicationWindow.mouseListener);
        createButton.removeMouseListener(ApplicationWindow.mouseListener);
        editButton.removeMouseListener(ApplicationWindow.mouseListener);
        deleteButton.removeMouseListener(ApplicationWindow.mouseListener);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JPanel getCreatePanel(){ return createPanel;}

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
