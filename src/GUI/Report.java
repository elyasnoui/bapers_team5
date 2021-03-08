package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import System.*;

public class Report {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JPanel buttonPanel;
    private JTable reportTable;
    private JLabel nameLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private ImageIcon bannerIcon;
    private Bapers system;

    public Report(Bapers system) {
        this.system = system;

        bannerIcon = new ImageIcon("data/banners/report.png");
        bannerLabel.setIcon(bannerIcon);

        Object [][] data = {
                {"01","John", "Jones","07558804711","1 Alley Way, N2 1NA","Fixed", "20"},
                {"02","Bob", "Marley","07888804444", "5 Bookers, SW11 KWE","Variable", "5,0,3,0,0,0,1"},
                {"03","Game", "Stop","07656186388","74A Snooker, W1 2BA","Flexible","0,1,2"},
                {"04","Doge", "Coin","07563656556","4a Shareholder, NBA 2K","None","None"}
        };

        //DefaultTableModel model = new DefaultTableModel()

        //nameLabel.setText(DatabaseConnection.getFirstAndLastName());
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
