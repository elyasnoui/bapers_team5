// This is just for designing GUI panels, will delete later

package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

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
    private JPanel createPanel;
    private JPanel mainPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactNumberField;
    private JTextField addressField;
    private JButton createButton;
    private JButton cancelButton;
    private JTextField emailField;
    private JTextField niField;
    private JTextField workHoursField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField roleField;
    private JTextField privilegesField;

    public Debug(Bapers system) {
        this.system = system;

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
        createButton.addMouseListener(mouseListener);
        cancelButton.addMouseListener(mouseListener);
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