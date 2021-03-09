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
    private ImageIcon bannerIcon;
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
                    }
                    i++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/login.png");
        bannerLabel.setIcon(bannerIcon);

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

        ApplicationWindow.displayTable(table, customerData, tableColumns);
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
