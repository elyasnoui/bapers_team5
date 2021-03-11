
package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
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
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactNumberField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField niField;
    private JTextField workHoursField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField roleField;
    private JButton popupCancelButton;
    private JTextField privilegesField;
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
            "NI",
            "Work Hours",
            "Username",
            "Password",
            "Role",
            "Privileges"
    };

    public Staff(Bapers system) {
        this.system = system;

        try {
            staffData = DatabaseConnection.getData("staff");
            for (String[] ss : staffData)
                ss[9] = "•••••••";
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/staff.png");
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

        ApplicationWindow.displayTable(table, staffData, tableColumns);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });
        popupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                tablePanel.setVisible(true);
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
        createButton.addMouseListener(mouseListener);
        editButton.addMouseListener(mouseListener);
        deleteButton.addMouseListener(mouseListener);
        popupCreateButton.addMouseListener(mouseListener);
        popupCancelButton.addMouseListener(mouseListener);
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
