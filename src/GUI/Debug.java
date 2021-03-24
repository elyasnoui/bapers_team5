// This is just for designing GUI panels, will delete later

package GUI;

import System.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class Debug {
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
    private JPanel customerLookupPanel;
    private JPanel mainPanel;
    private JButton selectButton;
    private JButton cancelButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JScrollPane customerScrollPane;
    private JTable customerTable;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private List<String[]> customerData;
    private final String[] customerColumns = {
            "ID",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Email"
    };

    public Debug(Bapers system) {
        this.system = system;

        firstNameField.setBorder(null);
        lastNameField.setBorder(null);
        customerTable.setModel(new DefaultTableModel(null, customerColumns));

        bannerIcon = new ImageIcon("data/banners/report.png");
        bannerLabel.setIcon(bannerIcon);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { system.changeScreen("logout", mainPanel); }
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

        MouseListener mouseListener = new MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                Component c = evt.getComponent();

                if (c.getBackground().equals(new Color(124, 134, 175))) {
                    c.setBackground(new Color(176, 191, 241));
                    return;
                }

                c.setBackground(new Color(124, 134, 175));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                Component c = evt.getComponent();

                if (c.getBackground().equals(new Color(176, 191, 241))) {
                    c.setBackground(new Color(124, 134, 175));
                    return;
                }

                c.setBackground(new Color(76, 84, 118));
            }
        };

        logoutButton.addMouseListener(mouseListener);
        jobsButton.addMouseListener(mouseListener);
        customerButton.addMouseListener(mouseListener);
        paymentsButton.addMouseListener(mouseListener);
        staffButton.addMouseListener(mouseListener);
        tasksButton.addMouseListener(mouseListener);
        reportsButton.addMouseListener(mouseListener);
        databaseButton.addMouseListener(mouseListener);
        selectButton.addMouseListener(mouseListener);
        cancelButton.addMouseListener(mouseListener);

        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (firstNameField.getText().length() >= 3 || lastNameField.getText().length() >= 3) {
                    customerSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (lastNameField.getText().length() >= 3 || firstNameField.getText().length() >= 3) {
                    customerSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customerTable.getSelectionModel().isSelectionEmpty()) {
                    String ID = customerData.get(customerTable.getSelectedRow())[0];
                }
            }
        });
    }

    private void customerSearch() {
        try {
            customerData = DatabaseConnection.searchCustomer(firstNameField.getText(), lastNameField.getText());
            assert customerData != null;
            ApplicationWindow.displayTable(customerTable, customerData, customerColumns);
        }
        catch (Exception e) { e.printStackTrace(); }
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
